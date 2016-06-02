package cn.yanweijia.eyes;

import java.io.IOException;

import com.baidu.mobstat.StatService;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Activity_bizhi_liulan extends Activity
{
	ImageView imageview = null;
	String url = "";
	@Override  
    public void onCreate(Bundle savedInstanceState) 
	{  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_bizhi_liulan);
        StatService.onPageStart(this,"浏览壁纸页面");
        imageview = (ImageView)findViewById(R.id.imageView_liulan);
        String str = getIntent().getStringExtra("url");
        url = "www.yanweijia.cn/eyes/wallpaper/" + str;
        if(str.equalsIgnoreCase("wallpaper_1.jpg"))
        	imageview.setImageResource(R.drawable.wallpaper_01);
        else if (str.equalsIgnoreCase("wallpaper_2.jpg"))
        	imageview.setImageResource(R.drawable.wallpaper_02);
        else if (str.equalsIgnoreCase("wallpaper_3.jpg"))
        	imageview.setImageResource(R.drawable.wallpaper_03);
        else if (str.equalsIgnoreCase("wallpaper_4.jpg"))
        	imageview.setImageResource(R.drawable.wallpaper_04);
        else if (str.equalsIgnoreCase("wallpaper_5.jpg"))
        	imageview.setImageResource(R.drawable.wallpaper_05);
        else 
        	imageview.setImageResource(R.drawable.wallpaper_06);
        imageview.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v) 
			{
				new AlertDialog.Builder(Activity_bizhi_liulan.this)   
				.setTitle("护眼壁纸")  
				.setMessage("确定要设置这幅图片为桌面么？")  
				.setPositiveButton("确认", new DialogInterface.OnClickListener() 
				{
					
					@Override
					public void onClick(DialogInterface dialog, int which) 
					{
						BitmapDrawable bmpDraw=(BitmapDrawable)imageview.getDrawable();
				        Bitmap bmp=bmpDraw.getBitmap();
				        try
				        {
				            setWallpaper(bmp);
				            Toast.makeText(Activity_bizhi_liulan.this, "设置成功!", Toast.LENGTH_LONG).show();
				        }
				        catch(IOException e) 
				        {
				            e.printStackTrace();
				            Toast.makeText(Activity_bizhi_liulan.this, "设置失败!", Toast.LENGTH_LONG).show();
				        }
					}
				})
				.setNegativeButton("取消", null)  
				.show(); 
				
				
			}
		});


	}
	protected void onResume() 
	{
		// TODO Auto-generated method stub
		StatService.onPageEnd(this,"浏览壁纸页面");
		super.onResume();
	}
}