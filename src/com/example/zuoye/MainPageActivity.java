package com.example.zuoye;

import com.bumptech.glide.Glide;
import com.example.myapp2.R;
import com.example.myapp2.PlayerManager.MediaService;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainPageActivity extends Activity implements View.OnClickListener,
		ServiceConnection {
	private MediaService.MediaBinder controller;
	private SeekBar seekbar_p, seekbar_v;
	private ImageView volumeImg, playImg;
	private boolean seekbar_p_onUser = false;
	private TextView d_text, c_text;
	private int[] icon = { R.drawable.four, R.drawable.chuf, R.drawable.book,
			R.drawable.qiuchang, R.drawable.azi, R.drawable.wu };
	private ImageView v_img1, v_img2, v_img3, v_img4, v_img5, v_img6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_p_activity);
		Intent intent = new Intent(this, MediaService.class);
		Button button=(Button)findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://a00762y.demo.guanwangyun.com/message.html"));
				startActivity(intent);
			}
		});
		Button button2=(Button)findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent(MainPageActivity.this,SecondActivity.class);
				startActivity(intent);
			}
			
		});
		bindService(intent, this, BIND_AUTO_CREATE);
		seekbar_p = (SeekBar) findViewById(R.id.seekbar_p);
		seekbar_v = (SeekBar) findViewById(R.id.seekbar_v);
		volumeImg = (ImageView) findViewById(R.id.volumeImg);

		playImg = (ImageView) findViewById(R.id.playImg);
		d_text = (TextView) findViewById(R.id.d_text);
		c_text = (TextView) findViewById(R.id.c_text);

		v_img1 = (ImageView) findViewById(R.id.v_img1);
		v_img2 = (ImageView) findViewById(R.id.v_img2);
		v_img3 = (ImageView) findViewById(R.id.v_img3);
		v_img4 = (ImageView) findViewById(R.id.v_img4);
		v_img5 = (ImageView) findViewById(R.id.v_img5);
		v_img6 = (ImageView) findViewById(R.id.v_img6);
		ImageView[] imgs = { v_img1, v_img2, v_img3, v_img4, v_img5, v_img6 };
		for (int a = 0; a < imgs.length; a++) {
			final int finalA = a;
			imgs[finalA].setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MainPageActivity.this,
							ShowActivity.class);
					intent.putExtra("pos", finalA);
					startActivity(intent);
				}
			});
			Glide.with(this).load(icon[a]).into(imgs[a]);
		}

		seekbar_v.setProgress(10);
		seekbar_v
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						if (progress == 0) {
							volumeImg
									.setImageResource(R.drawable.img_volume_mute);
						} else {
							volumeImg.setImageResource(R.drawable.img_volume);
						}
						if (controller.getPlayer() != null) {
							 float a = progress * 0.1f;
			                    controller.getPlayer().setVolume(a, a);
						}
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {

					}

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {

					}
				});
		seekbar_p.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() != 1) {
					seekbar_p_onUser = true;
				} else {
					seekbar_p_onUser = false;
				}
				return false;
			}
		});

		seekbar_p
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						Message message = Message.obtain();
						Bundle bundle = new Bundle();
						bundle.putInt("progress", progress);
						message.setData(bundle);
						message.arg1 = 2;
						if (handler != null)
							handler.sendMessage(message);
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {

					}

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						controller.seekTo(seekBar.getProgress());
					}
				});

		volumeImg.setOnClickListener(this);
		playImg.setOnClickListener(this);
	}
	

	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.arg1) {
			case 1:
				Bundle data = msg.getData();
				int duration = data.getInt("duration");
				if (d_text != null) {
					String time = formatTime(duration);
					d_text.setText(time);
				}
				int currentPosition = data.getInt("currentPosition");
				if (seekbar_p != null) {
					seekbar_p.setMax(duration);
					if (!seekbar_p_onUser)
						seekbar_p.setProgress(currentPosition);
				}
				if (c_text != null && !seekbar_p_onUser) {
					String time = formatTime(currentPosition);
					c_text.setText(time);
					// Log.e("aaaaaaaaaaaaaaaaaaaaa", "time :" + time);
				}
				break;
			case 2:
				if (c_text != null && seekbar_p_onUser) {
					Bundle data_progress = msg.getData();
					int progress = data_progress.getInt("progress");
					String time = formatTime(progress);
					c_text.setText(time);
				}
				break;
			}

		}
	};

	public static String formatTime(int time) {
		if (time / 1000 % 60 < 10) {
			return time / 1000 / 60 + ":0" + time / 1000 % 60;
		} else {
			return time / 1000 / 60 + ":" + time / 1000 % 60;
		}
	}

	private class ProgressThread implements Runnable {

		@Override
		public void run() {
			// Log.e("ProgressThread", "s");
			while (controller != null && controller.getPlayer() != null) {
				// Log.e("ProgressThread", "a!n 1:" + controller + " 2:" +
				// controller.getPlayer());
				try {
					if (controller.getPlayer().isPlaying()) {
						int currentPosition = (int) controller.getPlayer()
								.getCurrentPosition();
						int duration = (int) controller.getPlayer()
								.getDuration();
						// Log.e("mijkplayer", "progress :" + currentPosition +
						// "   " + duration);
						Message message = Message.obtain();
						Bundle bundle = new Bundle();
						bundle.putInt("duration", duration);
						bundle.putInt("currentPosition", currentPosition);
						message.setData(bundle);
						message.arg1 = 1;
						if (handler != null)
							handler.sendMessage(message);

					}
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.volumeImg:

			break;
		case R.id.playImg:
			/*
			 * Intent intent = new Intent(this, AdministratorActivity.class);
			 * startActivity(intent);
			 */
			if (controller.getPlayer() != null) {
				if (controller.getPlayer().isPlaying()) {
					controller.getPlayer().pause();
					playImg.setImageResource(R.drawable.img_play);
				} else if (!controller.getPlayer().isPlaying()) {
					controller.getPlayer().start();
					playImg.setImageResource(R.drawable.img_pause);
				}
			}
			break;
		}
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		controller = ((MediaService.MediaBinder) service);
		controller.initPlayer(MainPageActivity.this);

		Thread thread = new Thread(new ProgressThread());
		thread.start();
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		controller = null;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		unbindService(this);
	}
}
