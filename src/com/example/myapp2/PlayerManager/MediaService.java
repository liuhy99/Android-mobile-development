package com.example.myapp2.PlayerManager;

import com.example.myapp2.R;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MediaService extends Service {
    private MediaPlayer mediaPlayer;

    public class MediaBinder extends Binder {
        public void setLooping(boolean b) {
            mediaPlayer.setLooping(b);
        }

        public void start() {
            mediaPlayer.start();
        }

        public long getDuration() {
            return mediaPlayer.getDuration();
        }

        public long getCurrentPosition() {
            return mediaPlayer.getCurrentPosition();
        }

        public void seekTo(int position) {
            mediaPlayer.seekTo(position);
        }

        public MediaPlayer getPlayer() {
            return mediaPlayer;
        }

        public void initPlayer(Context mContext) {
                if (mediaPlayer == null) {
                	mediaPlayer = MediaPlayer.create(mContext,R.raw.ms);
                	mediaPlayer.start();
                	mediaPlayer.setVolume(1.0f, 1.0f);
                    mediaPlayer.setLooping(true);
                   
                }
          
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return new MediaBinder();
    }


    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        mediaPlayer = null;
        super.onDestroy();
    }
}
