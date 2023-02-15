package frc.robot.subsystem;

import frc.robot.Util;
import com.revrobotics.CANSparkMax;
// import edu.wpi.first.wpilibj.DoubleSolenoid;

public class DriveTrain {
	private final CANSparkMax frontLeft = Util.getMotor("frontLeft");
	private final CANSparkMax frontRight = Util.getMotor("frontRight");
	private final CANSparkMax backLeft = Util.getMotor("backLeft");
	private final CANSparkMax backRight = Util.getMotor("backRight");

	// private final DoubleSolenoid leftGearBox = Util.getSolenoid("leftGearBox");
	// private final DoubleSolenoid rightGearBox = Util.getSolenoid("rightGearBox");
	// private int timeSpentDrivingForwards = 0;
	// private boolean isHighGear = false;

	public DriveTrain() {
		frontLeft.setInverted(true);
		backLeft.setInverted(true);
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

	public void arcadeDrive(double speed, double turn) {
		frontLeft.set(speed + turn);
		frontRight.set(speed - turn);
		backLeft.set(speed + turn);
		backRight.set(speed - turn);
		// detectIfToSwitchGears();
	}

	public void tankDrive(double leftSpeed, double rightSpeed) {
		frontLeft.set(leftSpeed);
		frontRight.set(rightSpeed);
		backLeft.set(leftSpeed);
		backRight.set(rightSpeed);
		// detectIfToSwitchGears();
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

	public void stop() {
		tankDrive(0, 0);
	}
}
