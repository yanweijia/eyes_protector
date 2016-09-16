package cn.yanweijia.eyes;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_jinshi_result extends Activity
{
	MyDatabaseHelper dbhelper;
	float you_xiaoshu , you_zhengshu , zuo_xiaoshu , zuo_zhengshu;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jinshi_result);

		//数据库dbhelper实例化
		dbhelper = new MyDatabaseHelper(this,"eyes.db",null,1);
		SQLiteDatabase db = dbhelper.getReadableDatabase();
		you_xiaoshu = getIntent().getFloatExtra("you_xiaoshu", 1.0f);
		you_zhengshu = getIntent().getFloatExtra("you_zhengshu", 5.0f);
		zuo_xiaoshu = getIntent().getFloatExtra("zuo_xiaoshu", 1.0f);
		zuo_zhengshu = getIntent().getFloatExtra("zuo_zhengshu", 5.0f);
		//加入记录到数据库并关闭数据库
		db.execSQL("INSERT INTO ShiLi(" +
				"you_zheng,you_xiao,zuo_zheng,zuo_xiao,time)VALUES("+
				"'"+you_zhengshu+"'"+","+"'"+you_xiaoshu+"'"+","+"'"+zuo_zhengshu+"'"+
				","+"'"+zuo_xiaoshu+"'"+","+"'"+dbhelper.getTime()+"'"+")"
				);
		db.close();
		TextView textview_you_xiaoshu = (TextView)findViewById(R.id.textview_jinshi_result_you_xiaoshu);
		TextView textview_you_zhengshu = (TextView)findViewById(R.id.textview_jinshi_result_you_zhengshu);
		TextView textview_zuo_xiaoshu = (TextView)findViewById(R.id.textview_jinshi_result_zuo_xiaoshu);
		TextView textview_zuo_zhengshu = (TextView)findViewById(R.id.textview_jinshi_result_zuo_zhengshu);
		Button btn_back = (Button)findViewById(R.id.button_jinshi_result_gotoback);
		Button btn_test_again = (Button)findViewById(R.id.button_jinshi_result_test_again);
		Button btn_back_image = (Button)findViewById(R.id.button_jinshi_result_back);
		textview_you_xiaoshu.setText("小数视力为:" + you_xiaoshu);
		textview_you_zhengshu.setText("整数视力为:" + you_zhengshu);
		textview_zuo_xiaoshu.setText("小数视力为:" + zuo_xiaoshu);
		textview_zuo_zhengshu.setText("整数视力为:" + zuo_zhengshu);
		btn_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		btn_test_again.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Activity_jinshi_result.this, Activity_jinshi.class);
				Activity_jinshi_result.this.startActivity(intent);
				finish();
			}
		});
		btn_back_image.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected void onResume() 
	{

		super.onResume();
	}
}
