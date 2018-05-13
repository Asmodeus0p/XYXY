package com.jiyun.asmodeus.xyxy.view.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class CustomViewPager extends ViewPager {

	public CustomViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public CustomViewPager(Context context) {
		super(context);

	}

	private float curnX;

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			curnX = event.getX();
		}

		if (event.getAction() == MotionEvent.ACTION_MOVE) {

			if (event.getX() - curnX > 0.1 || curnX - event.getX() > 0.1) {

				return false;
			}

		}
		return true;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_MOVE) {

			if (event.getX() - curnX > 10 || curnX - event.getX() > 10) {

				return false;
			}

		}
		return false;
	}
}
