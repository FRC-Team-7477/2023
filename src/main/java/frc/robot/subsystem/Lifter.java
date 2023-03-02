package frc.robot.subsystem;

import frc.robot.util.Motor;

/**
 * This class is used to control the lifter.
 */
public class Lifter extends Motor {

	/**
	 * This constructor initializes the lifter.
	 */
	public Lifter() {
		super("lifterMotor");

		setInverted(true);
		setOpenLoopRampRate(3);
		setClosedLoopRampRate(3);
		setIdleMode(IdleMode.kBrake);
	}

	/**
	 * This method sets the speed of the lifter.
	 * @param speed The speed of the lifter.
	 */
	public void lift(double speed) {
		set(-speed);
	}

	// public void stop() {
	// 	if (getPosition() <= 0) super.stop();
	// 	else set(0.1);
	// }
}
