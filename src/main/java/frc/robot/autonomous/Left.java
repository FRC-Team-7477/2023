package frc.robot.autonomous;

import frc.robot.Robot;

public class Left implements TemplateAuto {
	private Robot robot;

	public Left(Robot robot) {
		this.robot = robot;
	}

	public void run() {
		robot.driveTrain.tankDrive(0.5, 0.5);
	}
}
