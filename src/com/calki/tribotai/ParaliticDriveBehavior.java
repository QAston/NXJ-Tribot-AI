package com.calki.tribotai;

import lejos.robotics.subsumption.Behavior;
import lejos.util.Stopwatch;

public class ParaliticDriveBehavior implements Behavior{
	
	protected boolean supressed;
	protected Stopwatch controlWatch;;
 
	@Override
	public boolean takeControl()
	{
		if (controlWatch == null)
		{
			controlWatch = new Stopwatch();
			controlWatch.reset();
			return false;
		}
		if (AvgSensor.Avg() < 520)
		{
			controlWatch.reset();
			return false;
		}
		else
			return controlWatch.elapsed() > 200;
	}
	
	@Override
	public void action() {
		supressed = false;
		float deviation = 20;
		while (!supressed)
		{
			while(AvgSensor.Avg() < 220);
			TribotAI.pilot.steer(deviation);
			float diff;
			do
			{
				diff = AvgSensor.Avg() - 160;
			}
			while(!supressed && diff < 150);
			deviation *= -1;
		}
	}

	@Override
	public void suppress() {
		supressed = true;
	}
}
