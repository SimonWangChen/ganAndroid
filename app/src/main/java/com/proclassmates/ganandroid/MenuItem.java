package com.proclassmates.ganandroid;

/**
 * @author simon
 * @name GanAndroid
 * @class name：com.proclassmates.ganandroid
 * @time 2019-11-04 19:29
 */

/**
 * 实体类来存储图标和文本
 */
public class MenuItem {
    public int imageId;
    public String title;

    public MenuItem(String title, int resId) {
        this.title = title;
        imageId = resId;
    }
}