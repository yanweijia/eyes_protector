package cn.yanweijia.eyes;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.view.View;

public class Activity_SeMang extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_semang);
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

}
