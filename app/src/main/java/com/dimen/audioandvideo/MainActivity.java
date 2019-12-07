package com.dimen.audioandvideo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dimen.audioandvideo.audio.AudioActivity;
import com.dimen.audioandvideo.camera.CameraActivity;
import com.dimen.audioandvideo.glsurfaceview.GlSurfaceViewActivity;
import com.dimen.audioandvideo.media.MediaActivity;
import com.dimen.audioandvideo.picture.CustomImageActivity;
import com.dimen.audioandvideo.picture.ImageViewActivity;
import com.dimen.audioandvideo.picture.SurfaceViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    public void imageView(View view) {
        startActivity(ImageViewActivity.class);
    }

    public void surfaceView(View view) {
        startActivity(SurfaceViewActivity.class);
    }

    public void customView(View view) {
        startActivity(CustomImageActivity.class);
    }


    public void audio(View view) {
        startActivity(AudioActivity.class);
    }


    public void camera(View view) {

        startActivity(CameraActivity.class);
    }

    public void Media(View view) {
        startActivity(MediaActivity.class);
    }

    public void glSurfaceView(View view) {
        startActivity(GlSurfaceViewActivity.class);
    }
}
