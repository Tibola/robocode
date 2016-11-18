package miv;
import robocode.*;
public class MarkIV extends Robot
{
  int turnDirection = 1, ahead = 30;
	double vidaInicialInimigo = 100;

	public void run() {
	  setAdjustRadarForRobotTurn(true);
	  turnGunRight(-90);

	  setBodyColor(new java.awt.Color(54,165,67));
	  setGunColor(new java.awt.Color(150,150,150));
	  setRadarColor(new java.awt.Color(102,165,104));
		
	  while(true) {
        turnRadarRight(360);
		if(bordas()){
			voltarArea();
		}
	  }
	}

	public void onScannedRobot(ScannedRobotEvent e) {
      System.out.println(e.getName());
      if (!(e.getName().equals("samplesentry.BorderGuard"))){
        double inimigo = e.getBearing() % 360, energiaInimigo = e.getEnergy();

        turnRight(inimigo+90);

        if(getEnergy() > 50 && e.getDistance() < 50) {
          fire(3);
        }
        fire(1);

        if(vidaInicialInimigo != energiaInimigo){
          ahead(50);
        }

        vidaInicialInimigo = e.getEnergy();
      }
	}

	public void onHitByBullet(HitByBulletEvent e) {
		if (!(e.getName().equals("samplesentry.BorderGuard"))){
			ahead(75);
		}
	}

  	public void onHitWall(HitWallEvent e) {
    	turnLeft(180 - Math.abs(e.getBearing()));
  	}
	
	public boolean bordas() {
		if(getX() > (getBattleFieldWidth() - getSentryBorderSize()) || getX() < getSentryBorderSize() ){
			return true;
		}
		else {
			if(getY() > (getBattleFieldHeight() - getSentryBorderSize()) || getY() < getSentryBorderSize() ){
				return true;
			}
			return false;
		}
	}
	
	public void voltarArea(){
		if (getX() > (getBattleFieldWidth() - getSentryBorderSize())) {
			turnLeft(getHeading() + 90);
			ahead(150);
		}
		
		if (getY() > (getBattleFieldWidth() - getSentryBorderSize())) {
			turnLeft(getHeading() + 180);
			ahead(150);
		}
		
		if (getX() < getSentryBorderSize()) {
			turnRight(getHeading() + 180);
			ahead(150);
		}
		
		if (getY() < getSentryBorderSize()) {
			turnRight(getHeading() + 180);
			ahead(150);
		}
	}
}
