package cn.yanweijia.eyes;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.adsmogo.adapters.AdsMogoCustomEventPlatformEnum;
import com.adsmogo.adview.AdsMogoLayout;
import com.adsmogo.controller.listener.AdsMogoListener;
import com.baidu.mobstat.StatService;

public class Activity_shezhi extends Activity
{
	MyDatabaseHelper dbhelper;
	SQLiteDatabase db;

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//TODO:销毁adsMogo的资源并清空线程
		AdsMogoLayout.clear();
	}

	//TextView text_ad,text_ad_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shezhi);
		//百度统计
		StatService.setSessionTimeOut(30);  //两次启动应用30s视为第二次启动
		StatService.setLogSenderDelayed(0); //崩溃后延迟0秒发送崩溃日志

		Button btn_back = (Button)findViewById(R.id.button_shezhi_back);
		RelativeLayout relativelayout_about = (RelativeLayout)findViewById(R.id.rlayout_about);
		RelativeLayout relativelayout_BackToUs = (RelativeLayout)findViewById(R.id.rlayout_BackToUs);
		RelativeLayout relativelayout_UpDate = (RelativeLayout)findViewById(R.id.rlayout_update);
		//添加广告view的linearlayout
		LinearLayout linearlayout_ad = (LinearLayout)findViewById(R.id.linearlayout_shezhi_adview);
		
		//TODO:添加广告View的代码
		//linearlayout_ad.addView(null);
		AdsMogoLayout adsMogoLayoutCode = new AdsMogoLayout(Activity_shezhi.this, "635d7536a3414841b21af99dab32a4ce");
		//设置广告出现的位置(悬浮于底部)
		adsMogoLayoutCode.setAdsMogoListener(new AdsMogoListener() {
			@Override
			public void onInitFinish() {}
			@Override
			public void onRequestAd(String s) {}
			@Override
			public void onRealClickAd() {}
			@Override
			public void onReceiveAd(ViewGroup viewGroup, String s) {}
			@Override
			public void onFailedReceiveAd() {}
			@Override
			public void onClickAd(String s) {}
			@Override
			public boolean onCloseAd() {return false;}
			@Override
			public void onCloseMogoDialog() {}
			@Override
			public Class getCustomEvemtPlatformAdapterClass(AdsMogoCustomEventPlatformEnum adsMogoCustomEventPlatformEnum) {return null;}
		});
		linearlayout_ad.addView(adsMogoLayoutCode);
		
		relativelayout_about.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent();
				intent.setClass(Activity_shezhi.this, Activity_about.class);
				Activity_shezhi.this.startActivity(intent);
			}
		});
		relativelayout_BackToUs.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent();
				intent.setClass(Activity_shezhi.this, Activity_advice.class);
				Activity_shezhi.this.startActivity(intent);
			}
		});
		relativelayout_UpDate.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				AlertDialog.Builder builder = new AlertDialog.Builder(Activity_shezhi.this);
				builder.setTitle("软件更新");
				builder.setMessage("软件重新启动即可自动检测更新,无需手动更新!");
				builder.setPositiveButton("确定",null);
				builder.create().show();
			}
		});
		btn_back.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				finish();
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		//百度统计_统计页面
		StatService.onPause(this);
	}


	
	
	protected void onResume() 
	{
		super.onResume();
		//百度统计
		StatService.onResume(this);
	}
}
