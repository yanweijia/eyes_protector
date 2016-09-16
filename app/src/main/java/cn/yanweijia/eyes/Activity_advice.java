package cn.yanweijia.eyes;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Activity_advice extends Activity 
{
	MyDatabaseHelper dbhelper;
	SQLiteDatabase db;
	String text = "";
	String backMessage;
	EditText text_jianyi , text_lianxifangshi;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advice);

		dbhelper = new MyDatabaseHelper(this,"eyes.db",null,1);
		text_jianyi = (EditText)findViewById(R.id.edittext_advice_jianyi);
		text_lianxifangshi = (EditText)findViewById(R.id.edittext_advice_lianxifangshi);
		Button button_back = (Button)findViewById(R.id.button_advice_back);
		Button button_submit = (Button)findViewById(R.id.button_advice_submit);
		button_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Activity_advice.this.finish();
			}
		});
		button_submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//判断有无网络链接,若无,则退出反馈Activity
				if(!isNetworkConnected(Activity_advice.this))
				{
					Toast.makeText(Activity_advice.this, "无网络链接,无法反馈!", Toast.LENGTH_SHORT).show();
					Activity_advice.this.finish();
					return;
				}
				
				//判断填写的信息是否完整,若不完整则提示用户填写完整的信息
				if(text_jianyi.getText().toString().equals("") || text_lianxifangshi.getText().toString().equals(""))
				{
					Toast.makeText(Activity_advice.this, "请填写完整的信息,便于我们能够收到您的建议反馈!", Toast.LENGTH_SHORT).show();
					return;
				}
				//反馈的内容
				text = "建议内容：" + text_jianyi.getText().toString()
						+"\n联系方式：" + text_lianxifangshi.getText().toString()
						+"\n手机制造商："+Build.PRODUCT+"\n安卓版本："+Build.MODEL
						+"时间："+ dbhelper.getTime().toString();
				try {
					text = URLEncoder.encode(text.toString(),"gb2312");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				
				//TODO:用户反馈
//				// FeedBack feedback = new FeedBack("FeedBack_eyes", text);
//				try {
//					//TODO:生成用户反馈
//					//backMessage = feedback.UpLoad();
//				} catch (IOException e) {
//					backMessage = e.getMessage();
//				}
				Toast.makeText(Activity_advice.this, "感谢您的反馈,我们已经收到您的建议(作为奖励,已经为您取消掉了广告),我们将会继续努力的!", Toast.LENGTH_SHORT).show();
				db = dbhelper.getReadableDatabase();
				db.execSQL("INSERT INTO GuangGao(guanggao)VALUES(1)");
				db.close();
                
			}
		});
	}
	public boolean isNetworkConnected(Context context) 
	{
		//判断有没有网络
		if (context != null) 
		{
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context
		.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) 
			{
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}
	protected void onResume() 
	{

		super.onResume();
	}
}
