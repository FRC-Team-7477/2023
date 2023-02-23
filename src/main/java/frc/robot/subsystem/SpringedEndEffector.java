package frc.robot.subsystem;

import frc.robot.util.Motor;
import com.revrobotics.CANSparkMax;

/**
 * This class is used to control the end effector.
 */
public class SpringedEndEffector {
    private final CANSparkMax grabber = new Motor("grabberMotor", true);
    private final double maxPosition = 1000;

    /**
     * This constructor initializes the grabber.
     */
    public SpringedEndEffector() {
        setPosition(0);
    }

    /**
     * This method opens the grabber.
     *
     * @param speed The speed of the grabber.
     */
    public void open(double speed) {
        if (speed > 0 && getPosition() < maxPosition) grabber.set(speed);
        else stop();
    }

    /**
     * This method closes the grabber.
     */
    public void stop() {
        grabber.set(0);
    }

    /**
     * This method sets the position of the grabber.
     *
     * @param position The position of the grabber.
     */
    public void setPosition(double position) {
        grabber.getEncoder().setPosition(position);
    }

    /**
     * This method gets the position of the grabber.
     *
     * @return The position of the grabber.
     */
    public double getPosition() {
        return grabber.getEncoder().getPosition();
    }
}
