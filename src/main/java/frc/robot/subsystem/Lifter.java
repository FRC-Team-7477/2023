package frc.robot.subsystem;

import frc.robot.util.Motor;
import com.revrobotics.CANSparkMax;

/**
 * This class is used to control the lifter.
 */
public class Lifter {
	public final CANSparkMax lifter = new Motor("lifterMotor", true);
	private final double maxPosition = 1000;

	/**
	 * This constructor initializes the lifter.
	 */
	public Lifter() {
		setPosition(0);
	}

	/**
	 * This method sets the speed of the lifter.
	 * @param speed The speed of the lifter.
	 */
	public void lift(double speed) {
		if (speed > 0 && getPosition() < maxPosition) lifter.set(speed);
		else stop();
	}

	/**
	 * This method stops the lifter. This will gently let the lifter down.
	 */
	public void stop() {
		if (getPosition() <= 0) lifter.set(0);
		else lifter.set(0.1);
	}

	/**
	 * This method sets the position of the lifter.
	 *
	 * @param position The position of the lifter.
	 */
	public void setPosition(double position) {
		lifter.getEncoder().setPosition(position);
	}

	/**
	 * This method gets the position of the lifter.
	 *
	 * @return The position of the lifter.
	 */
	public double getPosition() {
		return lifter.getEncoder().getPosition();
	}
}
