package frc.robot.subsystem;

import frc.robot.Util;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class DriveTrain {
    private final CANSparkMax frontLeft = Util.getMotor("frontLeft");
    private final CANSparkMax frontRight = Util.getMotor("frontRight");
    private final CANSparkMax backLeft = Util.getMotor("backLeft");
    private final CANSparkMax backRight = Util.getMotor("backRight");

    private final DoubleSolenoid leftGearBox = Util.getSolenoid("leftGearBox");
    private final DoubleSolenoid rightGearBox = Util.getSolenoid("rightGearBox");
    private boolean isHighGear = true;

    public DriveTrain() {
        frontLeft.setInverted(true);
        backLeft.setInverted(true);
    }

    public void arcadeDrive(double speed, double turn) {
        frontLeft.set(speed + turn);
        frontRight.set(speed - turn);
        backLeft.set(speed + turn);
        backRight.set(speed - turn);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        frontLeft.set(leftSpeed);
        frontRight.set(rightSpeed);
        backLeft.set(leftSpeed);
        backRight.set(rightSpeed);
    }

    public void shiftGear() {
        System.out.println("Gear: " + (isHighGear? "High" : "Low"));
        if (isHighGear) {
            leftGearBox.set(DoubleSolenoid.Value.kReverse);
            rightGearBox.set(DoubleSolenoid.Value.kReverse);
        } else {
            leftGearBox.set(DoubleSolenoid.Value.kForward);
            rightGearBox.set(DoubleSolenoid.Value.kForward);
        }
        isHighGear = !isHighGear;
    }
}
