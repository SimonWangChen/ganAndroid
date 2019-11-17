package com.proclassmates.ganandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.proclassmates.ganandroid.fragment.FindFragment;
import com.proclassmates.ganandroid.fragment.FriendFragment;
import com.proclassmates.ganandroid.fragment.MineFragment;
import com.proclassmates.ganandroid.fragment.WechatFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //wechat
    private ImageView iv_wechat;
    private TextView tv_wechat;
    private LinearLayout ll_wechat;
    private WechatFragment mWechatFragment = null;
    private FragmentTransaction mWechatTransaction = null;
    //friend
    private ImageView iv_friend;
    private TextView tv_friend;
    private LinearLayout ll_friend;
    private FriendFragment mFriendFragment = null;
    private FragmentTransaction mFriendTransaction = null;
    //find
    private ImageView iv_find;
    private TextView tv_find;
    private LinearLayout ll_find;
    private FindFragment mFindFragment = null;
    private FragmentTransaction mFindTransaction = null;
    //mine
    private ImageView iv_mine;
    private TextView tv_mine;
    private LinearLayout ll_mine;
    private MineFragment mMineFragment = null;
    private FragmentTransaction mMineTransaction = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //wechat
        ll_wechat = findViewById(R.id.ll_wechat);
        tv_wechat = findViewById(R.id.tv_wechat);
        iv_wechat = findViewById(R.id.iv_wechat);
        //friend
        ll_friend = findViewById(R.id.ll_friend);
        tv_friend = findViewById(R.id.tv_friend);
        iv_friend = findViewById(R.id.iv_friend);
        //find
        ll_find = findViewById(R.id.ll_find);
        tv_find = findViewById(R.id.tv_find);
        iv_find = findViewById(R.id.iv_find);
        //mine
        ll_mine = findViewById(R.id.ll_mine);
        tv_mine = findViewById(R.id.tv_mine);
        iv_mine = findViewById(R.id.iv_mine);

        ll_wechat.setOnClickListener(this);
        ll_friend.setOnClickListener(this);
        ll_find.setOnClickListener(this);
        ll_mine.setOnClickListener(this);

        initFragment();

        checkMainTab(0);


    }

    private void initFragment() {
        //wechat
        if (mWechatFragment == null) {
            mWechatFragment = new WechatFragment();
            mWechatTransaction = getSupportFragmentManager().beginTransaction();
            mWechatTransaction.add(R.id.main_layout, mWechatFragment);
            mWechatTransaction.commit();
        }
        //Friend
        if (mFriendFragment == null) {
            mFriendFragment = new FriendFragment();
            mFriendTransaction = getSupportFragmentManager().beginTransaction();
            mFriendTransaction.add(R.id.main_layout, mFriendFragment);
            mFriendTransaction.commit();
        }
        //Find
        if (mFindFragment == null) {
            mFindFragment = new FindFragment();
            mFindTransaction = getSupportFragmentManager().beginTransaction();
            mFindTransaction.add(R.id.main_layout, mFindFragment);
            mFindTransaction.commit();
        }
        //Mine
        if (mMineFragment == null) {
            mMineFragment = new MineFragment();
            mMineTransaction = getSupportFragmentManager().beginTransaction();
            mMineTransaction.add(R.id.main_layout, mMineFragment);
            mMineTransaction.commit();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_wechat:
                checkMainTab(0);
                break;
            case R.id.ll_friend:
                checkMainTab(1);
                break;
            case R.id.ll_find:
                checkMainTab(2);
                break;
            case R.id.ll_mine:
                checkMainTab(3);
                break;
        }
    }

    private void checkMainTab(int i) {
        switch (i) {
            case 0:
                showFragment(mWechatFragment);

                iv_wechat.setImageResource(R.drawable.img_wechat_s);
                iv_friend.setImageResource(R.drawable.img_friend);
                iv_find.setImageResource(R.drawable.img_find);
                iv_mine.setImageResource(R.drawable.img_mine);

                tv_wechat.setTextColor(Color.RED);
                tv_friend.setTextColor(Color.BLACK);
                tv_find.setTextColor(Color.BLACK);
                tv_mine.setTextColor(Color.BLACK);

                break;
            case 1:
                showFragment(mFriendFragment);

                iv_wechat.setImageResource(R.drawable.img_wechat);
                iv_friend.setImageResource(R.drawable.img_friend_s);
                iv_find.setImageResource(R.drawable.img_find);
                iv_mine.setImageResource(R.drawable.img_mine);

                tv_wechat.setTextColor(Color.BLACK);
                tv_friend.setTextColor(Color.RED);
                tv_find.setTextColor(Color.BLACK);
                tv_mine.setTextColor(Color.BLACK);
                break;
            case 2:
                showFragment(mFindFragment);

                iv_wechat.setImageResource(R.drawable.img_wechat);
                iv_friend.setImageResource(R.drawable.img_friend);
                iv_find.setImageResource(R.drawable.img_find_s);
                iv_mine.setImageResource(R.drawable.img_mine);

                tv_wechat.setTextColor(Color.BLACK);
                tv_friend.setTextColor(Color.BLACK);
                tv_find.setTextColor(Color.RED);
                tv_mine.setTextColor(Color.BLACK);
                break;
            case 3:
                showFragment(mMineFragment);

                iv_wechat.setImageResource(R.drawable.img_wechat);
                iv_friend.setImageResource(R.drawable.img_friend);
                iv_find.setImageResource(R.drawable.img_find);
                iv_mine.setImageResource(R.drawable.img_mine_s);

                tv_wechat.setTextColor(Color.BLACK);
                tv_friend.setTextColor(Color.BLACK);
                tv_find.setTextColor(Color.BLACK);
                tv_mine.setTextColor(Color.RED);
                break;

        }
    }

    private void showFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            hideAllFragment(transaction);
            transaction.show(fragment);
            transaction.commitAllowingStateLoss();
        }
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (mWechatFragment != null) {
            transaction.hide(mWechatFragment);
        }
        if (mFriendFragment != null) {
            transaction.hide(mFriendFragment);
        }
        if (mFindFragment != null) {
            transaction.hide(mFindFragment);
        }
        if (mMineFragment != null) {
            transaction.hide(mMineFragment);
        }
    }
}
