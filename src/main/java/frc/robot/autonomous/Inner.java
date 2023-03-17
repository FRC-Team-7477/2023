package frc.robot.autonomous;

import frc.robot.Robot;
import frc.robot.subsystem.Lifter;
import frc.robot.subsystem.SpringedEndEffector;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Inner implements TemplateAuto {
	private Robot robot;

	public Inner(Robot robot) {
		this.robot = robot;
		
		
	}

	public void run() {
		final SpringedEndEffector endEffector = new SpringedEndEffector(robot.effectorHolder);
		final Lifter lifter = new Lifter();
		// robot.driveTrain.tankDrive(0.5, 0.5);
		//this is where robot has to travel the least for mobility
		
		
		//STEP 1: gripping cone, raise arm to height with encoders
		//endEffector.open(-.1); NEEDED IF NOT SWITCHING SPRING
		
		if (lifter.getPosition() > 20) { //20 is a placeholder
			lifter.lift(0.5);
		} else {
			lifter.stop();
		}

		//STEP 2: go forward until in line

		//STEP 3: release cone
		if (Timer.getMatchTime() > 3 || endEffector.getPosition() < 100) { //100 is a placeholder
			endEffector.open(.5);
		} 
		else if(Timer.getMatchTime() > 3 || endEffector.getPosition() > 100) { //100 is a placeholder
			endEffector.open(.2); //(just enough to stop from closing)
		}
		else if (Timer.getMatchTime() > 4) {
			endEffector.stop();
		}

		//STEP 4: travel backwards, 78 in
		robot.driveTrain.move(-78, 0, 0.5);

	}
}
