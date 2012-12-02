package com.calki.tribotai;

import lejos.nxt.LCD;
import lejos.robotics.subsumption.Behavior;

public class DriveBehavior implements Behavior{
	
	protected boolean supressed;
	@Override
	// funkja okreslajaca, ze nasz behavior chce przejac kontrole nad robotem w danym momencie
	// kontrola zostanie mu przyznana, gdy dla wszystkich behaviorow o wyzszym priorytecie ta funkcja
	// zwroci false
	// tej funkcji najlepiej nie ruszac:P
	public boolean takeControl()
	{
		return true;
	}

	@Override
	// funkcja zawierajaca "glowna petla programu" w momencie gdy nasz behavior ma kontrole nad robotem
	// czyli jest behaviorem o najwyzszym priorytecie, dla ktorego takeControl() zwrocila true
	// tutaj umieszcza sie myslenie robota
	// zmienna supressed przyjmuje wartosc true gdy behavior o wyzszym priorytecie przejmuje kontrole
	// dlatego ta zmienna jest sprawdzana w petli, aby mozna zakonczyc funkcje
	public void action() {
		supressed = false;
		//TribotAI.pilot.forward();
		while (!supressed)
		{
			LCD.drawInt((int)AvgSensor.Avg(), 0, 0);
		}
	}

	@Override
	// funkcja sluzaca do przerywania behaviora, gdy jakis behavior o wyzszym priorytecie zechce przejac kontrole nad robotem
	// tej funkcji najlepiej nie ruszac :P
	public void suppress() {
		supressed = true;
	}
}