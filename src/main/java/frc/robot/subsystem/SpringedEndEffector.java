package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * This class is used to control the end effector.
 */
public class SpringedEndEffector {
    private WPI_VictorSPX grabber;

    /**
     * This constructor initializes the grabber.
     */
    public SpringedEndEffector() {
        grabber = new WPI_VictorSPX(0);
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
