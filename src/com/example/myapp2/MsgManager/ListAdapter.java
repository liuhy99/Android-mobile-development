package com.example.myapp2.MsgManager;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp2.R;
import com.example.myapp2.Bean.ViewInfo;

public class ListAdapter extends BaseAdapter {
	 private Context mContext;

	    private List<ViewInfo> list;
	    private String keywords = "";

	    public ListAdapter(Context context, List<ViewInfo> infos) {
	        mContext = context;
	        this.list = infos;
	    }


    @Override
    public int getCount() {
    	return list == null ? 0 : list.size()+1;
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null) {
            viewHolder = new ViewHolder();
            convertView=LayoutInflater.from(mContext).inflate(R.layout.listview_item,null);
            viewHolder.txt1 = (TextView) convertView.findViewById(R.id.title_txt);
            viewHolder.txt2 = (TextView) convertView.findViewById(R.id.produce_txt);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.viewImg);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        
        if (position == 0) {
        	viewHolder.txt2.setText(matcherSearchText(list.get(position).getPd(), keywords));
        	viewHolder.txt1.setText("æ∞µ„ΩÈ…‹£∫");
        	viewHolder.txt1.setVisibility(View.VISIBLE);
            viewHolder.txt2.setVisibility(View.VISIBLE);
            viewHolder.img.setVisibility(View.INVISIBLE);
        } else {
        	viewHolder.txt1.setVisibility(View.INVISIBLE);
        	viewHolder.txt2.setVisibility(View.INVISIBLE);
        	viewHolder.img.setVisibility(View.VISIBLE);
        	viewHolder.img.setImageResource(list.get(position - 1).getView());
        }
        
        return convertView;
    }
    public static SpannableString matcherSearchText(String text, String keyword) {
        SpannableString ss = new SpannableString(text);
        Pattern pattern = Pattern.compile(keyword);
        Matcher matcher = pattern.matcher(ss);

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(Color.parseColor("#FF0000")), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return ss;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
class ViewHolder{
	TextView txt1, txt2;
    ImageView img;
}

