package com.example.administrator.xiangmu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.xiangmu.data.bean.ZhuceBean;
import com.example.administrator.xiangmu.di.contrant.ZhuCeContrant;
import com.example.administrator.xiangmu.di.presenter.IZheCePresenterImpl;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhuceActivity extends AppCompatActivity implements ZhuCeContrant.IZhuCerView {

    @BindView(R.id.zc_ed_sj)
    EditText zcEdSj;
    @BindView(R.id.zc_ed_yzm)
    EditText zcEdYzm;
    @BindView(R.id.zc_ed_pwd)
    EditText zcEdPwd;
    @BindView(R.id.text_dl)
    TextView textDl;
    @BindView(R.id.btn_dl)
    Button btnDl;
    private ZhuCeContrant.IZhuCePresenter presenter;
    private ZhuceBean zhuceBean;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        ButterKnife.bind(this);

        presenter = new IZheCePresenterImpl();
        presenter.attahView(this);


        textDl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZhuceActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @OnClick(R.id.btn_dl)
    public void onViewClicked() {
        String userName = zcEdSj.getText().toString();
        String pwd = zcEdPwd.getText().toString();
        //点击时触发，P层请求M层的动作
        presenter.requestData(userName , pwd);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(this);
    }

    @Override
    public void showData(final String response) {

        gson = new Gson();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                zhuceBean = gson.fromJson(response, ZhuceBean.class);
                Toast.makeText(ZhuceActivity.this, zhuceBean.getMessage(), Toast.LENGTH_SHORT).show();

                if (zhuceBean.getStatus().equals("0000")) {
                    Intent intent = new Intent(ZhuceActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });



    }
}
