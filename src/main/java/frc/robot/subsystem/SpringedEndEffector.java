package frc.robot.subsystem;

import frc.robot.util.Motor;

/**
 * This class is used to control the end effector.
 */
public class SpringedEndEffector {
    public final EndEffectorHolder holder;
    public final Motor motor;
    /**
     * This constructor initializes the grabber.
     */
    public SpringedEndEffector(EndEffectorHolder holder) {
        motor = new Motor("grabberMotor");
        motor.setInverted(true);
        motor.setOpenLoopRampRate(3);

        this.holder = holder;
        holder.hold();
    }

    /**
     * This method opens the grabber.
     *
     * @param grip The speed of the grabber.
     */
    public void open(double grip) {
        motor.set(grip);
        //else motor.stop();
    }

    /**
     * This method releases the holder.
     */
    public void release() {
        holder.release();
    }

    public void stop() {
        motor.stop();
    }

    public double getPosition() {
        return motor.getPosition();
    }
}
