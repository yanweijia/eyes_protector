package cn.yanweijia.eyes;

import java.util.ArrayList;
import java.util.HashMap;

import com.baidu.mobads.AdView;
import com.baidu.mobstat.StatService;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
		StatService.onPageStart(this,"主页面");
		dbhelper = new MyDatabaseHelper(this,"eyes.db",null,1);
		db = dbhelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from GuangGao", null);
		if(cursor.getCount() == 0)
		{
			//如果数据库中没有关于取消广告的记载则显示广告
			LinearLayout linearlayout = (LinearLayout)findViewById(R.id.linearlayout_main);
			AdView adView = new AdView(this);
			linearlayout.addView(adView);
		}
		db.close();
		


		//首页功能的项数
		final int Size = 11;
		// 使用GridView初始化界面
		GridView gridview = (GridView) findViewById(R.id.gridview);
		ArrayList<HashMap<String, Object>> arraylist = new ArrayList<HashMap<String, Object>>();
		String[] array_str = new String[Size];
		array_str[0] = "视力测试";
		array_str[1] = "色盲测试";
		array_str[2] = "散光测试";
		array_str[3] = "护眼壁纸";
		array_str[4] = "眼保健操";
		array_str[5] = "夜间模式";
		array_str[6] = "眼睛保健图";
		array_str[7] = "护眼小技巧";
		array_str[8] = "测试记录";
		array_str[9] = "软件设置";
		array_str[10] = "退　出";
		int[] array_image = 
				{ 
				R.drawable.shili, 
				R.drawable.semang,
				R.drawable.sanguang, 
				R.drawable.wallpaper, 
				R.drawable.yanbao,
				R.drawable.yejianmoshi,
				R.drawable.baojiantu,
				R.drawable.eyes_skill,
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
		// TODO Auto-generated method stub
		
		super.onDestroy();
	}
	//按下返回键提示消息
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		super.onPause();
	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub。
		super.onRestart();
	}
	protected void onResume() 
	{
		// TODO Auto-generated method stub
		StatService.onPageEnd(this,"主页面");
		super.onResume();
	}
	// 启动各个功能的Activity
	public void startIntent(int position) {
		back = 0;
		Intent intent = new Intent();
		switch (position) {
		case 0: 
		{
			intent.setClass(MainActivity.this, Activity_jinshi.class);
			MainActivity.this.startActivity(intent);
		}
			break;
		case 1: 
		{
			intent.setClass(MainActivity.this, Activity_SeMang.class);
			MainActivity.this.startActivity(intent);
		}
			break;
		case 2: 
		{
			intent.setClass(MainActivity.this, Activity_sanguang_ceshi.class);
			MainActivity.this.startActivity(intent);
		}
			break;
		case 3:
		{
			intent.setClass(MainActivity.this, Activity_bizhi.class);
			MainActivity.this.startActivity(intent);
		}
			break;
		case 4:
		{
			intent.setClass(MainActivity.this, Activity_yanbaojiancao.class);
			MainActivity.this.startActivity(intent);
		}
			break;
		case 5:
		{
			intent.setClass(MainActivity.this, Activity_yejian.class);
			MainActivity.this.startActivity(intent);
		}
			break;
		case 6:
		{
			intent.setClass(MainActivity.this, Activity_baojiantu.class);
			MainActivity.this.startActivity(intent);
		}
			break;
		case 7:
		{
			intent.setClass(MainActivity.this, Activity_eyesSkill.class);
			MainActivity.this.startActivity(intent);
		}
			break;
		case 8:
		{
			intent.setClass(MainActivity.this, Activity_testrecord.class);
			MainActivity.this.startActivity(intent);
		}
			break;
		case 9:
		{
			intent.setClass(MainActivity.this, Activity_shezhi.class);
			MainActivity.this.startActivity(intent);
		}
			break;
		case 10:
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
		default: Toast.makeText(MainActivity.this, "MainActivity Error!", Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		//成功创建菜单后执行
		
		menu.add(0, 1, 0, "建议反馈");
		menu.add(0, 2, 0, "关于软件");
		menu.add(0, 3, 0, "设置");
		menu.add(0, 4, 0, "退出");
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		//菜单项被选择后执行
		int id = item.getItemId();
		switch (id) 
		{
			case 1:
			{
				//建议反馈
				Intent intent_advice = new Intent();
				intent_advice.setClass(MainActivity.this,Activity_advice.class);
				MainActivity.this.startActivity(intent_advice);
			}
			break;
			case 2:
			{
				Intent intent = new Intent();
				intent.setClass(MainActivity.this,Activity_about.class );
				MainActivity.this.startActivity(intent);
			}
			break;
			case 3:
			{
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, Activity_shezhi.class);
				MainActivity.this.startActivity(intent);
			}
			break;
			case 4:
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
			default:
				Toast.makeText(null, "Sorry!\nThe MenuItem hasn't find!",Toast.LENGTH_SHORT).show();
		}
		return super.onOptionsItemSelected(item);
	}
}