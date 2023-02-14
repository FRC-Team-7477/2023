package frc.robot;

import java.util.Map;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;

public class Util {
    public static final Map<String, Integer> Motors = Map.of(
        "frontLeft", 5,
        "frontRight", 1,
        "backLeft", 3,
        "backRight", 6,
        "lifterMotor", 51,
        "clawMotor", 61
    );

    public static CANSparkMax getMotor(String portName) {
        return getMotor(portName, true);
    }

    public static CANSparkMax getMotor(String portName, Boolean isBrushless) {
        int portNumber = Motors.get(portName);
        MotorType motorType = isBrushless? MotorType.kBrushless : MotorType.kBrushed;
        return new CANSparkMax(portNumber, motorType);
    }


    public static final Map<String, Integer> Controllers = Map.of(
        "driver_arcade", 0,
        "driver_tank", 1,
        "operator", 2
    );

    public static XboxController getController(String portName) {
        int portNumber = Controllers.get(portName);
        return new XboxController(portNumber);
    }


    public static final Map<String, Integer[]> Solenoids = Map.of(
        "leftGearBox", new Integer[] {0, 1},
        "rightGearBox", new Integer[] {2, 3}
    );

    public static DoubleSolenoid getSolenoid(String channelName) {
        Integer[] ports = Solenoids.get(channelName);
        return new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ports[0], ports[1]);
    }


    public static double deadband(double value) {
        return deadband(value, 0.2);
    }

    public static double deadband(double value, double deadband) {
        if (Math.abs(value) < deadband) return 0;
        else return (value - Math.copySign(deadband, value)) / (1 - deadband);
    }


    public static final Map<Boolean, Integer> ClawEncoderPositions = Map.of(
        false, 0,
        true, 100
    );


    public static final Map<Number, Integer> LifterEncoderPositions = Map.of(
        0, 0,
        1, 50,
        2, 100,
        3, 150
    );

    public static double getClosestLifterEncoderPosition(double position) {
        int currentBest = 0;
        int closestPosition = 0;

        for (Map.Entry<Number, Integer> entry : LifterEncoderPositions.entrySet()) {
            int distance = Math.abs((int) (entry.getKey().doubleValue() - position));
            if (distance < currentBest) {
                currentBest = distance;
                closestPosition = entry.getValue();
            }
        }

        return closestPosition;
    }
}
