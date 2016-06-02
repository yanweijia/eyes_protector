package cn.yanweijia.eyes;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper
{
	public MyDatabaseHelper(Context context, String name,CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	final String CREATE_TABLE_SEMANG = "CREATE TABLE SeMang(score text,result text,description text,time text)";
	final String CREATE_TABLE_SHILI = "CREATE TABLE ShiLi(you_zheng text,you_xiao text,zuo_zheng text,zuo_xiao text,time text)";
	final String CREARE_TABLE_GUANGGAO = "CREATE TABLE GuangGao(guanggao integer)";


	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		db.execSQL(CREATE_TABLE_SEMANG);
		db.execSQL(CREATE_TABLE_SHILI);
		db.execSQL(CREARE_TABLE_GUANGGAO);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) 
	{
		// TODO Auto-generated method stub
		
	}
	public String getTime()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");       
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间       
		String str = formatter.format(curDate);
		return str;
	}
	
}
