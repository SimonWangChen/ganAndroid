package com.proclassmates.ganandroid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author simon
 * @name GanAndroid
 * @class name：com.proclassmates.ganandroid
 * @time 2019-11-03 17:27
 */
public class CircleMenuLayout extends ViewGroup {

    // 圆形直径
    private int mRadius;
    // 该容器内 child item 的 默认尺寸
    private static final float RADIO_DEFAULT_CHILD_DIMENSION = 1 / 4f;
    // 该容器的内边距， 无视 padding 属性， 如需使用边距请用该变量
    private static final float RADIO_PADDING_LAYOUT = 1 / 12f;

    private float mPadding;

    // 布局时的开始角度
    private double mStartAngle = 0;

    // 菜单项的文本
    private String[] mItemTexts;

    // 菜单项的图标
    private int[] mItemImags;

    // 菜单的个数
    private int mMenuItemCount;

    // 餐单布局资源 id
    private int mMenuItemLayoutId = R.layout.circle_menu_item;

    // MenuItem 的点击事件接口
    private AdapterView.OnItemClickListener mOnMenuItemClickListener;


    /**
     * @param context
     * @param attrs
     */
    public CircleMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //无视 padding
        setPadding(0, 0, 0, 0);
    }

    public void setMenuItemIconsAndTexts(int[] images, String[] texts) {
        if (images == null && texts == null) {
            throw new IllegalArgumentException("菜单文本和图片至少设置其一");
        }

        mItemImags = images;
        mItemTexts = texts;
        //初始化 mMenuCount
        mMenuItemCount = mItemImags == null ? mItemTexts.length : mItemImags.length;

        if (images != null && texts != null) {
            mMenuItemCount = Math.min(images.length, texts.length);
        }

        //构建菜单项

        buildMenuItems();
    }

    //构建菜单项
    private void buildMenuItems() {
        // 根据用户设置的参数， 初始化 menu item
        for (int i = 0; i < mMenuItemCount; i++) {
            View itemView = inflateMenuItem(i);

            //初始化菜单项
            initMenuItem(itemView, i);
            //添加 view 到容器中
            addView(itemView);
        }
    }

    private View inflateMenuItem(final int i) {
        LayoutInflater mInflater = LayoutInflater.from(getContext());

        View itemView = mInflater.inflate(mMenuItemLayoutId, this, false);
        itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnMenuItemClickListener != null) {
                    mOnMenuItemClickListener.onItemClick(view, i);
                }
            }
        });

        return itemView;
    }

    private void initMenuItem(View itemView, int i) {
        ImageView iv = itemView.findViewById(R.id.id_circle_menu_item_image);

        TextView tv = itemView.findViewById(R.id.id_circle_menu_item_text);

        iv.setImageResource(mItemImags[i]);

        tv.setText(mItemTexts[i]);


    }

    // 设置 MenuItem 的布局文件
    public void setMenuItemLayoutId(int mMenuItemLayoutId) {
        this.mMenuItemLayoutId = mMenuItemLayoutId;
    }

    // 设置 MenuItem 的点击事件接口
    public void setOnItemClickListenr(AdapterView.OnItemClickListener listener) {
        this.mOnMenuItemClickListener = listener;

    }

    // 设置布局的宽高， 并测量 menu item 宽高

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureMyself(widthMeasureSpec, heightMeasureSpec);
        measureChildViews();
    }

    private void measureMyself(int widthMeasureSpec, int heightMeasureSpec) {
        int resWidth = 0;

        int resHeight = 0;
        // 根据传入的参数， 分别获取测量模式和测量值
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 如果宽或高的模式不是精确值
        if (widthMode != MeasureSpec.EXACTLY || heightMode != MeasureSpec.EXACTLY) {
            //主要设置为背景图的高度
            resWidth = getSuggestedMinimumWidth();
            // 如果没有设置背景图片，则设置为屏幕宽高的默认值
            resWidth = resWidth == 0 ? getDefaultWidth() : resWidth;

            //主要设置为背景图的高度
            resHeight = getSuggestedMinimumHeight();
            // 如果没有设置背景图片，则设置为屏幕宽高的默认值
            resHeight = resHeight == 0 ? getDefaultWidth() : resHeight;
        } else {
            // 如果都设置为精确值， 则直接取小值
            resWidth = resHeight = Math.min(width, height);

        }


    }

    private void measureChildViews() {
        // 获取半径
        mRadius = Math.max(getMeasuredWidth(), getMeasuredHeight());

        final int count = getChildCount();

        int childSize = (int) (mRadius * RADIO_DEFAULT_CHILD_DIMENSION);

        int childMode = MeasureSpec.EXACTLY;

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }

            int makeMeasureSpac = -1;
            makeMeasureSpac = MeasureSpec.makeMeasureSpec(childSize, childMode);
            child.measure(makeMeasureSpac, makeMeasureSpac);
        }
        mPadding = RADIO_PADDING_LAYOUT * mRadius;
    }


    //


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int childCount = getChildCount();

        int left, top;

        int itemWidth = (int) (mRadius * RADIO_DEFAULT_CHILD_DIMENSION);

        float angleDelay = 360 / childCount;

        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);

            if (child.getVisibility() == GONE) {
                continue;
            }

            mStartAngle %= 360;

            float distanceFromCenter = mRadius / 2f - itemWidth / 2 - mPadding;

            left = mRadius / 2 + (int) Math.round(distanceFromCenter * Math.cos(Math.toRadians(mStartAngle)) - 1 / 2f * itemWidth);

            top = mRadius / 2 + (int) Math.round(distanceFromCenter
                    * Math.sin(Math.toRadians(mStartAngle) - 1 / 2f * itemWidth));

            child.layout(left, top, left + itemWidth, top + itemWidth);

            mStartAngle += angleDelay;
        }
    }
}
