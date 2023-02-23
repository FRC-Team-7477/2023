package frc.robot.util;

import java.util.Map;
import com.revrobotics.CANSparkMax;

/**
 * This class is used to control the motors.
 */
public class Motor extends CANSparkMax {
    /**
     * This map is used to map the ports of the motors.
     */
    public static final Map<String, Integer> mapping = Map.of(
        "frontLeft", 1,
        "frontRight", 4,
        "backLeft", 2,
        "backRight", 3,
        "lifterMotor", 5,
        "grabberMotor", 6
    );

    /**
     * This constructor initializes the motor.
     *
     * @param portName The name of the port.
     */
    public Motor(String portName) {
        this(portName, false);
    }

    /**
     * This constructor initializes the motor.
     *
     * @param portName The name of the port.
     * @param isOnCoastMode Whether the motor is on coast mode.
     */
    public Motor(String portName, Boolean isOnCoastMode) {
        this(portName, true, isOnCoastMode);
    }

    /**
     * This constructor initializes the motor.
     *
     * @param portName The name of the port.
     * @param isBrushless Whether the motor is brushless.
     * @param isOnCoastMode Whether the motor is on coast mode.
     */
    public Motor(String portName, Boolean isBrushless, Boolean isOnCoastMode) {
        super(mapping.get(portName), isBrushless? MotorType.kBrushless : MotorType.kBrushed);
        setIdleMode(isOnCoastMode? IdleMode.kCoast : IdleMode.kBrake);
    }
}
