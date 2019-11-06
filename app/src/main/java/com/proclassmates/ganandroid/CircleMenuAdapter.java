package com.proclassmates.ganandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @author simon
 * @name GanAndroid
 * @class name：com.proclassmates.ganandroid
 * @time 2019-11-04 19:30
 */
public class CircleMenuAdapter extends BaseAdapter {

    List<MenuItem> mMenuItems;

    public CircleMenuAdapter(List<MenuItem> list) {
        mMenuItems = list;

    }

    @Override
    public int getCount() {
        return mMenuItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.circle_menu_item, parent, false);
        initMenuItem(itemView, position);
        return itemView;
    }

    private void initMenuItem(View itemView, int position) {
        final MenuItem item = (MenuItem) getItem(position);
        ImageView iv = itemView.findViewById(R.id.id_circle_menu_item_image);
        TextView tv = itemView.findViewById(R.id.id_circle_menu_item_text);

        iv.setImageResource(item.imageId);

        tv.setText(item.title);

    }
}
