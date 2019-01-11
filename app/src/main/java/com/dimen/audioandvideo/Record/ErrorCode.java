package com.dimen.audioandvideo.Record;

import android.content.Context;

import com.dimen.audioandvideo.R;

/**
 * @Author：JETIPC1 时间 :${DATA}
 * 项目名：AudioAndVideo
 * 包名：com.dimen.audioandvideo.Record
 * 类名：
 * 简述：
 */

public class ErrorCode {
    public final static int SUCCESS = 1000;
    public final static int E_NOSDCARD = 1001;
    public final static int E_STATE_RECODING = 1002;
    public final static int E_UNKOWN = 1003;


    public static String getErrorInfo(Context vContext, int vType)
    {
        switch(vType)
        {
            case SUCCESS:
                return "success";
            case E_NOSDCARD:
                return vContext.getResources().getString(R.string.error_no_sdcard);
            case E_STATE_RECODING:
                return vContext.getResources().getString(R.string.error_state_record);
            case E_UNKOWN:
            default:
                return vContext.getResources().getString(R.string.error_unknown);

        }
    }
}
