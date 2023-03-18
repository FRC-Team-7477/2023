package frc.robot.autonomous;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Inner implements TemplateAuto {
	private Robot robot;

	public Inner(Robot robot) {
		this.robot = robot;
	}

	public void run() {
        // You should not be redefining the lifter and end effector here. They are already defined in Robot.java.
        // Just use robot.lifter and robot.endEffector.

		//this is where robot has to travel the least for mobility
		
		
		//STEP 1: gripping cone, raise arm to height with encoders
		//endEffector.open(-.1); NEEDED IF NOT SWITCHING SPRING
		
		if (robot.lifter.getPosition() > 20) { //20 is a placeholder
			robot.lifter.lift(0.5);
		} else {
			robot.lifter.stop();
		}

		//STEP 2: go forward until in line

		//STEP 3: release cone
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

		//STEP 4: travel backwards, 78 in
		robot.driveTrain.move(-78, 0, 0.5);

	}
}
