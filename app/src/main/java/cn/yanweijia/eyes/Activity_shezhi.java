package cn.yanweijia.eyes;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Activity_shezhi extends Activity
{
	MyDatabaseHelper dbhelper;
	SQLiteDatabase db;
	//TextView text_ad,text_ad_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shezhi);
		//百度统计的代码
		Button btn_back = (Button)findViewById(R.id.button_shezhi_back);
		RelativeLayout relativelayout_about = (RelativeLayout)findViewById(R.id.rlayout_about);
		RelativeLayout relativelayout_BackToUs = (RelativeLayout)findViewById(R.id.rlayout_BackToUs);
		RelativeLayout relativelayout_UpDate = (RelativeLayout)findViewById(R.id.rlayout_update);
		//添加广告view的linearlayout
		LinearLayout linearlayout_ad = (LinearLayout)findViewById(R.id.linearlayout_shezhi_adview);
		
		//TODO:添加广告View的代码
		linearlayout_ad.addView(null);

		
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
				builder.setMessage("软件每次启动后都会自动检测更新.不用手动检测!");
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
	
	


	
	
	protected void onResume() 
	{
		super.onResume();
	}
}
