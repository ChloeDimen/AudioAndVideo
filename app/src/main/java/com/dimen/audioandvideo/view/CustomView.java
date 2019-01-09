package com.dimen.audioandvideo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.dimen.audioandvideo.R;
import com.dimen.audioandvideo.Utils.BitmapUtil;

/**
 * @Author：JETIPC1 时间 :${DATA}
 * 项目名：AudioAndVideo
 * 包名：com.dimen.audioandvideo.view
 * 类名：
 * 简述：
 */

public class CustomView extends View {
    private Paint mPaint;
    private Bitmap mBitmap;

    public CustomView(Context context) {
        this(context,null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.timg);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect srcRect = new Rect(0, 0, mBitmap.getHeight(), mBitmap.getWidth());
        Rect destRect = BitmapUtil.getBitmapRect(mBitmap,CustomView.this); // 获取调整后的bitmap的显示位置
        canvas.drawBitmap(mBitmap, srcRect, destRect, mPaint);
    }
}
