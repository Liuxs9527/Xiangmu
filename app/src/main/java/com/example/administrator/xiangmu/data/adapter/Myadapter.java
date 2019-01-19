package com.example.administrator.xiangmu.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiangmu.R;
import com.example.administrator.xiangmu.data.bean.MyBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class Myadapter extends BaseAdapter {
    private final Context context;
    private final List<MyBean.ResultBean.RxxpBean.CommodityListBean> list;
    private final LayoutInflater layoutInflater;

    public Myadapter(Context context, List<MyBean.ResultBean.RxxpBean.CommodityListBean> list) {
        this.context = context;
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder = null;
        if (convertView == null) {
            myHolder = new MyHolder();
            convertView = layoutInflater.inflate(R.layout.item, parent, false);
            myHolder.imageView = convertView.findViewById(R.id.imageView);
            myHolder.textView = convertView.findViewById(R.id.textView);
            myHolder.textView2 = convertView.findViewById(R.id.textView2);
            convertView.setTag(myHolder);
        }else {
            myHolder = (MyHolder) convertView.getTag();
        }
        myHolder.textView.setText(list.get(position).getCommodityName());
        myHolder.textView2.setText(list.get(position).getPrice());
        ImageLoader.getInstance().displayImage(list.get(position).getMasterPic(),myHolder.imageView);
        return convertView;
    }
    class MyHolder{
        TextView textView,textView2;
        ImageView imageView;
    }

}
