package cn.yanweijia.eyes;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_SeMang_result extends Activity 
{
	MyDatabaseHelper dbhelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_semang_ceshi_result);
		//数据库
		dbhelper = new MyDatabaseHelper(this, "eyes.db", null, 1);
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		
		String score = this.getIntent().getStringExtra("score");
		TextView textview_result = (TextView) findViewById(R.id.text_semang_state);
		TextView textview_zhonglei = (TextView) findViewById(R.id.text_semang_zhonglei);
		TextView textview_score = (TextView) findViewById(R.id.text_semang_result_point);
		final Button button_back_image = (Button) findViewById(R.id.button_semang_ceshi_result_back);
		final Button button_back = (Button) findViewById(R.id.button_semang_result_gotoback);
		final Button button_backtoceshi = (Button) findViewById(R.id.button_semang_result_gotoceshi);
		button_back_image.setOnClickListener(new View.OnClickListener() 
		{

			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				finish();
			}
		});
		button_back.setOnClickListener(new View.OnClickListener() 
		{

			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				finish();

			}
		});
		button_backtoceshi.setOnClickListener(new View.OnClickListener() 
		{

			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Activity_SeMang_result.this,
						Activity_SeMang_ceshi.class);
				startActivity(intent);
				finish();
			}
		});
		textview_score.setText(score);
		if (Integer.parseInt(score) >= 8) 
		{
			textview_result.setText("正常");
			textview_zhonglei.setText("您的视力检测结果为正常");
		} else if (Integer.parseInt(score) <= 2) 
		{
			textview_result.setText("异常");
			textview_zhonglei.setText("你可能是全色盲");
		}else
		{
			textview_result.setText("异常");
			textview_zhonglei.setText("你可能有黄，红，绿色盲等。");
		}
		//记录到数据库并关闭数据库
		db.execSQL("INSERT INTO SeMang(score,result,description,time)VALUES(" +
				"'"+score+"'"+","+"'"+textview_result.getText()+"'"+","+"'"+textview_zhonglei.getText()+"'"+
				","+"'"+dbhelper.getTime()+"')");
		db.close();
	}
	protected void onResume() 
	{
		super.onResume();
	}
}
