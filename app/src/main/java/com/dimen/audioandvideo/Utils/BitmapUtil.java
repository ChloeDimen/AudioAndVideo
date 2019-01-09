package com.dimen.audioandvideo.Utils;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import com.dimen.audioandvideo.view.CustomView;

/**
 * @Author：JETIPC1 时间 :${DATA}
 * 项目名：AudioAndVideo
 * 包名：com.dimen.audioandvideo.Utils
 * 类名：
 * 简述：
 */

public class BitmapUtil {
    /**
     * 图片的尺寸和屏幕的尺寸不一样，需要把图片调整居中
     **/
    public static  Rect getBitmapRect(Bitmap bimap, View parent) {
        int bimapHeight = bimap.getHeight();
        int bimapWidth = bimap.getWidth();
        int viewWidth = parent.getWidth();
        int viewHeight = parent.getHeight();
        float bimapRatio = (float) bimapWidth / (float) bimapHeight; // 宽高比
        float screenRatio = (float) viewWidth / (float) viewHeight;
        int factWidth;
        int factHeight;
        int x1, y1, x2, y2;
        if (bimapRatio > screenRatio) {
            factWidth = viewWidth;
            factHeight = (int)(factWidth / bimapRatio);
            x1 = 0;
            y1 = (viewHeight - factHeight) / 2;
        } else if (bimapRatio < screenRatio) {
            factHeight = viewHeight;
            factWidth = (int)(factHeight * bimapRatio);
            x1 = (viewWidth - factWidth) / 2;
            y1 = 0;
        } else {
            factWidth = bimapWidth;
            factHeight = bimapHeight;
            x1 = 0;
            y1 = 0;
        }
        x2 = x1 + factWidth;
        y2 = y1 + factHeight;
        return new Rect(x1, y1, x2, y2);
    }
}
