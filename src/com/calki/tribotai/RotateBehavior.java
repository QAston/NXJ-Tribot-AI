package com.calki.tribotai;

import java.util.Random;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.*;
import lejos.util.Stopwatch;

public class RotateBehavior implements Behavior{
	
	protected final int[] rotations = new int[]{15, -40, 90, -180, 180};
	protected boolean supressed;
	protected Stopwatch controlWatch;;
	
	public boolean needFinish()
	{
		return AvgSensor.Avg() < 450 || supressed;
	}
	@Override
	public boolean takeControl()
	{
		if (controlWatch == null)
		{
			controlWatch = new Stopwatch();
			controlWatch.reset();
			return false;
		}
		if (AvgSensor.Avg() < 500)
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
		if (supressed)
			return;
		
		for (int i = 0; !needFinish() &&  i < rotations.length; ++i)
		{
			int angle = rotations[i];
			if (i > 0)
				angle -= rotations[i-1];
			TribotAI.pilot.rotate(angle, true);
			while(TribotAI.pilot.isMoving())
			{
				if (needFinish())
				{
					TribotAI.pilot.stop();
					return;
				}
			}
		}
	}

	@Override
	public void suppress() {
		supressed = true;
	}
}

