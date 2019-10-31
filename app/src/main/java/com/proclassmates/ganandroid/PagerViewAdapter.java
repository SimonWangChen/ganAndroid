package com.proclassmates.ganandroid;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author simon
 * @name GanAndroid
 * @class name：com.proclassmates.ganandroid
 * @time 2019-10-31 12:05
 */
public class PagerViewAdapter extends PagerAdapter {

    List<View> mPagerViewList;

    public PagerViewAdapter(List<View> pagerViewList) {
        mPagerViewList = pagerViewList;
    }

    @Override
    public int getCount() {
        return mPagerViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ((ViewPager) container).addView(mPagerViewList.get(position));

        return mPagerViewList.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView(mPagerViewList.get(position));
        super.destroyItem(container, position, object);

    }
}
