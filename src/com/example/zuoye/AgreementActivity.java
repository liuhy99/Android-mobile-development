package com.example.zuoye;

import com.example.myapp2.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AgreementActivity extends Activity {
	private Button agree_bt, reject_bt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agree_activity);
		agree_bt = (Button) findViewById(R.id.agree_bt);
		reject_bt = (Button) findViewById(R.id.reject_bt);
		agree_bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("agree");
				sendBroadcast(intent);
				finish();
			}
		});
		reject_bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("refuse");
				sendBroadcast(intent);
				finish();
			}
		});
	}
}
