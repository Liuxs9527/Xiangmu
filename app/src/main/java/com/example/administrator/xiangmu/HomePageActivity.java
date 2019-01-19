package com.example.administrator.xiangmu;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.xiangmu.ui.fragment.Fragment_Circle;
import com.example.administrator.xiangmu.ui.fragment.Fragment_Home;
import com.example.administrator.xiangmu.ui.fragment.Fragment_List;
import com.example.administrator.xiangmu.ui.fragment.Fragment_My;
import com.example.administrator.xiangmu.ui.fragment.Fragment_Shopping;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageActivity extends AppCompatActivity {

    @BindView(R.id.frag)
    FrameLayout frag;
    @BindView(R.id.btn_home)
    RadioButton btnHome;
    @BindView(R.id.btn_circle)
    RadioButton btnCircle;
    @BindView(R.id.btn_shopping)
    RadioButton btnShopping;
    @BindView(R.id.btn_list)
    RadioButton btnList;
    @BindView(R.id.btn_my)
    RadioButton btnMy;
    @BindView(R.id.rads)
    RadioGroup rads;

    private Fragment_Home fragment_home;
    private Fragment_Circle fragment_circle;
    private Fragment_Shopping fragment_shopping;
    private Fragment_List fragment_list;
    private Fragment_My fragment_my;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);

        rads.check(rads.getChildAt(0).getId());

        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragment_home = new Fragment_Home();
        fragment_circle = new Fragment_Circle();
        fragment_shopping = new Fragment_Shopping();
        fragment_list = new Fragment_List();
        fragment_my = new Fragment_My();

        transaction.add(R.id.frag,fragment_home);
        transaction.add(R.id.frag,fragment_circle);
        transaction.add(R.id.frag,fragment_shopping);
        transaction.add(R.id.frag,fragment_list);
        transaction.add(R.id.frag,fragment_my);

        transaction.show(fragment_home).hide(fragment_circle).hide(fragment_shopping).hide(fragment_list).hide(fragment_my);
        transaction.commit();

        rads.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (checkedId){
                    case R.id.btn_home:
                        fragmentTransaction.show(fragment_home).hide(fragment_circle).hide(fragment_shopping).hide(fragment_list).hide(fragment_my);
                        break;
                    case R.id.btn_circle:
                        fragmentTransaction.hide(fragment_home).show(fragment_circle).hide(fragment_shopping).hide(fragment_list).hide(fragment_my);
                        break;
                    case R.id.btn_shopping:
                        fragmentTransaction.hide(fragment_home).hide(fragment_circle).show(fragment_shopping).hide(fragment_list).hide(fragment_my);
                        break;
                    case R.id.btn_list:
                        fragmentTransaction.hide(fragment_home).hide(fragment_circle).hide(fragment_shopping).show(fragment_list).hide(fragment_my);
                        break;
                    case R.id.btn_my:
                        fragmentTransaction.hide(fragment_home).hide(fragment_circle).hide(fragment_shopping).hide(fragment_list).show(fragment_my);
                        break;

                    default:
                        break;
                }
                fragmentTransaction.commit();
            }
        });
    }



















}
