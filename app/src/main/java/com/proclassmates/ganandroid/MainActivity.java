package com.proclassmates.ganandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SlidingTabLayout mTabLayout;
    ViewPager mViewPager;

    private List<Fragment> mFragments = new ArrayList<>();
    private List<Date> days = new ArrayList<>();

    /**
     * 显示从某个日期起未来一个月的时间表 viewpager
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout = findViewById(R.id.wx_detail_tab_layout);
        mViewPager = findViewById(R.id.wx_detail_viewpager);
        initEventAndData();
        showDatesListView(days);
    }


    protected void initEventAndData() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for (int i = 0; i < 30; i++) {
            days.add(date);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            date = calendar.getTime();
        }
    }

    private void initViewPagerAndTabLayout(List<Date> dates) {
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments == null ? 0 : mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return "Today";
                }
                return days.get(position).getDate() + "";
            }
        });
        mTabLayout.setViewPager(mViewPager);
    }

    public void showDatesListView(List<Date> dates) {
        mFragments.clear();
        for (Date date : dates) {
            mFragments.add(DateDetailFragment.getInstance(date));
        }
        initViewPagerAndTabLayout(dates);
    }


}
