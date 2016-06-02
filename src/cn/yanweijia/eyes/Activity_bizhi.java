package cn.yanweijia.eyes;

import com.baidu.mobstat.StatService;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Activity_bizhi extends Activity{
	Intent intent = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bizhi);
		StatService.onPageStart(this,"壁纸");
		Button btn_back = (Button)findViewById(R.id.button_bizhi_back);
		LinearLayout linearlayout_1 = (LinearLayout)findViewById(R.id.linearlayout_bizhi_1);
		LinearLayout linearlayout_2 = (LinearLayout)findViewById(R.id.linearlayout_bizhi_2);
		LinearLayout linearlayout_3 = (LinearLayout)findViewById(R.id.linearlayout_bizhi_3);
		LinearLayout linearlayout_4 = (LinearLayout)findViewById(R.id.linearlayout_bizhi_4);
		LinearLayout linearlayout_5 = (LinearLayout)findViewById(R.id.linearlayout_bizhi_5);
		LinearLayout linearlayout_6 = (LinearLayout)findViewById(R.id.linearlayout_bizhi_6);
		TextView textview_download = (TextView)findViewById(R.id.textview_bizhi_download);
		intent = new Intent();
		intent.setClass(Activity_bizhi.this, Activity_bizhi_liulan.class);
		linearlayout_1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.putExtra("url", "wallpaper_1.jpg");
				Activity_bizhi.this.startActivity(intent);
			}
		});
		linearlayout_2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.putExtra("url", "wallpaper_2.jpg");
				Activity_bizhi.this.startActivity(intent);
			}
		});
		linearlayout_3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.putExtra("url", "wallpaper_3.jpg");
				Activity_bizhi.this.startActivity(intent);
			}
		});
		linearlayout_4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.putExtra("url", "wallpaper_4.jpg");
				Activity_bizhi.this.startActivity(intent);
			}
		});
		linearlayout_5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.putExtra("url", "wallpaper_5.jpg");
				Activity_bizhi.this.startActivity(intent);
			}
		});
		linearlayout_6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent.putExtra("url", "wallpaper_6.jpg");
				Activity_bizhi.this.startActivity(intent);
			}
		});
		
		textview_download.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    Intent intent_web= new Intent();       
			    intent_web.setAction("android.intent.action.VIEW");   
			    Uri content_url = Uri.parse("http://pan.baidu.com/share/link?shareid=3657083064&uk=51584230");  
			    intent_web.setData(content_url); 
			    Activity_bizhi.this.startActivity(intent_web);
			}
		});
		
		btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		
	}
	protected void onResume() 
	{
		// TODO Auto-generated method stub
		StatService.onPageEnd(this,"壁纸");
		super.onResume();
	}
}
