package com.proclassmates.ganandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateDetailFragment extends Fragment {

    @BindView(R.id.rv_date_detail)
    RecyclerView rvDateDetail;
    @BindView(R.id.normal_view)
    SmartRefreshLayout normalView;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    private Date day;

    public DateDetailFragment() {
    }


    public static DateDetailFragment getInstance(Date date) {
        DateDetailFragment fragment = new DateDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("date", date);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            day = (Date) getArguments().getSerializable("date");
        }
        Log.d("DateDetailFragment", "onCreate" + day);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("DateDetailFragment", "onCreateView" + day);
        View view = inflater.inflate(R.layout.fragment_date_detail, container, false);
        ButterKnife.bind(this, view);
        tvDetail.setText("days");
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("DateDetailFragment", "onDetach" + day);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("DateDetailFragment", "onResume" + day);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("DateDetailFragment", "onStart" + day);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("DateDetailFragment", "onPause" + day);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("DateDetailFragment", "onDestroyView" + day);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("DateDetailFragment", "onDestroy" + day);
    }
}
