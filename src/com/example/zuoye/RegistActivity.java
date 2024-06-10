package com.example.zuoye;

import com.example.myapp2.R;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")

public class RegistActivity extends Activity implements OnClickListener {
	private Button regist_bt;
	private EditText userid, pwd1, pwd2;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regist_activity);
		findId();
		regist_bt.setOnClickListener(this);
		Button button=(Button)findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent(RegistActivity.this,HeadActivity.class);
				startActivityForResult(intent,0x11);
			}
		});

	}
	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==0x11&&resultCode==0x11){
			Bundle bundle=data.getExtras();
			int imgId=bundle.getInt("imgId");
			ImageView iv=(ImageView)findViewById(R.id.imageView1);
			iv.setImageResource(imgId);
		}
	}
	
	private void findId() {
		regist_bt = (Button) findViewById(R.id.regist_bt);
		userid = (EditText) findViewById(R.id.userid);
		pwd1 = (EditText) findViewById(R.id.pwd1);
		pwd2 = (EditText) findViewById(R.id.pwd2);
	}

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.regist_bt:
			// Log.e("detectidcount","p :"+databaseManager.detectPwd("qqq"));
			if (userid.getText().toString().isEmpty()) {
				Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
			} else if (pwd1.getText().toString().isEmpty()
					|| pwd2.getText().toString().isEmpty()) {
				Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
			} else if (!pwd1.getText().toString()
					.equals(pwd2.getText().toString())) {
				Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
				finish();
			}
			break;
			

		}
	}
	
}

