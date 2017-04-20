package com.example.hoomsun.testrxlife;

import android.graphics.Color;

/**
 * Created by hoomsun on 2017/4/19.
 */

public enum TextType {
    TYPE1(1, Color.BLUE, "未开始"),

    TYPE2(2, Color.BLUE, "进行中"),

    TYPE3(3, Color.BLUE, "已完成");

    int type;
    int color;
    String des;

    TextType(int type, int color, String des) {
        this.type = type;
        this.color = color;
        this.des = des;

    }

    public static TextType getTextType(int type) {
        for (TextType mType : values()) {
            if (type == mType.type) {
                return mType;
            }
        }
        return TYPE1;

    }
}
