package com.example.administrator.xiangmu.di.model;

import com.example.administrator.xiangmu.data.utils.OKHttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyHomeListModel implements IHomeListModel{
    ModelInterface modelInterface;

    //重写构造器,调用内部接口
    public MyHomeListModel(ModelInterface modelInterface) {
        this.modelInterface = modelInterface;
    }

    @Override
    public void getDate(String url) {
        OKHttpUtils.OkHttpGet(url, new Callback() {
            @Override
            //失败则调用Model类的内部接口的失败方法
            public void onFailure(Call call, IOException e) {
                modelInterface.ShiBai();
            }
            //成功则调用Model类的内部接口的成功方法
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                modelInterface.ChengGong(response.body().string());
            }
        });
    }

    public interface ModelInterface{
        //获取数据状态回调的接口
        void ChengGong(String data);

        void ShiBai();
    }
}
