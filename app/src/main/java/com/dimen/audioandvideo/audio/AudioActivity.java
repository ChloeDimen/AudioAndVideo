package com.dimen.audioandvideo.audio;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dimen.audioandvideo.R;
import com.dimen.audioandvideo.Record.AudioRecordManger;
import com.dimen.audioandvideo.Record.MediaRecorderManger;
import com.dimen.audioandvideo.Utils.AudioFileUtil;

import java.io.File;
import java.io.IOException;

/**
 * 录音的3种方式
 */
public class AudioActivity extends AppCompatActivity {
    private static int REQUEST_RECORDER = 100;
    private Uri mUri;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        mMediaPlayer = new MediaPlayer();
    }

    //第一种，调用系统的
    public void systemAudio(View view) {
        Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
        startActivityForResult(intent, REQUEST_RECORDER);

    }

    public void playAudio(Uri uri) {
        if (uri != null) {
            if (mMediaPlayer != null) {

                mMediaPlayer.reset();
                try {

                    mMediaPlayer.setDataSource(this, uri);

                    mMediaPlayer.prepare();

                    mMediaPlayer.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "没有成功创建Mediaplayer", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void playAudio(String path) {

            if (mMediaPlayer != null) {

                mMediaPlayer.reset();
                try {

                    mMediaPlayer.setDataSource(path);

                    mMediaPlayer.prepare();

                    mMediaPlayer.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }

    //第二种
    public void startAudioRecord(View view) {
        AudioRecordManger.getInstance().startRecordAndFile();
    }

    public void stopAudioRecord(View view) {
        AudioRecordManger.getInstance().stopRecordAndFile();
    }

    public void playAudioTrack(View view) {
        AudioRecordManger.getInstance().playRecord();
    }

    public void stopAudioTrack(View view) {
        AudioRecordManger.getInstance().stopPlayRecord();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RECORDER && resultCode == RESULT_OK) {
            mUri = data.getData();
            Log.e("sss", "onActivityResult: " + mUri.toString());
            playAudio(mUri);
        }

    }

    public void startMediaRecorder(View view) {
        MediaRecorderManger.getInstance().startRecordAndFile();
    }

    public void stopMediaRecorder(View view) {
        MediaRecorderManger.getInstance().stopRecordAndFile();
    }

    public void playMediaRecorder(View view) {
        playAudio(AudioFileUtil.getAMRFilePath());

    }
}
