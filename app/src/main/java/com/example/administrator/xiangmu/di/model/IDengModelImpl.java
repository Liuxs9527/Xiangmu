package com.example.administrator.xiangmu.di.model;

import com.example.administrator.xiangmu.data.Constant;
import com.example.administrator.xiangmu.di.contrant.Contrant;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IDengModelImpl  implements Contrant.IDengModel {

    @Override
    public void containLoginResponseData(final String userName,final String password,final CallBack callback) {
        requestLoginDataEnqueue(userName, password, callback);

    }

    private void requestLoginDataEnqueue(String userName, String password, final CallBack callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();
        FormBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .method("POST", formBody)
                .url(Constant.DENG_URL + "?phone=" + userName + "&pwd=" + password)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                String responseData = e.getMessage();
                callback.responseData(responseData);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String responseData = response.body().string();
                callback.responseData(responseData);

            }
        });
    }
}
