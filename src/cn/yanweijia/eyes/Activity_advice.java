package cn.yanweijia.eyes;


import com.baidu.mobstat.StatService;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Activity_advice extends Activity 
{
	MyDatabaseHelper dbhelper;
	String text = "";
	EditText text_jianyi , text_lianxifangshi , text_shoujixinghao;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advice);
		StatService.onPageStart(this,"建议页面");
		dbhelper = new MyDatabaseHelper(this,"eyes.db",null,1);
		text_jianyi = (EditText)findViewById(R.id.edittext_advice_jianyi);
		text_lianxifangshi = (EditText)findViewById(R.id.edittext_advice_lianxifangshi);
		text_shoujixinghao = (EditText)findViewById(R.id.edittext_advice_shoujixinghao);
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
				/*
				//判断填写的信息是否完整,若不完整则提示用户填写完整的信息
				if(text_jianyi.getText().toString().equals("") || text_lianxifangshi.getText().toString().equals("")
						|| text_shoujixinghao.getText().toString().equals(""))
				{
					Toast.makeText(Activity_advice.this, "请填写完整的信息,便于我们能够收到您的建议反馈!", Toast.LENGTH_SHORT).show();
					return;
				}
				*/
				//反馈的内容
				text = "　　建议内容：" + text_jianyi.getText().toString()
						+"　　联系方式：" + text_lianxifangshi.getText().toString()
						+"　　手机型号：" + text_shoujixinghao.getText().toString()
						+"　　时间："+ dbhelper.getTime().toString();
						
				//利用smtp邮件协议用happyboyywj@gmail.com邮箱发送到happyboyywj@163.com邮箱
				try {   
                    GMailSender sender = new GMailSender("happyboyywj@gmail.com", "zheshimima");
                    sender.sendMail("This is advice",//head
                            text, //body  
                            "happyboyywj@gmail.com",
                            "happyboyywj@163.com"); 
                    Toast.makeText(Activity_advice.this, "感谢您的反馈,我们已经收到您的建议,我们将会继续努力的!", Toast.LENGTH_SHORT).show();
                    Activity_advice.this.finish();
				} catch (Exception e) {   
                    e.printStackTrace();
                    Toast.makeText(Activity_advice.this, "Failed to send advice!", Toast.LENGTH_SHORT).show();
                } 
                
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
		// TODO Auto-generated method stub
		StatService.onPageEnd(this,"建议页面");
		super.onResume();
	}
}
