package com.example.administrator.xiangmu.di.presenter;

import com.example.administrator.xiangmu.di.model.MyHomeListModel;
import com.example.administrator.xiangmu.ui.fragment.Fragment_Home;

public class MyHomeListPresenter implements IHomeLitsPresenter,MyHomeListModel.ModelInterface{
    String dataurl="http://mobile.bwstudent.com/small/commodity/v1/commodityList";
    Fragment_Home fragmentHome;
    private final MyHomeListModel myHomeListModel;

    public MyHomeListPresenter(Fragment_Home fragmentHome) {
        this.fragmentHome = fragmentHome;
        //获取Model层
        myHomeListModel = new MyHomeListModel(this);
    }

    @Override
    public void ChengGong(String data) {
        fragmentHome.getViewData(data);
    }

    @Override
    public void ShiBai() {
        fragmentHome.getViewData("加载失败");
    }

    @Override
    public void getModelData() {
        myHomeListModel.getDate(dataurl);
    }
}
