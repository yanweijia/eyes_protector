package cn.yanweijia.eyes;




/*
 * 5.76mm 视力 :整数4.0 小数0.1
 * 3.04mm 视力 :整数4.3 小数0.2
 * 1.84mm 视力 :整数4.5 小数0.3
 * 1.44mm 视力 :整数4.6 小数0.4
 * 1.20mm 视力 :整数4.7 小数0.5
 * 0.96mm 视力 :整数4.8 小数0.6
 * 0.72mm 视力 :整数4.9 小数0.8
 * 0.60mm 视力 :整数5.0 小数1.0
 * 0.48mm 视力 :整数5.1 小数1.2
 * 0.40mm 视力 :整数5.2 小数1.5
 * 0.32mm 视力 :整数5.3 小数2.0
*/
public class ShiLi 
{
	public float xiaoshu = 0.0f;
	public ShiLi()
	{
		xiaoshu = 0.1f;
	}
	public boolean nextHigher()
	{
		if(xiaoshu == 0.1f)
			return false;
		else if(xiaoshu == 0.8f)
		{
			xiaoshu = 0.6f;
			return true;
		}
		else if(xiaoshu == 1.0f)
		{
			xiaoshu = 0.8f;
			return true;
		}
		else if(xiaoshu == 1.2f)
		{
			xiaoshu = 1.0f;
			return true;
		}
		else if(xiaoshu == 1.5f)
		{
			xiaoshu = 1.2f;
			return true;
		}
		else if(xiaoshu == 2.0f)
		{
			xiaoshu = 1.5f;
			return true;
		}
		else
		{
			xiaoshu = xiaoshu - 0.1f;
			return true;
		}
	}
	public boolean nextLower()
	{
		if(xiaoshu == 2.0f)
		{
			return false;
		}
		else if(xiaoshu ==1.5f)
		{
			xiaoshu = 2.0f;
			return true;
		}
		else if(xiaoshu ==1.2f)
		{
			xiaoshu = 1.5f;
			return true;
		}
		else if(xiaoshu ==1.0f)
		{
			xiaoshu = 1.2f;
			return true;
		}
		else if(xiaoshu ==0.8f)
		{
			xiaoshu = 1.0f;
			return true;
		}
		else if(xiaoshu ==0.6f)
		{
			xiaoshu = 0.8f;
			return true;
		}
		else
		{
			xiaoshu = xiaoshu + 0.1f;
			return true;
		}
	}

	public float getMm()
	{
		if(xiaoshu == 0.1f)
			return 5.76f;
		else if(xiaoshu == 0.2f)
			return 3.04f;
		else if(xiaoshu == 0.3f)
			return 1.84f;
		else if(xiaoshu == 0.4f)
			return 1.44f;
		else if(xiaoshu == 0.5f)
			return 1.20f;
		else if(xiaoshu == 0.6f)
			return 0.96f;
		else if(xiaoshu == 0.8f)
			return 0.72f;
		else if(xiaoshu == 1.0f)
			return 0.60f;
		else if(xiaoshu == 1.2f)
			return 0.48f;
		else if(xiaoshu == 1.5f)
			return 0.40f;
		else
			return 0.32f;
					
	}
	public float getZhengShu()
	{
		if(xiaoshu == 0.1f)
			return 4.0f;
		else if(xiaoshu == 0.2f)
			return 4.3f;
		else if(xiaoshu == 0.3f)
			return 4.5f;
		else if(xiaoshu == 0.4f)
			return 4.6f;
		else if(xiaoshu == 0.5f)
			return 4.7f;
		else if(xiaoshu == 0.6f)
			return 4.8f;
		else if(xiaoshu == 0.8f)
			return 4.9f;
		else if(xiaoshu == 1.0f)
			return 5.0f;
		else if(xiaoshu == 1.2f)
			return 5.1f;
		else if(xiaoshu == 1.5f)
			return 5.2f;
		else
			return 5.3f;

	}
	public float getXiaoShu()
	{
		return xiaoshu;
	}
}
