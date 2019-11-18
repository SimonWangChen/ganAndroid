package com.proclassmates.ganandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.proclassmates.ganandroid.adapter.SimpleListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private SimpleListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        initViews();

        mAdapter = new SimpleListAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);
        //设置布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //设置分割线 https://gist.github.com/alexfu/0f464fc3742f134ccd1e
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

    }

    private void initDatas() {
        mDatas = new ArrayList<>();

        for (int i = 'A'; i <= 'Z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.id_recyclerView);
    }
}
