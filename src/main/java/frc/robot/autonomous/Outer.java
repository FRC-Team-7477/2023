package frc.robot.autonomous;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

public class Outer implements TemplateAuto {
	private Robot robot;

	public Outer(Robot robot) {
		this.robot = robot;
	}

	public void run() {
		// You should not be redefining the lifter and end effector here. They are already defined in Robot.java.
        // Just use robot.lifter and robot.endEffector.

		// robot.driveTrain.tankDrive(0.5, 0.5);
		//This is where bot must travel over bump to exit community
		//"each ROBOT whose BUMPERS have completely left its COMMUNITY at any point during AUTO" (Mobility, 3 pts)

		//gripping cone, raise arm to height (test encoders for distance)
		//endEffector.open(-.1);
		
		if (robot.lifter.getPosition() > 20) { //20 is a placeholder
			robot.lifter.lift(0.5);
		} else {
			robot.lifter.stop();
		}

		//go forward until in line

		//release cone
		// This code is entirely wrong. It will not work.
        // The first two statements will be true even after the match time is greater than 4.
        // Else if statements will run the first true statement and ignore the rest.
        // Therefore the last statement will never run.
        // Also you should not be hardcoding values like 3 and 4. Match time is irrelevant to the position of the end effector.
        // This code is ultimately flawed and should be rewritten.

		// if (Timer.getMatchTime() > 3 || robot.endEffector.getPosition() < 100) { //100 is a placeholder
		// 	robot.endEffector.open(.5);
		// } else if (Timer.getMatchTime() > 3 || robot.endEffector.getPosition() > 100) { //100 is a placeholder
		// 	robot.endEffector.open(.2); //(just enough to stop from closing)
		// } else if (Timer.getMatchTime() > 4) {
		// 	robot.endEffector.stop();
		// }

		//travel backward farther distance, 145 in
		robot.driveTrain.move(-145, 0, 0.5);

	}
}
