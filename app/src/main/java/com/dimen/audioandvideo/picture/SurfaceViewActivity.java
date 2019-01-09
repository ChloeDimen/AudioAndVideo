package com.dimen.audioandvideo.picture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.dimen.audioandvideo.R;
import com.dimen.audioandvideo.Utils.BitmapUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SurfaceViewActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    SurfaceView suf_view;
    private ExecutorService mThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
        suf_view = findViewById(R.id.suf_view);
        // 创建一个只有一个线程的线程池，其实用Thread也可以
        mThread = new ThreadPoolExecutor(1, 1, 2000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>());
        // 添加SurfaceHolder.callback，在回调中可以绘制
        suf_view.getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // 执行绘制
        mThread.execute(new DrawRunnable());
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (!mThread.isShutdown()) {
            mThread.shutdown();
        }
    }
    private class DrawRunnable implements Runnable {
        @Override
        public void run() {
            Bitmap bimap = BitmapFactory.decodeResource(SurfaceViewActivity.this.getResources(),
                    R.drawable.timg);
            SurfaceHolder surfaceHolder = suf_view.getHolder();
            Canvas canvas = surfaceHolder.lockCanvas(); // 获取画布
            Paint paint = new Paint();
            Rect srcRect = new Rect(0, 0, bimap.getHeight(), bimap.getWidth());
            Rect destRect = BitmapUtil.getBitmapRect(bimap,suf_view);
            canvas.drawBitmap(bimap, srcRect, destRect, paint);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }


}
