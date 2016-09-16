package cn.yanweijia.eyes;


import cn.yanweijia.eyes.ShiLi;
import cn.yanweijia.eyes.Mm2Px;
import java.util.Random;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Activity_jinshi_ceshi_you extends Activity 
{
	int direction = 0;//方向0为上 ,1为下,2为左,3为右
	int mistake_count = 0;//统计错误次数,超过两次(包含)视力到此为止!
	LayoutParams para;
	ShiLi shili = new ShiLi();//用来获取视力整数和小数
	Random rand = new Random();//取随机数,主要用来取方向用在random()方法中
	Mm2Px mm2px = new Mm2Px();//定义毫米mm转换像素px的class
	ImageView imageview;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jinshi_ceshi);
		//新建ImageView控件
		imageview = (ImageView) findViewById(R.id.image_jinshi_ceshi_pic);
		final Button button_shang = (Button) findViewById(R.id.button_jinshi_ceshi_shang);
		final Button button_xia = (Button) findViewById(R.id.button_jinshi_ceshi_xia);
		final Button button_zuo = (Button) findViewById(R.id.button_jinshi_ceshi_zuo);
		final Button button_you = (Button) findViewById(R.id.button_jinshi_ceshi_you);
		final Button button_back = (Button)findViewById(R.id.button_jinshi_ceshi_back);
		final Button button_kanbuqing = (Button)findViewById(R.id.button_jinshi_ceshi_kanbuqing);
		

		// 将图片居中拉伸显示
		imageview.setScaleType(ImageView.ScaleType.FIT_XY);
		mm2px.setRate(imageview.getLayoutParams().height / 5.76f);//设置mm2px的缩放率
 		
		//将图片缩放为标准5.76mm
		setImageViewSize(5.76f);
		
		random();
		


		button_shang.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				// 如果观测到上,则
				if(direction == 0)
				{
					mistake_count = 0;
					if(shili.nextLower() == false)
					{
						test_result();
					}
					setImageViewSize(shili.getMm());
					//取随机数并设置imageview的图片资源
					random();
				}
				else
				{
					mistake_count++;
					if(mistake_count >= 3)
					{
						test_result();
					}
					setImageViewSize(shili.getMm());
					//取随机数并设置imageview的图片资源
					random();
				}
			}
		});
		button_xia.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				if(direction == 1)
				{
					mistake_count = 0;
					if(shili.nextLower() == false)
					{
						test_result();
					}
					setImageViewSize(shili.getMm());
					//取随机数并设置imageview的图片资源
					random();
				}
				else
				{
					mistake_count++;
					if(mistake_count >= 3)
					{
						test_result();
					}
					setImageViewSize(shili.getMm());
					//取随机数并设置imageview的图片资源
					random();
				}
			}
		});
		button_zuo.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				if(direction == 2)
				{
					mistake_count = 0;
					if(shili.nextLower() == false)
					{
						test_result();
					}
					setImageViewSize(shili.getMm());
					//取随机数并设置imageview的图片资源
					random();
				}
				else
				{
					mistake_count++;
					if(mistake_count >= 3)
					{
						test_result();
					}
					setImageViewSize(shili.getMm());
					//取随机数并设置imageview的图片资源
					random();
				}
			}
		});
		button_you.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				if(direction == 3)
				{
					mistake_count = 0;
					if(shili.nextLower() == false)
					{
						test_result();
					}
					setImageViewSize(shili.getMm());
					//取随机数并设置imageview的图片资源
					random();
				}
				else
				{
					mistake_count++;
					if(mistake_count >= 3)
					{
						test_result();
					}
					setImageViewSize(shili.getMm());
					//取随机数并设置imageview的图片资源
					random();
				}
			}
		});

		button_back.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent intent =new Intent();
				intent.setClass(Activity_jinshi_ceshi_you.this, Activity_jinshi_ceshi_wuyan_you.class);
				startActivity(intent);
				finish();
			}
		});
		button_kanbuqing.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mistake_count++;
				if(mistake_count >= 3)
				{
					test_result();
				}
				setImageViewSize(shili.getMm());
				//取随机数并设置imageview的图片资源
				random();
			}
		});

	}
	public void random()
	{
		//取随机数并设置imageview的图片资源
				direction = (rand.nextInt() % 4);
				switch(direction)
				{
				case 0:imageview.setImageResource(R.drawable.jinshi_ceshi_shang);break;
				case 1:imageview.setImageResource(R.drawable.jinshi_ceshi_xia);break;
				case 2:imageview.setImageResource(R.drawable.jinshi_ceshi_zuo);break;
				case 3:imageview.setImageResource(R.drawable.jinshi_ceshi_you);break;
				}
	}
	public void setImageViewSize(float size_mm)
	{
		para = imageview.getLayoutParams();
		para.height = para.width = mm2px.getPx(size_mm);
		imageview.setLayoutParams(para);
	}
	public void test_result()
	{
		Toast.makeText(Activity_jinshi_ceshi_you.this,
				"您的视力测试结果:\n小数视力"+shili.getXiaoShu()+"\n整数视力"+shili.getZhengShu(),
				Toast.LENGTH_LONG).show();
		Intent intent = new Intent();
		intent.setClass(Activity_jinshi_ceshi_you.this, Activity_jinshi_ceshi_wuyan_zuo.class);
		intent.putExtra("you_xiaoshu", shili.getXiaoShu());
		intent.putExtra("you_zhengshu",shili.getZhengShu());
		startActivity(intent);
		finish();
	}
}