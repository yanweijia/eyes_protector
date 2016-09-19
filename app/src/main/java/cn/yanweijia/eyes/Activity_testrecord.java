package cn.yanweijia.eyes;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.baidu.mobstat.StatService;

public class Activity_testrecord extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record);
		//百度统计
		StatService.setSessionTimeOut(30);  //两次启动应用30s视为第二次启动
		StatService.setLogSenderDelayed(0); //崩溃后延迟0秒发送崩溃日志

		RelativeLayout relativelayout_shilirecord = (RelativeLayout)findViewById(R.id.relativelayout_shili_record);
		RelativeLayout relativelayout_semangrecord = (RelativeLayout)findViewById(R.id.relativelayout_semang_record);
		Button btn_back = (Button)findViewById(R.id.button_record_back);
		btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		relativelayout_shilirecord.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setClass(Activity_testrecord.this,Activity_testrecord_shili.class);
				Activity_testrecord.this.startActivity(intent);
			}
		});
		relativelayout_semangrecord.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setClass(Activity_testrecord.this,Activity_testrecord_semang.class);
				Activity_testrecord.this.startActivity(intent);
			}
		});
	}
	protected void onResume()
	{
		super.onResume();
		//百度统计_统计页面
		StatService.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		//百度统计_统计页面
		StatService.onPause(this);
	}
}
