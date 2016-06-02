package cn.yanweijia.eyes;

import com.baidu.mobstat.StatService;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_about extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		StatService.onPageStart(this,"关于页面");
		Button btn_back = (Button)findViewById(R.id.button_about_back);
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
		StatService.onPageEnd(this,"关于页面");
		super.onResume();
	}
}
