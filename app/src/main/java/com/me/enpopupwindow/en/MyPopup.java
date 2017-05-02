package com.me.enpopupwindow.en;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.me.enpopupwindow.R;

/**
 * 作者: 51hs_android
 * 时间: 2017/5/2
 * 简介:
 */

public class MyPopup extends EnPopouWindow {

    public MyPopup(Context context) {
        super(context);
        View popView = LayoutInflater.from(context).inflate(R.layout.popup_select, null, false);
        setContentView(popView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
    }

}
