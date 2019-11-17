package com.proclassmates.ganandroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.proclassmates.ganandroid.R;

/**
 * @name: GanAndroid
 * @desc:
 * @class name:com.proclassmates.ganandroid.fragment
 * @author: simon
 * @time： 2019-11-17 19:40
 */
public class WechatFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wechat, null);
    }
}
