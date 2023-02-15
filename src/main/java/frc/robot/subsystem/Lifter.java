package frc.robot.subsystem;

import java.util.Map;
import frc.robot.util.*;
import com.revrobotics.CANSparkMax;

/**
 * This class is used to control the lifter.
 */
public class Lifter {
	private final CANSparkMax lifter = new Motor("lifterMotor");
	public int currentStage = 0;

	/**
	 * This map is used to map the positions of the lifter.
	 */
	public static final Map<Number, Integer> positions = Map.of(
		0, 0,
		1, 50,
		2, 100,
		3, 150
	);

	/**
	 * This constructor initializes the lifter.
	 */
	public Lifter() {
		lifter.getEncoder().setPosition(0);
	}

	/**
	 * This method sets the speed of the lifter.
	 * @param speed The speed of the lifter.
	 */
	public void lift(double speed) {
		if (speed > 0) lifter.set(speed);
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

	/**
	 * This method switches the position of the lifter.
	 *
	 * @param additive The additive of the position.
	 */
	public void switchPosition(int additive) {
		currentStage += additive;
		if (currentStage < 0) currentStage = 0;
		else if (currentStage > positions.size() - 1) currentStage = positions.size() - 1;
		setPosition(positions.get(currentStage));
	}
}
