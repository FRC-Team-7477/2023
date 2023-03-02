package frc.robot.subsystem;

import frc.robot.util.Motor;
// import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

/**
 * This class is used to control the drivetrain.
 */
public class DriveTrain extends DifferentialDrive {
	private static final Motor frontLeft = new Motor("frontLeft");
	private static final Motor frontRight = new Motor("frontRight");
	private static final Motor backLeft = new Motor("backLeft");
	private static final Motor backRight = new Motor("backRight");

	private static final MotorControllerGroup left = new MotorControllerGroup(frontLeft, backLeft);
	private static final MotorControllerGroup right = new MotorControllerGroup(frontRight, backRight);

	// private final DoubleSolenoid leftGearBox = Util.getSolenoid("leftGearBox");
	// private final DoubleSolenoid rightGearBox = Util.getSolenoid("rightGearBox");
	// private int timeSpentDrivingForwards = 0;
	// private boolean isHighGear = false;

	/**
	 * This constructor initializes the motors.
	 */
	public DriveTrain() {
		super(left, right);

		left.setInverted(true);

		frontLeft.setOpenLoopRampRate(3);
		frontRight.setOpenLoopRampRate(3);
		backLeft.setOpenLoopRampRate(3);
		backRight.setOpenLoopRampRate(3);

		frontLeft.setClosedLoopRampRate(1);
		frontRight.setClosedLoopRampRate(1);
		backLeft.setClosedLoopRampRate(1);
		backRight.setClosedLoopRampRate(1);
	}

	// private void detectIfToSwitchGears() {
	//     if (frontLeft.get() > 0.1 || frontRight.get() > 0.1 || backLeft.get() > 0.1 || backRight.get() > 0.1) {
	//         timeSpentDrivingForwards++;
	//     } else {
	//         timeSpentDrivingForwards = 0;
	//     }

	//     if (timeSpentDrivingForwards > 50 && !isHighGear) {
	//         shiftGear("HIGH");
	//     } else if (timeSpentDrivingForwards < 50 && isHighGear) {
	//         shiftGear("LOW");
	//     }
	// }

	// /**
	//  * This method implements the arcade drive.
	//  *
	//  * @param speed The speed of the robot.
	//  * @param turn The turning speed of the robot.
	//  */
	// public void arcadeDrive(double speed, double turn) {
	// 	super.arcadeDrive(speed, turn);
	// 	detectIfToSwitchGears();
	// }

	// /**
	//  * This method implements the tank drive.
	//  *
	//  * @param leftSpeed The speed of the left side of the robot.
	//  * @param rightSpeed The speed of the right side of the robot.
	//  */
	// public void tankDrive(double leftSpeed, double rightSpeed) {
	// 	super.tankDrive(leftSpeed, rightSpeed);
	// 	detectIfToSwitchGears();
	// }

	// might not work
	/**
	 * This method moves the robot to a specific location.
	 *
	 * @param inches The distance to move in the x direction.
	 * @param turn The distance to move in the y direction.
	 * @param speed The speed to move at.
	 */
	public void move(double inches, double turn, double speed) {
		double distance = Math.sqrt(Math.pow(inches, 2) + Math.pow(turn, 2));
		double angle = Math.atan2(inches, turn);
		double robotAngle = Math.atan2(frontLeft.get(), frontRight.get());
		double relativeAngle = angle - robotAngle;
		double relativeDistance = Math.sqrt(Math.pow(distance * Math.cos(relativeAngle), 2) + Math.pow(distance * Math.sin(relativeAngle), 2));

		arcadeDrive(0, 0.5);
		while (Math.abs(relativeAngle) > 0.1) {
			robotAngle = Math.atan2(frontLeft.get(), frontRight.get());
			relativeAngle = angle - robotAngle;
			arcadeDrive(0, 0.5);
		}

		arcadeDrive(0.5, 0);
		while (relativeDistance > 0.1) {
			distance = Math.sqrt(Math.pow(inches, 2) + Math.pow(turn, 2));
			angle = Math.atan2(inches, turn);
			robotAngle = Math.atan2(frontLeft.get(), frontRight.get());
			relativeAngle = angle - robotAngle;
			relativeDistance = Math.sqrt(Math.pow(distance * Math.cos(relativeAngle), 2) + Math.pow(distance * Math.sin(relativeAngle), 2));
			arcadeDrive(0.5, 0);
		}

		stop();
	}

	// public void shiftGear() {
	//     shiftGear(isHighGear? "LOW" : "HIGH");
	// }

	// public void shiftGear(String gear) {
	//     System.out.println("Gear: " + (isHighGear? "High" : "Low"));
	//     if (gear.equals("HIGH")) {
	//         leftGearBox.set(DoubleSolenoid.Value.kReverse);
	//         rightGearBox.set(DoubleSolenoid.Value.kReverse);
	//         isHighGear = true;
	//     } else if (gear.equals("LOW")) {
	//         leftGearBox.set(DoubleSolenoid.Value.kForward);
	//         rightGearBox.set(DoubleSolenoid.Value.kForward);
	//         isHighGear = false;
	//     }
	// }

	/**
	 * This method stops the robot.
	 */
	public void stop() {
		tankDrive(0, 0);
	}
}
