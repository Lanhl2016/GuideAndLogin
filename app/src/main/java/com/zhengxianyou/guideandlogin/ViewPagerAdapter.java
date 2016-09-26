package com.zhengxianyou.guideandlogin;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhengxianyou on 2016/9/23.
 * 创建viewpager适配器，设置相关属性
 */

public class ViewPagerAdapter extends PagerAdapter{
    private List<View>views;
    private Context mContext;

    ViewPagerAdapter(List<View>views,Context mContext){
        this.views = views;
        this.mContext = mContext;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager)container).addView(views.get(position));
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
