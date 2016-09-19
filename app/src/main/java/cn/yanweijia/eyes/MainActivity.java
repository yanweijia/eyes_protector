package cn.yanweijia.eyes;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.adsmogo.adapters.AdsMogoCustomEventPlatformEnum;
import com.adsmogo.adview.AdsMogoLayout;
import com.adsmogo.controller.listener.AdsMogoListener;
import com.baidu.mobstat.StatService;

public class MainActivity extends Activity 
{
    int back = 0;//按两次退出/按一次提示"再按一次返回键退出"
    MyDatabaseHelper dbhelper;//数据库操作,用来判断是否显示广告
    SQLiteDatabase db;
    @Override
	protected void onCreate(Bundle savedInstanceState) 
    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//百度统计
		StatService.setSessionTimeOut(30);  //两次启动应用30s视为第二次启动
		StatService.setLogSenderDelayed(0); //崩溃后延迟0秒发送崩溃日志

		//TODO:专门用来写广告的Linearlayout
		LinearLayout adLayout = (LinearLayout)findViewById(R.id.linearlayout_main_ad);
		dbhelper = new MyDatabaseHelper(this,"eyes.db",null,1);
		db = dbhelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from GuangGao", null);
		if(cursor.getCount() == 0)
		{
			//TODO:如果数据库中没有关于取消广告的记载则显示广告
			AdsMogoLayout adsMogoLayoutCode = new AdsMogoLayout(MainActivity.this, "635d7536a3414841b21af99dab32a4ce");
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
		
		//首页功能的项数
		final int Size = 8;
		// 使用GridView初始化界面
		GridView gridview = (GridView) findViewById(R.id.gridview);
		ArrayList<HashMap<String, Object>> arraylist = new ArrayList<HashMap<String, Object>>();
		String[] array_str = new String[Size];
		array_str[0] = "夜间模式";
		array_str[1] = "眼睛保健图";
		array_str[2] = "视力测试";
		array_str[3] = "色盲测试";
		array_str[4] = "散光测试";
		array_str[5] = "测试记录";
		array_str[6] = "软件设置";
		array_str[7] = "退　出";
		int[] array_image = 
				{
				R.drawable.yejianmoshi,
				R.drawable.baojiantu,
				R.drawable.shili, 
				R.drawable.semang,
				R.drawable.sanguang, 
				R.drawable.record,
				R.drawable.shezhi,
				R.drawable.quit
				};
		for (int i = 0; i < Size; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("icon", array_image[i]);// 添加图像资源的ID
			map.put("name", array_str[i]);// 按序号做ItemText
			arraylist.add(map);
		}
		SimpleAdapter sa = new SimpleAdapter(this, arraylist,
				R.layout.activity_main_gridview_linearlayout, new String[] {
						"icon", "name" }, new int[] {
						R.id.imageview_main_activity,
						R.id.textview_main_activity });
		gridview.setAdapter(sa);

		// 给gridview设置监听器
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				startIntent(position);
			}
		});
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//TODO:销毁adsMogo的资源并清空线程
		AdsMogoLayout.clear();
	}
	//按下返回键提示消息
	@Override
	public void onBackPressed() {
		if(back == 0)
		{
			Toast.makeText(this, "o(>﹏<)o不要再按啦~~再按就退出了.", Toast.LENGTH_SHORT).show();
			back++;
		}
		else
		{
			finish();
		}
			
	}

	@Override
	protected void onPause() {
		super.onPause();
		//百度统计_统计页面
		StatService.onPause(this);
	}
	@Override
	protected void onRestart() {
		super.onRestart();
	}
	protected void onResume() 
	{
		super.onResume();
		//百度统计
		StatService.onResume(this);
	}
	// 启动各个功能的Activity
	public void startIntent(int position) {
		back = 0;
		Intent intent = new Intent();
		switch (position) {
		case 0:
		{
			intent.setClass(MainActivity.this, Activity_yejian.class);
			MainActivity.this.startActivity(intent);
		}
			break;
		case 1:
		{
			intent.setClass(MainActivity.this, Activity_baojiantu.class);
			MainActivity.this.startActivity(intent);
		}
			break;
		case 2: 
		{
			intent.setClass(MainActivity.this, Activity_jinshi.class);
			MainActivity.this.startActivity(intent);
		}
			break;
		case 3: 
		{
			intent.setClass(MainActivity.this, Activity_SeMang.class);
			MainActivity.this.startActivity(intent);
		}
			break;
		case 4: 
		{
			intent.setClass(MainActivity.this, Activity_sanguang_ceshi.class);
			MainActivity.this.startActivity(intent);
		}
			break;
		case 5:
		{
			intent.setClass(MainActivity.this, Activity_testrecord.class);
			MainActivity.this.startActivity(intent);
		}
			break;
		case 6:
		{
			intent.setClass(MainActivity.this, Activity_shezhi.class);
			MainActivity.this.startActivity(intent);
		}
			break;
		case 7:
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			builder.setTitle("护眼小助手");
			builder.setMessage("确定要退出软件 ?");
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					MainActivity.this.finish();
				}
			});
			builder.setNegativeButton("取消", null);
			builder.create().show();
		}
			break;
		default: Toast.makeText(MainActivity.this, "MainActivity Error! 主页面错误,请及时在设置中反馈给我们以便我们修正错误", Toast.LENGTH_SHORT).show();
		}
	}
}