package com.example.zuoye;

import com.example.myapp2.R;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Window;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class SecondActivity extends Activity{
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_second);
		//在Activity中获取设置的按钮和文本框
		Button bu=(Button)findViewById(R.id.button2);
		registerForContextMenu(bu);
		Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                + "/Pictures/QQ/hp.mp4");//手机中视频的路径

		final VideoView vv= (VideoView) this.findViewById(R.id.videoView1);
        MediaController mediaController = new MediaController(this);
        vv.setMediaController(mediaController);//增加视频控制器
        vv.setVideoURI(uri);
        // mVideoView1.requestFocus();
        vv.start();
	}
	
}
