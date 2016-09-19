package cn.yanweijia.eyes;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.view.View;

import com.baidu.mobstat.StatService;

public class Activity_SeMang extends Activity {

	@Override
	protected void onResume() {
		super.onResume();
		//百度统计
		StatService.onResume(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_semang);
		//百度统计
		StatService.setSessionTimeOut(30);  //两次启动应用30s视为第二次启动
		StatService.setLogSenderDelayed(0); //崩溃后延迟0秒发送崩溃日志

		final Button button = (Button) findViewById(R.id.button_kaishiceshi);
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Activity_SeMang.this,
						Activity_SeMang_ceshi.class);
				startActivity(intent);
				finish();
			}
		});
		final Button btn_back = (Button) findViewById(R.id.button_semangceshi_back);
		btn_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
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

}
