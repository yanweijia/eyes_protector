package cn.yanweijia.eyes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_jinshi_ceshi_wuyan_zuo extends Activity {
	float  you_xiaoshu , you_zhengshu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jinshi_ceshi_wuyan);
		you_xiaoshu = getIntent().getFloatExtra("you_xiaoshu", 1.0f);
		you_zhengshu = getIntent().getFloatExtra("you_zhengshu", 5.0f);
		final Button button_back = (Button) findViewById(R.id.button_jinshi_ceshi_wuyan_back);
		final Button button_gotoceshi = (Button) findViewById(R.id.button_jinshi_ceshi_wuyan_gotoceshi);
		ImageView imageview = (ImageView)findViewById(R.id.imageview_eye);
		imageview.setBackgroundResource(R.drawable.ceshileft);
		TextView textview = (TextView)findViewById(R.id.text_eye);
		textview.setText("1，捂住你的右眼，开始测试左眼视力");
		
		
		button_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Activity_jinshi_ceshi_wuyan_zuo.this,
						Activity_jinshi_ceshi_you.class);
				startActivity(intent);
				finish();
			}
		});
		button_gotoceshi.setText("测试左眼");
		button_gotoceshi.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent_gotoceshi = new Intent();
				intent_gotoceshi.setClass(Activity_jinshi_ceshi_wuyan_zuo.this,
						Activity_jinshi_ceshi_zuo.class);
				intent_gotoceshi.putExtra("you_xiaoshu", you_xiaoshu);
				intent_gotoceshi.putExtra("you_zhengshu",you_zhengshu);
				startActivity(intent_gotoceshi);
				finish();
			}
		});
	}
}
