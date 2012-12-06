package com.calki.tribotai;

import java.util.Random;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.*;
import lejos.util.Stopwatch;

public class RotateBehavior implements Behavior{
	
	protected final int[] rotations = new int[]{115, -145, 180};
	protected boolean supressed;
	protected Stopwatch controlWatch1;
	
	public boolean control = false;
	
	@Override
	public boolean takeControl()
	{
		if (supressed)
			return false;
		if (!control)
		{
			if (controlWatch1 == null)
			{
				controlWatch1 = new Stopwatch();
				controlWatch1.reset();
			}
			else if (AvgSensor.Avg() < 500)
			{
				controlWatch1.reset();
			}
			else if (controlWatch1.elapsed() > 200)
			{
				controlWatch1 = null;
				control = true;
			}
			return control;
		}
		else
		{
			control = TribotAI.color.getRawLightValue() > 450;
			return control;
		}
	}

	@Override
	public void action() {
		supressed = false;
		if (supressed)
			return;
		
		for (int i = 0; !supressed && control &&  i < rotations.length; ++i)
		{
			int angle = rotations[i];
			if (i > 0)
				angle -= rotations[i-1];
			TribotAI.pilot.rotate(TribotAI.strona*angle, true);
			while(!supressed && control && TribotAI.pilot.isMoving())
				Thread.yield();
		}
		TribotAI.pilot.stop();
	}

	@Override
	public void suppress() {
		supressed = true;
	}
}

