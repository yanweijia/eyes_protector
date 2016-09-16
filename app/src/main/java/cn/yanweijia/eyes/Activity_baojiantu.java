package cn.yanweijia.eyes;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_baojiantu extends Activity 
{
	
    @Override
	protected void onCreate(Bundle savedInstanceState) 
    {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_baojiantu);
		Button btn_back = (Button)findViewById(R.id.button_baojiantu_back);
		btn_back.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				finish();
			}
		});
    }
    protected void onResume() 
	{
		super.onResume();
	}

}
