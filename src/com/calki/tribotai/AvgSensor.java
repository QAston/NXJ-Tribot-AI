package com.calki.tribotai;

import lejos.util.Delay;
import lejos.util.Stopwatch;
import lejos.util.Stopwatch;

public class AvgSensor extends Thread {
	
	private static final int buforSize = 2;
	private static final int delayMs = 0;
	private static AvgSensor instance;
	public static void Init()
	{
		instance = new AvgSensor();
		instance.setDaemon(true);	
		instance.start();
	}
	
	public static float Avg()
	{
		float bufAvg = 0;
		for (int i = 0; i < buforSize; ++i)
		{
		    bufAvg += instance.BuforSensora[i];
		}
		return bufAvg / buforSize;
	}
	
	private int BuforSensora[];
	private int cyklBuforaSensora;
	private AvgSensor()
	{
		BuforSensora = new int[buforSize];
		cyklBuforaSensora = 0;
	}
	public void run()
	{
		  for (int i = 0; i < buforSize; ++i)
		  {
			  BuforSensora[i] = TribotAI.color.getRawLightValue();
		  }
		  while(true)
		  {
			  cyklBuforaSensora++;
			  cyklBuforaSensora %= buforSize;
			  BuforSensora[cyklBuforaSensora] = TribotAI.color.getRawLightValue();
		  }
	}
}
