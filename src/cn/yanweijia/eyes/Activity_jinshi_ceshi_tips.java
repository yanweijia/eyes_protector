package cn.yanweijia.eyes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_jinshi_ceshi_tips extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jinshi_ceshi_tips);
		final Button button_back = (Button) findViewById(R.id.button_jinshi_back);
		final Button button_gotoceshi = (Button) findViewById(R.id.button_jinshi_gotoceshi);
		button_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Activity_jinshi_ceshi_tips.this,
						Activity_jinshi.class);
				startActivity(intent);
				finish();
			}
		});
		button_gotoceshi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Activity_jinshi_ceshi_tips.this,
						Activity_jinshi_ceshi_wuyan_you.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
