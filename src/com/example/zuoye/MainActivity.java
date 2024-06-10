package com.example.zuoye;

import android.os.Bundle;

import com.example.myapp2.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnClickListener {
	private Button loginbutton, registbutton;
	private EditText st_id, st_password;
	private CheckBox checkBox;
	private TextView serviceText;

	private IntentFilter intentFilter;

	private MyBroadcastReceiver myBroadcastReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		intentFilter = new IntentFilter();

		intentFilter.addAction("refuse");
		intentFilter.addAction("agree");
		myBroadcastReceiver = new MyBroadcastReceiver();

		registerReceiver(myBroadcastReceiver, intentFilter);

		registbutton = (Button) findViewById(R.id.registbutton);
		loginbutton = (Button) findViewById(R.id.loginbutton);
		st_id = (EditText) findViewById(R.id.st_id);
		checkBox = (CheckBox) findViewById(R.id.checkbox);
		serviceText = (TextView) findViewById(R.id.serviceText);
		st_password = (EditText) findViewById(R.id.st_password);
		registbutton.setOnClickListener(this);
		loginbutton.setOnClickListener(this);
		serviceText.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("NewApi")
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.registbutton:
			Intent intent = new Intent(this, RegistActivity.class);
			startActivity(intent);
			break;
		case R.id.loginbutton:
			if (st_id.getText().toString().isEmpty()) {
				Toast.makeText(this, "请输入学号", Toast.LENGTH_SHORT).show();
			} else if (st_password.getText().toString().isEmpty()) {
				Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
			} else {
				if (!checkBox.isChecked()) {
					Toast.makeText(this, "请先阅读服务协议并同意", Toast.LENGTH_SHORT)
							.show();
				} else {
					Intent intent1 = new Intent(this, MainPageActivity.class);
					startActivity(intent1);
					// finish();
					Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
				}

			}
			break;
		case R.id.serviceText:
			// Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
			Intent intent1 = new Intent(this, AgreementActivity.class);
			startActivity(intent1);
			break;
		}
	}

	public class MyBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String st = intent.getAction();
			if (st.equals("refuse")) {
				checkBox.setChecked(false);
			} else {
				checkBox.setChecked(true);
			}

		}

	}

}
