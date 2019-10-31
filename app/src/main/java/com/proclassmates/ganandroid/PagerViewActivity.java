package com.proclassmates.ganandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PagerViewActivity extends AppCompatActivity {

    private ViewPager mPagerView;
    private List<View> mPagerViewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_view);

        mPagerView = findViewById(R.id.mViewPager);
        View pagerView1 = View.inflate(this, R.layout.layout_pager_guide_1, null);
        View pagerView2 = View.inflate(this, R.layout.layout_pager_guide_2, null);
        View pagerView3 = View.inflate(this, R.layout.layout_pager_guide_3, null);

        mPagerViewList.add(pagerView1);
        mPagerViewList.add(pagerView2);
        mPagerViewList.add(pagerView3);

        //预加载
        mPagerView.setOffscreenPageLimit(mPagerViewList.size());

        //Adapter
        PagerViewAdapter pagerViewAdapter = new PagerViewAdapter(mPagerViewList);

        mPagerView.setAdapter(pagerViewAdapter);



    }
}
