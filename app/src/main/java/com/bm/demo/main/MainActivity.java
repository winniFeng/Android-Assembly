package com.bm.demo.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bm.demo.R;
import com.bm.demo.TabViewPager.SimpleTabActivity;
import com.bm.demo.TabViewPager.TabViewPagerActivity;
import com.bm.demo.gky.MyProCity;
import com.bm.demo.upload.UploadActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private ListView lv_list;
    private List<AppInfo> list;
    private AppInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);

        lv_list = (ListView) findViewById(R.id.lv_list);

        list = new ArrayList<AppInfo>();
        list.add(new AppInfo("下拉刷新","赵鑫", UploadActivity.class));
        list.add(new AppInfo("日期控件","郭楷炎", UploadActivity.class));
        list.add(new AppInfo("省市区三级联动控件","郭楷炎", MyProCity.class));
        list.add(new AppInfo("城市快速选择seekbar","郭楷炎", UploadActivity.class));
        list.add(new AppInfo("相册一次选择多张图片","杨凯", UploadActivity.class));
        list.add(new AppInfo("图片裁剪","陈斌", UploadActivity.class));
        list.add(new AppInfo("自定义下拉框","周伟男", UploadActivity.class));
        list.add(new AppInfo("tabHost","陈斌", UploadActivity.class));
        list.add(new AppInfo("广告轮播图","周伟男", UploadActivity.class));
        list.add(new AppInfo("多个tab点击和滑动切换","杨凯", TabViewPagerActivity.class));
        list.add(new AppInfo("少数tab点击和滑动切换","杨凯", SimpleTabActivity.class));
        list.add(new AppInfo("listview侧滑删除","赵鑫", UploadActivity.class));
        list.add(new AppInfo("第三方登陆","杨凯", UploadActivity.class));
        list.add(new AppInfo("第三方分享","杨凯", UploadActivity.class));
        list.add(new AppInfo("支付","冯文卉", UploadActivity.class));
        adapter = new AppInfoAdapter(this,list);
        lv_list.setAdapter(adapter);
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotoOtherActivity(list.get(position).activity);
            }
        });
    }

    public void gotoOtherActivity(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
    }

}
