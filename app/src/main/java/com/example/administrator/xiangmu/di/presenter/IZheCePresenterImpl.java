package com.example.administrator.xiangmu.di.presenter;

import com.example.administrator.xiangmu.di.contrant.Contrant;
import com.example.administrator.xiangmu.di.contrant.ZhuCeContrant;
import com.example.administrator.xiangmu.di.model.IZheCeModelImpl;

import java.lang.ref.SoftReference;

public class IZheCePresenterImpl implements ZhuCeContrant.IZhuCePresenter {

    ZhuCeContrant.IZhuCerView zhuCerView;
    private SoftReference<ZhuCeContrant.IZhuCerView> reference;
    private ZhuCeContrant.IZheCeModel zheCeModel;


    @Override
    public void attahView(ZhuCeContrant.IZhuCerView zhuceView) {
        this.zhuCerView = zhuceView;

        reference = new SoftReference<>(zhuceView);
        zheCeModel = new IZheCeModelImpl();
    }

    @Override
    public void detachView(ZhuCeContrant.IZhuCerView zhuceView) {
        reference.clear();
    }

    @Override
    public void requestData(String userName, String pwd) {
        zheCeModel.containData(userName, pwd, new ZhuCeContrant.IZheCeModel.CallBack() {
            @Override
            public void responseData(String response) {
                zhuCerView.showData(response);
            }
        });
    }


}
