package com.dimen.audioandvideo.camera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dimen.audioandvideo.R;
import com.dimen.audioandvideo.picture.SurfaceViewActivity;

public class CameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
    }

    public void SurfaceView(View view) {
        Intent intent = new Intent(this, CameraSurfaceViewActivity.class);
        startActivity(intent);
    }

    public void TextureView(View view) {
        Intent intent = new Intent(this,  CameraTextureViewActivity.class);
        startActivity(intent);
    }
}
