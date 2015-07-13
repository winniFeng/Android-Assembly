package com.bm.demo.TabViewPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.bm.demo.R;

import java.util.ArrayList;
import java.util.List;


public class SimpleTabActivity extends FragmentActivity {

    List<Fragment> fragmentsList = new ArrayList<Fragment>();
    private RadioGroup radioGroup;

    private ViewPager viewpager;
    private TabViewPagerAdapter adapter;
    private TextView tv_cursor;
    private int currIndex;// 当前页卡编号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_simpletab);
        initView();
        InitTextBar();
    }

    private void initView() {
        ExampleFragment fragment;
        fragment = new ExampleFragment();
        fragmentsList.add(fragment);
        fragment = new ExampleFragment();
        fragmentsList.add(fragment);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        tv_cursor = (TextView) findViewById(R.id.tv_cursor);
        adapter = new TabViewPagerAdapter(getSupportFragmentManager(), fragmentsList);
        viewpager.setAdapter(adapter);
        viewpager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                if (arg0 == 0) {
                    radioGroup.check(R.id.rb_one);
                } else {
                    radioGroup.check(R.id.rb_two);
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // 取得该控件的实例
                LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) tv_cursor
                        .getLayoutParams();

                if (currIndex == arg0) {
                    ll.leftMargin = (int) (currIndex * tv_cursor.getWidth() + arg1
                            * tv_cursor.getWidth());
                     ll.leftMargin += (arg0 == 0 ? 0 : 1);
                } else if (currIndex > arg0) {
                    ll.leftMargin = (int) (currIndex * tv_cursor.getWidth() - (1 - arg1)
                            * tv_cursor.getWidth());
                }
                tv_cursor.setLayoutParams(ll);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // 点击事件获取的选择对象
                switch (checkedId) {
                    case R.id.rb_one:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.rb_two:
                        viewpager.setCurrentItem(1);
                        break;
                }
            }
        });
    }

    /*
     * 初始化图片的位移像素
     */
    public void InitTextBar() {
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        // 得到显示屏宽度
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        // 1/2屏幕宽度
        int tabLineLength = metrics.widthPixels / 2;
        android.view.ViewGroup.LayoutParams lp = (android.view.ViewGroup.LayoutParams) tv_cursor.getLayoutParams();
        lp.width = tabLineLength;
        tv_cursor.setLayoutParams(lp);

    }
}
