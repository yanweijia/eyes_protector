package cn.yanweijia.eyes;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.adsmogo.adapters.AdsMogoCustomEventPlatformEnum;
import com.adsmogo.adview.AdsMogoLayout;
import com.adsmogo.controller.listener.AdsMogoListener;
import com.baidu.mobstat.StatService;

public class Activity_yejian extends Activity{
    MyDatabaseHelper dbhelper;//数据库操作,用来判断是否显示广告 
    SQLiteDatabase db;
	SeekBar seekBar=null,seekbar_yejian= null;
	CheckBox checkbox = null;
	WindowManager wm = null;
	View tv = null;
	WindowManager.LayoutParams params = null;

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//TODO:销毁adsMogo的资源并清空线程
		AdsMogoLayout.clear();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yejian);

		//百度统计
		StatService.setSessionTimeOut(30);  //两次启动应用30s视为第二次启动
		StatService.setLogSenderDelayed(0); //崩溃后延迟0秒发送崩溃日志


		Button button_back = (Button)findViewById(R.id.button_yejian_back);
		seekBar = (SeekBar) findViewById(R.id.seekbar_yejian_liangdu);
		seekbar_yejian = (SeekBar)findViewById(R.id.seekbar_yejianmoshi);
		checkbox = (CheckBox)findViewById(R.id.checkbox_yejian);
		//夜间模式用的WindowManager
		
		wm = (WindowManager)getApplicationContext().getSystemService(WINDOW_SERVICE);
		params = new WindowManager.LayoutParams();
		params.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
		params.width = WindowManager.LayoutParams.FILL_PARENT;
		params.height = WindowManager.LayoutParams.FILL_PARENT;
		params.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL|LayoutParams.FLAG_NOT_FOCUSABLE;
		params.alpha = 10;
		tv = new View(Activity_yejian.this);
		tv.setBackgroundColor(Color.argb(10, 10, 10, 10));



		dbhelper = new MyDatabaseHelper(this,"eyes.db",null,1);
		db = dbhelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from GuangGao", null);
		if(cursor.getCount() == 0)
		{
			//TODO:如果数据库中没有关于取消广告的记载则显示广告
			LinearLayout adLayout = (LinearLayout)findViewById(R.id.linearlayout_yejian_ad);
			AdsMogoLayout adsMogoLayoutCode = new AdsMogoLayout(Activity_yejian.this, "635d7536a3414841b21af99dab32a4ce");
			//设置广告出现的位置(悬浮于底部)
			adsMogoLayoutCode.setAdsMogoListener(new AdsMogoListener() {
				@Override
				public void onInitFinish() {}
				@Override
				public void onRequestAd(String s) {}
				@Override
				public void onRealClickAd() {}
				@Override
				public void onReceiveAd(ViewGroup viewGroup, String s) {}
				@Override
				public void onFailedReceiveAd() {}
				@Override
				public void onClickAd(String s) {}
				@Override
				public boolean onCloseAd() {return false;}
				@Override
				public void onCloseMogoDialog() {}
				@Override
				public Class getCustomEvemtPlatformAdapterClass(AdsMogoCustomEventPlatformEnum adsMogoCustomEventPlatformEnum) {return null;}
			});
			adLayout.addView(adsMogoLayoutCode);
		}
		db.close();
		
		button_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		//设置初始值
		seekBar.setProgress((int) (android.provider.Settings.System.getInt(
                getContentResolver(),
                android.provider.Settings.System.SCREEN_BRIGHTNESS, 255) ));
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() 
        {

            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) 
            {
                    if (fromUser)
                    {
                            Integer tmpInt = seekBar.getProgress();
                            System.out.println(tmpInt);
                            // 51 (seek scale) * 5 = 255 (max brightness)
                            // Old way
                            android.provider.Settings.System.putInt(getContentResolver(),
                            		android.provider.Settings.System.SCREEN_BRIGHTNESS,tmpInt); // 0-255
                            tmpInt = Settings.System.getInt(getContentResolver(),
                                            Settings.System.SCREEN_BRIGHTNESS, -1);
                            WindowManager.LayoutParams lp = getWindow().getAttributes();
                            // lp.screenBrightness = 1.0f;
                            // Float tmpFloat = (float)tmpInt / 255;
                            if (0< tmpInt && tmpInt < 255) 
                            {
                                    lp.screenBrightness = tmpInt + 1;//TODO:亮度调节的问题
                            }
                            getWindow().setAttributes(lp);
                    }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
		
		seekbar_yejian.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				if(checkbox.isChecked()==true)
				{
					wm.removeView(tv);
					params.alpha = seekBar.getProgress();
					if (params.alpha < 30)
						params.alpha = 30;
					wm.addView(tv, params);


				}
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

			}
		});
		
		checkbox.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(checkbox.isChecked()==true)
				{
					params.alpha = seekbar_yejian.getProgress();
					if (params.alpha < 30)
						params.alpha = 30;
					wm.addView(tv, params);
				}
				else
				{
					wm.removeView(tv);
				}
			}
		});
		
	}
	protected void onResume() 
	{

		super.onResume();
		//百度统计
		StatService.onResume(this);
	}
	@Override
	protected void onPause() {
		super.onPause();
		//百度统计_统计页面
		StatService.onPause(this);
	}
}
