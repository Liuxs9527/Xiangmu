package com.example.administrator.xiangmu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.xiangmu.MainActivity;
import com.example.administrator.xiangmu.R;
import com.example.administrator.xiangmu.ui.fragment.my.My_Circle;
import com.example.administrator.xiangmu.ui.fragment.my.My_address;
import com.example.administrator.xiangmu.ui.fragment.my.My_information;
import com.example.administrator.xiangmu.ui.fragment.my.My_qianbao;
import com.example.administrator.xiangmu.ui.fragment.my.My_zuji;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Fragment_My extends Fragment {

    @BindView(R.id.drawee_img)
    SimpleDraweeView draweeImg;
    @BindView(R.id.text_geren)
    TextView textGeren;
    @BindView(R.id.text_quanzi)
    TextView textQuanzi;
    @BindView(R.id.text_zuji)
    TextView textZuji;
    @BindView(R.id.text_qianbao)
    TextView textQianbao;
    @BindView(R.id.text_dizhi)
    TextView textDizhi;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my, container, false);


        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.text_geren, R.id.text_quanzi, R.id.text_zuji, R.id.text_qianbao, R.id.text_dizhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_geren:
                Intent intent = new Intent(getActivity(),My_information.class);
                startActivity(intent);
                break;
            case R.id.text_quanzi:
                Intent intent1 = new Intent(getActivity(),My_Circle.class);
                startActivity(intent1);
                break;
            case R.id.text_zuji:
                Intent intent2 = new Intent(getActivity(),My_zuji.class);
                startActivity(intent2);
                break;
            case R.id.text_qianbao:
                Intent intent3 = new Intent(getActivity(),My_qianbao.class);
                startActivity(intent3);
                break;
            case R.id.text_dizhi:
                Intent intent4 = new Intent(getActivity(),My_address.class);
                startActivity(intent4);
                break;
        }
    }
}
