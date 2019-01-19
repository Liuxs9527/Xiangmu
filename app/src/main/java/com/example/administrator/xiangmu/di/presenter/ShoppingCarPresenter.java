package com.example.administrator.xiangmu.di.presenter;

import com.example.administrator.xiangmu.di.contrant.ShoppingCartContract;
import com.example.administrator.xiangmu.di.model.ShoppinCartModel;

import java.lang.ref.SoftReference;

public class ShoppingCarPresenter implements ShoppingCartContract.IPresenter<ShoppingCartContract.IView> {
    ShoppingCartContract.IView iView;
    private SoftReference<ShoppingCartContract.IView> softReference;
    private ShoppingCartContract.IModel model;

    @Override
    public void attachView(ShoppingCartContract.IView iView) {
        this.iView = iView;
        softReference = new SoftReference<>(iView);
        model = new ShoppinCartModel();
    }

    @Override
    public void detachView(ShoppingCartContract.IView iView) {
        softReference.clear();
    }

    @Override
    public void requestData() {
        iView.showLoading();
        model.containData(new ShoppingCartContract.IModel.OnCallBackListener() {
            @Override
            public void onCallBack(String mCartString) {
                //去掉进度条
                iView.hideLoading();
                //数据回显
                iView.showData(mCartString);
            }
        });
    }
}
