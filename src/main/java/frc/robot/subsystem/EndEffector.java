package frc.robot.subsystem;

import java.util.Map;
import frc.robot.util.*;
import com.revrobotics.CANSparkMax;

/**
 * This class is used to control the end effector.
 */
public class EndEffector {
	private final CANSparkMax grabber = new Motor("grabberMotor");
	public boolean isOpened = false;

	/**
	 * This map is used to map the positions of the grabber.
	 */
	public static final Map<Boolean, Integer> positions = Map.of(
		false, 0,
		true, 100
	);

	/**
	 * This constructor initializes the grabber.
	 */
	public EndEffector() {
		grabber.getEncoder().setPosition(0);
	}

	/**
	 * This method sets the position of the grabber to the state of isOpened.
	 */
	private void setPosition() {
		grabber.getEncoder().setPosition(positions.get(isOpened));
	}

	/**
	 * This method opens the grabber.
	 */
	public void open() {
		isOpened = true;
		setPosition();
	}

	/**
	 * This method closes the grabber.
	 */
	public void close() {
		isOpened = false;
		setPosition();
	}

	/**
	 * This method toggles the state of isOpened.
	 */
	public void toggle() {
		isOpened = !isOpened;
		setPosition();
	}
}
