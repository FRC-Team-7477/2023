package frc.robot.subsystem;

import frc.robot.util.Motor;

/**
 * This class is used to control the lifter.
 */
public class Lifter {
    Motor motor;

	/**
	 * This constructor initializes the lifter.
	 */
	public Lifter() {
		motor = new Motor("lifterMotor");

		motor.setInverted(true);
		motor.setOpenLoopRampRate(3);
		motor.setClosedLoopRampRate(0.5);
		motor.setIdleMode(Motor.IdleMode.kBrake);
	}

	/**
	 * This method sets the speed of the lifter.
	 * @param speed The speed of the lifter.
	 */
	public void lift(double speed) {
		motor.set(-speed);
	}

	public double getPosition() {
		return motor.getPosition();
	}

	// public void stop() {
	// 	if (getPosition() <= 0) super.stop();
	// 	else set(0.1);
	// }

	public void stop() {
		motor.stop();
	}

}
