package com.example.administrator.xiangmu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangmu.data.bean.DengBean;
import com.example.administrator.xiangmu.di.contrant.Contrant;
import com.example.administrator.xiangmu.di.presenter.IDengPresenterImpl;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements Contrant.IDengView {

    @BindView(R.id.ed_dl_sj)
    EditText edDlSj;
    @BindView(R.id.ed_dl_pwd)
    EditText edDlPwd;
    @BindView(R.id.btn_dl)
    Button btnDl;
    @BindView(R.id.text_zc)
    TextView textZc;
    @BindView(R.id.jz_check)
    CheckBox jzCheck;
    private Contrant.IDengPresenter dengPresenter;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private DengBean dengBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        dengPresenter = new IDengPresenterImpl();
        dengPresenter.attachView(this);

        preferences = getSharedPreferences("User", MODE_PRIVATE);
        editor = preferences.edit();

        boolean ischeck = preferences.getBoolean("ischeck", false);
        if (ischeck){

            String userName = edDlSj.getText().toString();
            String password = edDlPwd.getText().toString();

            edDlSj.setText(userName);
            edDlPwd.setText(password);
            jzCheck.setChecked(true);

        }

        textZc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZhuceActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @OnClick(R.id.btn_dl)
    public void onViewClicked() {
        String userName = edDlSj.getText().toString();
        String password = edDlPwd.getText().toString();
        dengPresenter.requstLoginData(userName, password);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dengPresenter.detachView(this);
    }

    @Override
    public void showData(final String responseData) {
        gson = new Gson();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dengBean = gson.fromJson(responseData, DengBean.class);
                Toast.makeText(MainActivity.this, dengBean.getMessage(), Toast.LENGTH_SHORT).show();

                String userName = edDlSj.getText().toString();
                String password = edDlPwd.getText().toString();


                if (jzCheck.isChecked()) {

                    editor.putString("userName", userName);
                    editor.putString("password", password);

                    editor.putBoolean("ischeck", true);
                    editor.commit();
                }
                    if (dengBean.getStatus().equals("0000")) {
                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                        startActivity(intent);
                    }
            }
        });
    }

    @Override
    public void jumpActivity() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dengBean.getStatus().equals("0000")) {
                    Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
