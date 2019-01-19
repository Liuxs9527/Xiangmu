package com.example.administrator.xiangmu.di.presenter;

import android.print.PrinterId;
import android.widget.Toast;

import com.example.administrator.xiangmu.di.contrant.Contrant;
import com.example.administrator.xiangmu.di.model.IDengModelImpl;

import java.lang.ref.SoftReference;

public class IDengPresenterImpl implements Contrant.IDengPresenter{

    Contrant.IDengView dengView;
    private SoftReference<Contrant.IDengView>  reference;
    private Contrant.IDengModel dengModel;


    @Override
    public void attachView(Contrant.IDengView dengView) {
        this.dengView = dengView;

        reference = new SoftReference<>(dengView);

        dengModel = new IDengModelImpl();
    }

    @Override
    public void detachView(Contrant.IDengView dengView) {
        reference.clear();
    }

    @Override
    public void requstLoginData(String userName, String password) {
        dengModel.containLoginResponseData(userName, password, new Contrant.IDengModel.CallBack() {
            @Override
            public void responseData(String responseData) {
                dengView.showData(responseData);
            }
        });
    }


}
