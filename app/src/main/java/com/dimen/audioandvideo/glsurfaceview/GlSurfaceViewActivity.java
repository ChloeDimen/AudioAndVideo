package com.dimen.audioandvideo.glsurfaceview;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dimen.audioandvideo.R;

public class GlSurfaceViewActivity extends AppCompatActivity {
private GLSurfaceView gLsurfaceview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gl_surface_view);
        gLsurfaceview = findViewById(R.id.gLsurfaceview);
        gLsurfaceview.setEGLContextClientVersion(2);
       // gLsurfaceview.setRenderer(new Trianglerenderer());//三角形
        //gLsurfaceview.setRenderer(new SquareRenderer());//正方形
        //gLsurfaceview.setRenderer(new CircleRenderer());//圆
        gLsurfaceview.setRenderer(new ImageRenderer(this));//图片
        gLsurfaceview.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    protected void onResume() {
        super.onResume(); gLsurfaceview.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gLsurfaceview.onPause();
    }
}
