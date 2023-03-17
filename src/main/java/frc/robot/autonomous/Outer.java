package frc.robot.autonomous;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import frc.robot.subsystem.Lifter;
import frc.robot.subsystem.SpringedEndEffector;

public class Outer implements TemplateAuto {
	private Robot robot;

	public Outer(Robot robot) {
		this.robot = robot;
	}

	public void run() {
		final SpringedEndEffector endEffector = new SpringedEndEffector(robot.effectorHolder);
		final Lifter lifter = new Lifter();
		// robot.driveTrain.tankDrive(0.5, 0.5);
		//This is where bot must travel over bump to exit community
		//"each ROBOT whose BUMPERS have completely left its COMMUNITY at any point during AUTO" (Mobility, 3 pts)

		//gripping cone, raise arm to height (test encoders for distance)
		//endEffector.open(-.1);
		
		if (lifter.getPosition() > 20) { //20 is a placeholder
			lifter.lift(0.5);
		} else {
			lifter.stop();
		}

		//go forward until in line

		//release cone
		if (Timer.getMatchTime() > 3 || endEffector.getPosition() < 100) { //100 is a placeholder
			endEffector.open(.5);
		} 
		else if(Timer.getMatchTime() > 3 || endEffector.getPosition() > 100) { //100 is a placeholder
			endEffector.open(.2); //(just enough to stop from closing)
		}
		else if (Timer.getMatchTime() > 4) {
			endEffector.stop();
		}

		//travel backward farther distance, 145 in
		robot.driveTrain.move(-145, 0, 0.5);

	}
}
