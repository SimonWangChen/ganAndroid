package com.proclassmates.ganandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class PagerViewActivity extends AppCompatActivity {

    private ImageView iv_guide_point_1;
    private ImageView iv_guide_point_2;
    private ImageView iv_guide_point_3;

    private ViewPager mPagerView;

    private List<View> mPagerViewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_view);

        iv_guide_point_1 = (ImageView) findViewById(R.id.iv_guide_point_1);
        iv_guide_point_2 = (ImageView) findViewById(R.id.iv_guide_point_2);
        iv_guide_point_3 = (ImageView) findViewById(R.id.iv_guide_point_3);

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


        mPagerView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectPoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void selectPoint(int position) {
        switch (position){
            case 0:
                iv_guide_point_1.setImageResource(R.drawable.img_guide_point_p);
                iv_guide_point_2.setImageResource(R.drawable.img_guide_point);
                iv_guide_point_3.setImageResource(R.drawable.img_guide_point);
                break;
            case 1:
                iv_guide_point_1.setImageResource(R.drawable.img_guide_point);
                iv_guide_point_2.setImageResource(R.drawable.img_guide_point_p);
                iv_guide_point_3.setImageResource(R.drawable.img_guide_point);
                break;
            case 2:
                iv_guide_point_1.setImageResource(R.drawable.img_guide_point);
                iv_guide_point_2.setImageResource(R.drawable.img_guide_point);
                iv_guide_point_3.setImageResource(R.drawable.img_guide_point_p);
                break;
        }
    }
}
