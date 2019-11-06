package com.proclassmates.ganandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CircleMenuLayout mCircleMenuLayout;

    private ListView mListView;

    List<MenuItem> mMenuItems = new ArrayList<MenuItem>();



    //菜单标题
    private String[] mItemTexts = new String[]{
            "安全中心",
            "特色服务",
            "投资理财",
            "转账汇款",
            "我的账户",
            "信用卡",
    };
    // 菜单图标
    private int[] mItemImgs = new int[]{
            R.drawable.home_mbank_1_normal,
            R.drawable.home_mbank_2_normal,
            R.drawable.home_mbank_3_normal,
            R.drawable.home_mbank_4_normal,
            R.drawable.home_mbank_5_normal,
            R.drawable.home_mbank_6_normal,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //初始化圆形菜单
        mCircleMenuLayout = findViewById(R.id.id_menulayout);

        // 设置菜单数据项
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);

        // 设置菜单项点击事件
        mCircleMenuLayout.setOnItemClickListenr(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, mItemTexts[i], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
