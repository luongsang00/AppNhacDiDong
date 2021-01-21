package com.example.appnghenhac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter_listbaihat extends BaseAdapter {
    Context mContext;
    int mLayout;
    List<list_baihat> mList_baihat;

    Adapter_listbaihat(Context context, int Layout, List<list_baihat> list_baihat)
    {
        mContext=context;
        mLayout=Layout;
        mList_baihat = list_baihat;
    }

    @Override
    public int getCount() {
        return mList_baihat.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        convertView = inflater.inflate(mLayout, null);

        //ánh xạ
        TextView txt_tenbaihat = (TextView) convertView.findViewById(R.id.ten_bai_hat);
        txt_tenbaihat.setText(mList_baihat.get(position).tenbaihat);
        TextView txt_tgbaihat = (TextView) convertView.findViewById((R.id.sum_time));
        txt_tgbaihat.setText(mList_baihat.get(position).tgbai);

        return convertView;
    }
}
