package cn.yanweijia.eyes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_jinshi extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jinshi);
		Button button_gotoceshi = (Button) findViewById(R.id.button_jinshi_gotoceshi);
		Button button_back = (Button) findViewById(R.id.button_jinshi_back);

		button_back.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				finish();
			}
		});

		button_gotoceshi.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent();
				intent.setClass(Activity_jinshi.this,
						Activity_jinshi_ceshi_tips.class);
				startActivity(intent);
				finish();
			}
		});
	}
}
