package com.zdpx.cctpp.concrete;

/**
 *
 */
public class SizeInfo {
    public SizeInfo(double length, double width, double height)
	{
		this.length = length;
		this.width = width;
		this.height = height;
	}

	public double getLength()
	{
		return this.length;
	}

	public double getWidth()
	{
		return this.width;
	}

	public double getHeight()
	{
		return this.height;
	}

	public double volume()
	{
		return this.length * this.width * this.height;
	}

	public void setLength(double length)
	{
		this.length = length;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

	public void upHeight(double volume)
	{
		double num = this.height;
		if (volume != this.volume())
		{
			double num2 = this.length * this.width;
			if (num2 != 0.0)
			{
				num = volume / num2;
			}
		}
		this.height = num;
	}

	public boolean Equals(SizeInfo sizeInfo)
	{
		return this.length == sizeInfo.length && this.width == sizeInfo.width && this.height == sizeInfo.height;
	}
//
//	@Override
//	public  int GetHashCode()
//	{
//
//    }

	private double length;

	private double width;

	private double height;
}
