package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Servo;

public class EndEffectorHolder extends Servo {

    public EndEffectorHolder() {
        super(0);
    }

    public void hold() {
        set(0);
    }

    public void release() {
        set(0.1);
    }
}