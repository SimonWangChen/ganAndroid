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
 * @class name:com.proclassmates.ganandroid
 * @author: simon
 * @time： 2019-11-17 14:48
 */
public class TitleFragment extends Fragment {
    /**
     * @param inflater           将 xml 转换成 view 对象
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_title, null);
        return view;
    }
}
