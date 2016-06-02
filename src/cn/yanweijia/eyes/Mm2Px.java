package cn.yanweijia.eyes;
//本类是用来将dip单位换算成mm单位的
public class Mm2Px {
	private float rate = 0.0f;
	public Mm2Px(){rate = 0.0f;}
	public Mm2Px(float phone_rate)
	{
		rate = phone_rate;		
	}
	public void setRate(float phone_rate)
	{
		rate = phone_rate;
	}
	public int getPx(float mm)
	{
		return ((int)(mm * rate));
	}
}
