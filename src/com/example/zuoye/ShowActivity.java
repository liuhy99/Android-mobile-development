package com.example.zuoye;

import java.util.ArrayList;
import java.util.List;

import com.example.myapp2.R;
import com.example.myapp2.Bean.ViewInfo;
import com.example.myapp2.MsgManager.ListAdapter;
import com.example.myapp2.RatingView.RatingView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

public class ShowActivity extends Activity {
	private int mess = 0;
	private EditText search_edt;
	private Button search_bt, back_bt, score_bt;
	private ListView info_listView;
	private ListAdapter listAdapter;
	public Handler handler;
	private List<ViewInfo> viewInfoList;
	private int[] icon1 = { R.drawable.gelan, R.drawable.hafu,
			R.drawable.sly, R.drawable.v_cbc_04, R.drawable.v_cbc_05 };
	private int[] icon2 = { R.drawable.v_fh_01, R.drawable.v_fh_02,
			R.drawable.v_fh_03, R.drawable.v_fh_04, R.drawable.v_fh_05 };
	private int[] icon3 = { R.drawable.v_gg_01, R.drawable.v_gg_02,
			R.drawable.v_gg_03, R.drawable.v_gg_04, R.drawable.v_gg_05 };
	private int[] icon4 = { R.drawable.v_hhl_01, R.drawable.v_hhl_02,
			R.drawable.v_hhl_03, R.drawable.v_hhl_04, R.drawable.v_hhl_05 };
	private int[] icon5 = { R.drawable.v_hs_01, R.drawable.v_hs_02,
			R.drawable.v_hs_03, R.drawable.v_hs_04, R.drawable.v_hs_05 };
	private int[] icon6 = { R.drawable.v_xh_01, R.drawable.v_xh_02,
			R.drawable.v_xh_03, R.drawable.v_xh_04, R.drawable.v_xh_05 };
	private PopupWindow popWindow;
	private View window_content_view = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_layout);
		search_edt = (EditText) findViewById(R.id.search_edt);
		search_bt = (Button) findViewById(R.id.search_bt);
		back_bt = (Button) findViewById(R.id.back_bt);
		score_bt = (Button) findViewById(R.id.score_bt);
		viewInfoList = new ArrayList<ViewInfo>();
		viewInfoList.clear();
		info_listView = (ListView) findViewById(R.id.info_listView);

		Intent intent = getIntent();
		mess = intent.getIntExtra("pos", 0);
		switch (mess) {
		case 0:
			for (int a = 0; a < icon1.length; a++) {
				ViewInfo gf1 = new ViewInfo();
				if (a == 0) {
					gf1.setPd(getResources().getString(R.string.produce1));
				}

				gf1.setView(icon1[a]);
				viewInfoList.add(gf1);
			}
			break;
		case 1:
			for (int a = 0; a < icon2.length; a++) {
				ViewInfo gf1 = new ViewInfo();
				if (a == 0) {
					gf1.setPd(getResources().getString(R.string.produce2));
				}

				gf1.setView(icon2[a]);
				viewInfoList.add(gf1);
			}
			break;
		case 2:
			for (int a = 0; a < icon3.length; a++) {
				ViewInfo gf1 = new ViewInfo();
				if (a == 0) {
					gf1.setPd(getResources().getString(R.string.produce3));
				}

				gf1.setView(icon3[a]);
				viewInfoList.add(gf1);
			}
			break;
		case 3:
			for (int a = 0; a < icon4.length; a++) {
				ViewInfo gf1 = new ViewInfo();
				if (a == 0) {
					gf1.setPd(getResources().getString(R.string.produce4));
				}

				gf1.setView(icon4[a]);
				viewInfoList.add(gf1);
			}
			break;
		case 4:
			for (int a = 0; a < icon5.length; a++) {
				ViewInfo gf1 = new ViewInfo();
				if (a == 0) {
					gf1.setPd(getResources().getString(R.string.produce5));
				}

				gf1.setView(icon5[a]);
				viewInfoList.add(gf1);
			}
			break;
		case 5:
			for (int a = 0; a < icon6.length; a++) {
				ViewInfo gf1 = new ViewInfo();
				if (a == 0) {
					gf1.setPd(getResources().getString(R.string.produce6));
				}

				gf1.setView(icon6[a]);
				viewInfoList.add(gf1);
			}
			break;
		}

		listAdapter = new ListAdapter(ShowActivity.this, viewInfoList);
		info_listView.setAdapter(listAdapter);
		listInitSelection();
		search_bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (listAdapter != null) {
					listAdapter.setKeywords(search_edt.getText().toString());
					listAdapter.notifyDataSetChanged();
					listInitSelection();
				}
			}
		});
		back_bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		//initPopWindow();
		score_bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				backgroundAlpha(ShowActivity.this, 0.4f);
				popWindow.showAtLocation(search_edt, Gravity.CENTER, 0, 0);
				// popWindow.showAsDropDown(search_edt);
			}
		});

	}

	private void listInitSelection() {
		info_listView.post(new Runnable() {
			@Override
			public void run() {
				info_listView.setSelection(0);
			}
		});
	}

	private void initPopWindow() {
		final RatingView ratingView;
		Button button;
		window_content_view = LayoutInflater.from(this).inflate(
				R.layout.rating_window, null);
		popWindow = new PopupWindow(window_content_view,
				ViewGroup.LayoutParams.MATCH_PARENT, 800, true);
		popWindow.setContentView(window_content_view);
		window_content_view.setBackgroundColor(getResources().getColor(
				R.color.colorM));
		popWindow.setFocusable(true);
		popWindow.setBackgroundDrawable(new ColorDrawable(Color.argb(
				Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT,
				Color.TRANSPARENT)));
		ratingView = (RatingView) window_content_view
				.findViewById(R.id.ratingView);
		button = (Button) window_content_view.findViewById(R.id.score_bt);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(ShowActivity.this,
						"获得评分：" + ratingView.getRating() + "分",
						Toast.LENGTH_SHORT).show();
				popWindow.dismiss();
			}
		});
		popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
			@Override
			public void onDismiss() {
				backgroundAlpha(ShowActivity.this, 1.0f);
			}
		});

	}

	public void backgroundAlpha(Activity context, float bgAlpha) {
		WindowManager.LayoutParams lp = context.getWindow().getAttributes();
		lp.alpha = bgAlpha;
		context.getWindow()
				.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		context.getWindow().setAttributes(lp);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (search_edt != null) {
			search_bt.post(new Runnable() {
				@Override
				public void run() {
					search_edt.getText().clear();
				}
			});

		}

	}
}
