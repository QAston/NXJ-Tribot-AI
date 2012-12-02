package com.calki.tribotai;

import lejos.robotics.subsumption.Behavior;
import lejos.util.PIDController;
import lejos.util.Stopwatch;
import lejos.nxt.*;

public class PIDEdgeDriveBehavior implements Behavior{

	protected boolean supressed;

	protected PIDController pidControler;
	protected int setpoint = 385;
	protected float deadband = 10;
	protected Stopwatch controlWatch = new Stopwatch();
	
	// dla speeda 25
	/*protected float kp = 0.4f;
	protected float ki = 0f;
    protected float kd = 0.1f;
    protected float limithigh = 60.0f;
    protected float limitlow = -60.0f;
    protected float integrallimithigh = 500f;
    protected float integrallimitlow = -500f;
    protected int msdelay = 10;*/
	
	/*// dla speeda 25
	protected float kp = 0.3f;
	protected float ki = 0.001f;
    protected float kd = 0.15f;
    protected float limithigh = 100.0f;
    protected float limitlow = -100.0f;
    protected float integrallimithigh = 15f;
    protected float integrallimitlow = -15f;
    protected int msdelay = 10;*/
	
	protected float kp = 0.26f;
	protected float ki = 0.003f;
    protected float kd = 0.35f;
    protected float limithigh = 100.0f;
    protected float limitlow = -100.0f;
    protected float integrallimithigh = 25f;
    protected float integrallimitlow = -25f;
    protected int msdelay = 5;
    
    public PIDEdgeDriveBehavior()
    {
    	pidControler = new PIDController(setpoint, msdelay);
    	pidControler.setPIDParam(PIDController.PID_DEADBAND, deadband);
    	pidControler.setPIDParam(PIDController.PID_KP, kp);
    	pidControler.setPIDParam(PIDController.PID_KI, ki);
    	pidControler.setPIDParam(PIDController.PID_KD, kd);
    	pidControler.setPIDParam(PIDController.PID_LIMITHIGH, limithigh);
        pidControler.setPIDParam(PIDController.PID_LIMITLOW, limitlow);
        pidControler.setPIDParam(PIDController.PID_I_LIMITHIGH, integrallimithigh);
        pidControler.setPIDParam(PIDController.PID_I_LIMITLOW, integrallimitlow);
    }
 
	@Override
	public boolean takeControl()
	{
		return true;
	}

	@Override
	public void action() {
		supressed = false;
		if (supressed)
			return;

		controlWatch.reset();
		while (!supressed)
		{
			float avg = AvgSensor.Avg();
			LCD.drawInt((int)avg, 0, 0);
	        int pct = pidControler.doPID((int)avg);
	        TribotAI.pilot.steer(TribotAI.strona*pct);
			/*if (AvgSensor.Avg() < 500)
			{
				controlWatch.reset();
			}
			else
			{
				if (controlWatch.elapsed() > 400)
				{
					TribotAI.strona *= -1;
					controlWatch.reset();
				}
			}*/
		}
	}

	@Override
	public void suppress() {
		supressed = true;
	}
}
