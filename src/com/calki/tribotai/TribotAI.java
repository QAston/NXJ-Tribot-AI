package com.calki.tribotai;

import lejos.robotics.subsumption.*;
import lejos.nxt.*;
import lejos.nxt.comm.LCPBTResponder;
import lejos.nxt.comm.RConsole;
import lejos.robotics.navigation.*;
import lejos.util.*;
import java.util.*;
import java.lang.Math;
import lejos.nxt.comm.*;
/**
 * Robot that stops if it hits something before it completes its travel.
 */

public class TribotAI {
	/**
	 * Robot that stops if it hits something before it completes its travel.
	 */
	
	/*
	 * Wyjatki
		Class 0: java.lang.Object
		Class 1: java.lang.Throwable
		Class 2: java.lang.Error
		Class 3: java.lang.OutOfMemoryError
		Class 4: boolean
		Class 5: char
		Class 6: float
		Class 7: double
		Class 8: byte
		Class 9: short
		Class 10: int
		Class 11: long
		Class 12: void
		Class 13: java.lang.Object[]
		Class 14: java.lang.NoSuchMethodError
		Class 15: java.lang.StackOverflowError
		Class 16: java.lang.NullPointerException
		Class 17: boolean[]
		Class 18: char[]
		Class 19: float[]
		Class 20: double[]
		Class 21: byte[]
		Class 22: short[]
		Class 23: int[]
		Class 24: long[]
		Class 25: reserved
		Class 26: java.lang.ClassCastException
		Class 27: java.lang.ArithmeticException
		Class 28: java.lang.ArrayIndexOutOfBoundsException
		Class 29: java.lang.IllegalArgumentException
		Class 30: java.lang.InterruptedException
		Class 31: java.lang.IllegalStateException
		Class 32: java.lang.IllegalMonitorStateException
		Class 33: java.lang.ThreadDeath
	 */
	
	// ODCZYTY
	// bialy - 520-540 500<
	// szary1 415-430
	// szary2 310-320
	// szary 245-260
	// szary 185-210
	// czarny 168-184 140-160
	  
	// linia 170-190
	// lewo1: 190-200
	// lewo21/3 290-310 230-216
	// lewo 1/2 380-390 410 
	// 3/4 440-460 430-440
	// lewo full 510-520
	// prawo:
	// prawo1: 200-220
	// prawo 1/3 340-350
	// prawo 1/2 425-440
	// prawo 3/4 500-510
	// prawo 1 525-530
  
	// FUNKCJE RUCHU
	// _pilot.steer[Backward](turnRate[,angle]) - turnRate:[-200;200], 100 - abs(turnRate) - predkosc kola wewnetrznego, 100 - predkosc kola zewnetrznego, koloWewnetrzne:turnPct>0 - lewe, turnPct < 0 - prawe; angle - kat na okregu do przejechania
	// _pilot.arc[Backward](radius[,angle]) - radius: promien okregu po ktorym odbywa sie ruch, radius>0, srodek po lewej, <0 - po prawej; angle: kat ktory zostanie przejechany po okregu
	// _pilot.travelArc(radius[,angle]) - tak jak arc, ale konczy po przejechaniu dystansu
	// _pilot.forward[/Backward] - jazda przod/tyl
	// _pilot.rotateLeft[/Right] - obrot w miejscu lewo/prawo
	// _pilot.stop[/quickStop]
	// _pilot.getMovement - aktualnie wykonywane polecenie
	// _pilot.addMoveListener - dodanie reakcji na ruchy
	// getAngleIncrement/getMovementIncrement
	// getMinRadius/setMinRadius
	// isStalled/isMoving
	// reset - resetuje liczniki przejazdów
	// getRotateMaxSpeed/getRotateSpeed/setRotateSpeed stopnie/sec
	// getMaxTravelSpeed/getTravelSpeed/setTravelSpeed odleglosc/sec
	// setAcceleration
	
	public static DifferentialPilot pilot = new DifferentialPilot(5.6f, 12.3f, Motor.A, Motor.B, true);
	public static Random random = new Random();
	public static ColorSensor color = new ColorSensor(SensorPort.S2);
	
	public static float strona = -1.0f;
	  
	public static void main(String[] args) {
		// NXJMonitor - monitorowanie sensorow, silnikow itp!
		/*LCPBTResponder lcpThread = new LCPBTResponder();
		lcpThread.setDaemon(true);
		lcpThread.start();*/
		// NTJConsoleViewer
		/*RConsole.open();
	    RConsole.println("Test");
	    RConsole.close();*/
		pilot.setTravelSpeed(25);
		pilot.setRotateSpeed(155);
		LCD.drawString("STARTUJE!!!", 0, 0, false);
		LCD.drawString("LEWA!!!", 1, 1, false);
		Delay.msDelay(4700);
		AvgSensor.Init();
		Delay.msDelay(300);
        Behavior [] bArray = {new PIDEdgeDriveBehavior(), new RotateBehavior()};
        Arbitrator arby = new Arbitrator(bArray);
        arby.start();
	}
}
