package com.dimen.audioandvideo.picture;

import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.dimen.audioandvideo.R;

public class ImageViewActivity extends AppCompatActivity {

    private ImageView iv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        iv_show = findViewById(R.id.iv_show);

        iv_show.setImageDrawable(getResources().getDrawable(R.drawable.timg));
       // iv_show.setImageResource(R.drawable.timg);
       // iv_show.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.timg));//图片绘制占得内存跟高
    }
}
