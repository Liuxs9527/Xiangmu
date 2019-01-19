package com.example.administrator.xiangmu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.example.administrator.xiangmu.R;
import com.example.administrator.xiangmu.data.adapter.Myadapter;
import com.example.administrator.xiangmu.data.adapter.Threeadapter;
import com.example.administrator.xiangmu.data.adapter.Twoadapter;
import com.example.administrator.xiangmu.data.bean.MyBean;
import com.example.administrator.xiangmu.di.presenter.MyHomeListPresenter;
import com.example.administrator.xiangmu.di.view.MyHomeView;
import com.google.gson.Gson;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Home extends Fragment implements MyHomeView {
    private MyHomeListPresenter myHomeListPresenter;
    private List<MyBean.ResultBean.RxxpBean.CommodityListBean> list;
    private GridView gridview;
    private List<MyBean.ResultBean.MlssBean.CommodityListBeanXX> list1;
    private ListView listView;
    private GridView gridView2;
    private List<MyBean.ResultBean.PzshBean.CommodityListBeanX> list2;
    private FlyBanner flyBanner;
    private ArrayList<String> list3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        gridview = view.findViewById(R.id.gridview);
        listView = view.findViewById(R.id.listview);
        gridView2 = view.findViewById(R.id.gridview2);
        flyBanner = view.findViewById(R.id.flybanner);
        myHomeListPresenter = new MyHomeListPresenter(this);
        myHomeListPresenter.getModelData();



        //创建数组存放网络图片
        list3 = new ArrayList<>();
        list3.add("http://mobile.bwstudent.com/images/small/banner/cj.png");
        list3.add("http://mobile.bwstudent.com/images/small/banner/hzp.png");
        list3.add("http://mobile.bwstudent.com/images/small/banner/px.png");
        list3.add("http://mobile.bwstudent.com/images/small/banner/wy.png");
        //list3.add("http://172.17.8.100/images/small/banner/wy.png");
        flyBanner.setImagesUrl(list3);
        return view;



    }


    @Override
    public void getViewData(String mViewData) {
        Gson gson = new Gson();
        MyBean myBean = gson.fromJson(mViewData, MyBean.class);
        list = myBean.getResult().getRxxp().get(0).getCommodityList();
        Myadapter myadapter = new Myadapter(getActivity(), list);
        gridview.setAdapter(myadapter);

        list1 = myBean.getResult().getMlss().get(0).getCommodityList();
        Twoadapter twoadapter = new Twoadapter(getActivity(),list1);
        listView.setAdapter(twoadapter);

        list2 = myBean.getResult().getPzsh().get(0).getCommodityList();
        Threeadapter threeadapter = new Threeadapter(getActivity(),list2);
        gridView2.setAdapter(threeadapter);
    }
}
