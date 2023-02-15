package frc.robot.subsystem;

import frc.robot.util.*;
import com.revrobotics.CANSparkMax;

/**
 * This class is used to control the end effector.
 */
public class SpringedEndEffector {
    private final CANSparkMax grabber = new Motor("grabberMotor", true);

    /**
     * This constructor initializes the grabber.
     */
    public SpringedEndEffector() {
    }

    /**
     * This method opens the grabber.
     *
     * @param speed The speed of the grabber.
     */
    public void open(double speed) {
        if (speed > 0) grabber.set(speed);
        else stop();
    }

    /**
     * This method closes the grabber.
     */
    public void stop() {
        grabber.set(0);
    }
}
