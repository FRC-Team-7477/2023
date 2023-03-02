package frc.robot.subsystem;

import frc.robot.util.Motor;

/**
 * This class is used to control the end effector.
 */
public class SpringedEndEffector extends Motor {
    public final EndEffectorHolder holder = new EndEffectorHolder();

    /**
     * This constructor initializes the grabber.
     */
    public SpringedEndEffector() {
        super("grabberMotor");
        setInverted(true);
        setOpenLoopRampRate(3);

        holder.hold();
    }

    /**
     * This method opens the grabber.
     *
     * @param grip The speed of the grabber.
     */
    public void open(double grip) {
        if (grip > 0) set(grip);
        else stop();
    }

    /**
     * This method releases the holder.
     */
    public void release() {
        holder.release();
    }
}
