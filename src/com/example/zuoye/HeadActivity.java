package com.example.zuoye;

import com.example.myapp2.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class HeadActivity extends Activity{
	public int[] imgId =new int[] { R.drawable.four, R.drawable.chuf, R.drawable.book,
			R.drawable.qiuchang, R.drawable.azi, R.drawable.wu,R.drawable.wu,R.drawable.wu,R.drawable.wu };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.head);
		GridView gridview=(GridView)findViewById(R.id.gridView1);
		
		BaseAdapter adapter=new BaseAdapter(){

			@Override
			public int getCount() {
				// TODO 自动生成的方法存根
				return imgId.length;
			}

			@Override
			public Object getItem(int position) {
				// TODO 自动生成的方法存根
				return position;
			}

			@Override
			public long getItemId(int position) {
				// TODO 自动生成的方法存根
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO 自动生成的方法存根
				ImageView imageview;
				if(convertView==null){
					imageview=new ImageView(HeadActivity.this);
					imageview.setAdjustViewBounds(true);
					imageview.setMaxWidth(158);
					imageview.setMaxHeight(150);
					imageview.setPadding(5, 5, 5, 5);
				}else{
					imageview=(ImageView)convertView;
				}
				imageview.setImageResource(imgId[position]);
				return imageview;
				
			}
		};
        gridview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO 自动生成的方法存根
				Intent intent=getIntent();
				Bundle bundle=new Bundle();
				bundle.putInt("imgId", imgId[position]);
				intent.putExtras(bundle);
				setResult(0x11,intent);
				finish();
			}
			
		});
        
	}
}

