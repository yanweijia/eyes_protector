package cn.yanweijia.eyes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_jinshi_ceshi_wuyan_you extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jinshi_ceshi_wuyan);
		final Button button_back = (Button) findViewById(R.id.button_jinshi_ceshi_wuyan_back);
		final Button button_gotoceshi = (Button) findViewById(R.id.button_jinshi_ceshi_wuyan_gotoceshi);
		button_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Activity_jinshi_ceshi_wuyan_you.this,
						Activity_jinshi_ceshi_tips.class);
				startActivity(intent);
				finish();
			}
		});
		button_gotoceshi.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent_gotoceshi = new Intent();
				intent_gotoceshi.setClass(Activity_jinshi_ceshi_wuyan_you.this,
						Activity_jinshi_ceshi_you.class);
				startActivity(intent_gotoceshi);
				finish();
			}
		});
	}
}
