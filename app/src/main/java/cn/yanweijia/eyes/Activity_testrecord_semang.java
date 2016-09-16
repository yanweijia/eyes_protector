package cn.yanweijia.eyes;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class Activity_testrecord_semang extends Activity {
	ListView listview = null;
	MyDatabaseHelper dbhelper;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lookrecord);
		Button btn_back = (Button)findViewById(R.id.button_lookrecord_back);
		btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		listview = (ListView)findViewById(R.id.listView_lookrecord);
		//数据库dbhelper实例化
		dbhelper = new MyDatabaseHelper(this,"eyes.db",null,1);
		db = dbhelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from SeMang", null);
		
		if (cursor.getCount() == 0)
		{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("护眼小助手");
			builder.setMessage("没有任何测试记录 !");
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which) 
				{
					Activity_testrecord_semang.this.finish();
				}
			});
			builder.create().show();
			db.close();
		}
		else
		{
			//获取记录总数
			int Size = cursor.getCount();
			
			final String[] array_time = new String[Size];
			String[] array_first = new String[Size];
			String[] array_second = new String[Size];
			
			int count = 0;
			//将记录指针移到首个位置
			cursor.moveToFirst();
			
			
			
			do{
				array_time[count] = cursor.getString(3);
				array_first[count] = "得分:"+cursor.getString(0)+" 结果:"+cursor.getString(1);
				array_second[count] = "描述:"+cursor.getString(2);
				count++;
			}while(cursor.moveToNext() == true);
			
			ArrayList<HashMap<String, Object>> arraylist = new ArrayList<HashMap<String, Object>>();
			for (int i = 0; i < Size; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("icon", R.drawable.semang);// 添加图像资源的ID
				map.put("name", "色盲");
				map.put("time", array_time[i]);
				map.put("first", array_first[i]);
				map.put("second", array_second[i]);
				arraylist.add(map);
			}
			SimpleAdapter sa = new SimpleAdapter(this, arraylist,
					R.layout.activity_record_linearlayout,
					new String[] {"icon", "name", "time", "first", "second" },
					new int[] {	R.id.imageview_record_xiangmu,R.id.textview_record_activity,R.id.textview_record_time,R.id.textview_record_first,R.id.textview_record_second });
			listview.setAdapter(sa);
			listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
			{
				public void onItemClick(AdapterView adapterView, View view,int position, long arg3) 
				{ 
					db.delete("SeMang", "time=?", new String[]{array_time[position]});
					Toast.makeText(Activity_testrecord_semang.this, "删除成功!", Toast.LENGTH_LONG).show();
					db.close();
					Intent intent = new Intent();
					intent.setClass(Activity_testrecord_semang.this, Activity_testrecord_semang.class);
					Activity_testrecord_semang.this.startActivity(intent);
					finish();
				} 
			});
		}
		

	}

}
