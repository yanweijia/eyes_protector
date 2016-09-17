package cn.yanweijia.eyes;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Activity_yejian extends Activity{
    MyDatabaseHelper dbhelper;//数据库操作,用来判断是否显示广告 
    SQLiteDatabase db;
	SeekBar seekBar=null,seekbar_yejian= null;
	CheckBox checkbox = null;
	WindowManager wm = null;
	View tv = null;
	WindowManager.LayoutParams params = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yejian);
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
			//如果数据库中没有关于取消广告的记载则显示广告
			LinearLayout adLayout = (LinearLayout)findViewById(R.id.linearlayout_yejian_ad);

			//adLayout.addView(null);
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
	}
}
