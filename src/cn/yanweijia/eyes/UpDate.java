package cn.yanweijia.eyes;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

public class UpDate 
{
	
	//获得手机版本
	public int getVersionCode(Context context) throws NameNotFoundException
	{
		return context.getPackageManager().getPackageInfo("cn.yanweijia.eyes", 0).versionCode;
	}
	//获取手机型号
	public String getModel(Context ctx) 
	{
		 return android.os.Build.MODEL;
	}
     //获得系统固件版本
    public String getTarget(Context ctx) 
    {
        return android.os.Build.VERSION.RELEASE;
    }
    //获得系统固件版本号
    public String getSDK(Context ctx) 
    {
        return android.os.Build.VERSION.SDK;
    }
}
