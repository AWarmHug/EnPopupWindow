package com.me.enpopupwindow.en;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.PopupWindow;


/**
 * 作者: 51hs_android
 * 时间: 2017/3/2
 * 简介:
 */

public class EnPopouWindow extends PopupWindow {
    private static final String TAG = "MyPopouWindow";

    ViewGroup vOut;
    private View v;



    public EnPopouWindow(Context context) {
        super(context);
        set(context);

    }

    private void set(Context context) {
        setFocusable(true);
        setOutsideTouchable(true);
        //设置popupwindow 背景颜色
//        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setAnimationStyle(android.R.style.Animation_Dialog);
        addBackGround(context);
    }

    public void addBackGround(Context context) {
        v = new View(context);
        v.setBackgroundColor(Color.BLACK);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        v.setLayoutParams(lp);
    }


    public EnPopouWindow(int width, int height) {
        this(null, width, height);
    }


    public EnPopouWindow(View contentView, int width, int height) {
        super(contentView, width, height);
        set(contentView.getContext());
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);

        vOut = findSuitableParent(parent);
        vOut.addView(v);
        anim(0f, 0.5f, 300);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        super.showAsDropDown(anchor, xoff, yoff, gravity);
        anim(0f, 0.5f, 300);
        vOut = findSuitableParent(anchor);
        vOut.addView(v);
    }

    @Override
    public void dismiss() {
        super.dismiss();
//        anim(0.5f, 0f, 300);
        vOut.removeView(v);

    }

    public void switchPopup() {
        if (isShowing()) {
            dismiss();
        }
    }


    private void anim(float start, float end, int time) {
        Log.d(TAG, "anim: ");
        ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        animator.setInterpolator(new FastOutLinearInInterpolator());
        animator.setDuration(time);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.setAlpha((float) animation.getAnimatedValue());

            }
        });
        animator.start();
    }




    /**
     * copy from SnackBar 源码
     * @param view
     * @return
     */
    private ViewGroup findSuitableParent(View view) {
        ViewGroup fallback = null;
        do {
            if (view instanceof FrameLayout) {
                if (view.getId() == android.R.id.content) {
                    // If we've hit the decor content view, then we didn't find a CoL in the
                    // hierarchy, so use it.
                    return (ViewGroup) view;
                } else {
                    // It's not the content view but we'll use it as our fallback
                    fallback = (ViewGroup) view;
                }
            }

            if (view != null) {
                // Else, we will loop and crawl up the view hierarchy and try to find a parent
                final ViewParent parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
        } while (view != null);

        // If we reach here then we didn't find a CoL or a suitable content view so we'll fallback
        return fallback;
    }

}
