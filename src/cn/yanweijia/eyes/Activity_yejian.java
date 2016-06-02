package cn.yanweijia.eyes;



import com.baidu.mobstat.StatService;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Activity_yejian extends Activity{
	SeekBar seekBar=null,seekbar_yejian= null;
	CheckBox checkbox = null;
	WindowManager wm = null;
	View tv = null;
	WindowManager.LayoutParams params = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yejian);
		StatService.onPageStart(this,"夜间模式");
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
		
		
		
		button_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
                            if (0<= tmpInt && tmpInt < 255) 
                            {
                                    lp.screenBrightness = tmpInt + 1;//TODO:亮度调节的问题
                            }
                            getWindow().setAttributes(lp);
                    }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub
                    // put awesomeness here
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub
                    // and here too
            }
        });
		
		seekbar_yejian.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if(checkbox.isChecked()==true)
				{
					wm.removeView(tv);
					params.alpha = seekBar.getProgress();
					wm.addView(tv, params);
				}
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if(checkbox.isChecked()==true)
				{
					wm.removeView(tv);
					params.alpha = seekBar.getProgress();
					wm.addView(tv, params);
				}
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				
			}
		});
		
		checkbox.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(checkbox.isChecked()==true)
				{
					params.alpha = seekbar_yejian.getProgress();
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
		// TODO Auto-generated method stub
		StatService.onPageEnd(this,"夜间模式");
		super.onResume();
	}
}
