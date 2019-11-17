package com.proclassmates.ganandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.proclassmates.ganandroid.fragment.ContentFragment;
import com.proclassmates.ganandroid.fragment.TitleFragment;

//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dynamic_main);
//
//        /**
//         * 1.创建Fragment 管理器对象
//         * 2.获取 fragment 事物对象
//         * 3.fragment 事物对象对 fragment 进行管理
//         * 4.提交事物
//         */
//        FragmentManager fragmentManager = getFragmentManager();
//
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        fragmentTransaction.add(R.id.title_layout, new TitleFragment());
//        fragmentTransaction.add(R.id.content_layout, new ContentFragment());
//        fragmentTransaction.commit();
//
//
//    }
//}
