package cn.yanweijia.eyes;

import java.io.IOException;

import com.baidu.mobstat.StatService;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.media.MediaPlayer; 

public class Activity_yanbaojiancao extends Activity {

	private MediaPlayer mp;  
	//声明一个变量判断是否为暂停,默认为false  
	private boolean isPaused = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yanbaojiancao);
		StatService.onPageStart(this,"眼保健操");
		final ImageView imageview_play = (ImageView)findViewById(R.id.image_paly_state);
		final Button button_back = (Button)findViewById(R.id.button_yanbaojiancao_back);
	    //创建MediaPlayer对象,将raw文件夹下的lovefool.mp3  
	    mp = MediaPlayer.create(this,R.raw.yanbao);  
	    isPaused = true;
	    imageview_play.setBackgroundResource(R.drawable.play);
		imageview_play.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(isPaused == true)
				{
					isPaused = false;
				     try {
						mp.prepare();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
				     mp.start(); 
				     imageview_play.setBackgroundResource(R.drawable.pause);
				}else{
					isPaused = true;
					mp.pause();
					imageview_play.setBackgroundResource(R.drawable.play);
				}
			}
		});
		button_back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mp.stop();
				finish();
			}
		});
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		mp.stop();
	}
	protected void onResume() 
	{
		// TODO Auto-generated method stub
		StatService.onPageEnd(this,"眼保健操");
		super.onResume();
	}
}
