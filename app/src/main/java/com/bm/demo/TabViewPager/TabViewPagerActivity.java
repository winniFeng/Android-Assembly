package com.bm.demo.TabViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.ViewGroup;


import com.bm.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 多
 */
public class TabViewPagerActivity extends FragmentActivity {
    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private ViewPager mViewPager;
    private final String[] TITLES = {"分类", "主页", "热门推荐", "热门收藏", "本月热榜", "今日热榜", "专栏", "随机"};
    List<Fragment> fragmentsList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_tabviewpager);
        initView();
    }

    private void initView() {
        ExampleFragment fragment;
        for (int i = 0; i < TITLES.length; i++) {
            fragment = new ExampleFragment();
            fragmentsList.add(fragment);
        }
        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragmentsList));
        mPagerSlidingTabStrip.setViewPager(mViewPager);
        mPagerSlidingTabStrip.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        initTabsValue();
    }



    /**
     * mPagerSlidingTabStrip初始化
     */
    private void initTabsValue() {
        // 底部游标颜色
        mPagerSlidingTabStrip.setIndicatorColor(getResources().getColor(R.color.trans));
        // tab的分割线颜色
        mPagerSlidingTabStrip.setDividerColorResource(R.color.design_red);
        // tab底线高度
        mPagerSlidingTabStrip.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                1, getResources().getDisplayMetrics()));
        // 游标高度
        mPagerSlidingTabStrip.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                2, getResources().getDisplayMetrics()));
        // 选中的文字颜色
        mPagerSlidingTabStrip.setSelectedTextColor(getResources().getColor(R.color.blue));
        // 正常文字颜色
        mPagerSlidingTabStrip.setTextColor(Color.BLACK);
    }

    /**
     * 颜色加深处理
     *
     * @param RGBValues
     *            RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     *            Android中我们一般使用它的16进制，
     *            例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     *            red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     *            所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
     * @return
     */
    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentsList;

        // �����б�

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragmentsList) {
            super(fm);
            this.fragmentsList = fragmentsList;
        }

        // �õ�ÿ��item
        @Override
        public Fragment getItem(int position) {
            // return ArrFragment.newInstance(position);
            return fragmentsList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        // ��õ�ǰ����??
        @Override
        public int getCount() {
            return TITLES.length;
        }

        // ��ʼ��ÿ��ҳ��ѡ��
        @Override
        public Object instantiateItem(ViewGroup arg0, int arg1) {
            return super.instantiateItem(arg0, arg1);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

    }
}
