package cn.yanweijia.eyes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_sanguang_ceshi extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sanguang_ceshi);
		Button button_back = (Button) findViewById(R.id.button_sanguang_ceshi_back);
		button_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	protected void onResume() 
	{
		// TODO Auto-generated method stub
		super.onResume();
	}
}
