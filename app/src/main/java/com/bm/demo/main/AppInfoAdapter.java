package com.bm.demo.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bm.demo.R;
import com.bm.demo.util.ViewHolder;

import java.util.List;

public class AppInfoAdapter extends BaseAdapter {

    private Context mContext = null;
    private List<AppInfo> list;

    public AppInfoAdapter(Context context, List<AppInfo> list) {
        this.mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_app, parent,
                    false);
        }

        TextView tv_app_name = ViewHolder.get(convertView, R.id.tv_app_name);
        tv_app_name.setText(list.get(position).app_name);

        TextView tv_app_auth = ViewHolder.get(convertView, R.id.tv_app_auth);
        tv_app_auth.setText(list.get(position).app_auth);

        return convertView;
    }
}
