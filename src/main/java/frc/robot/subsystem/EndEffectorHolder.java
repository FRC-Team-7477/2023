package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class EndEffectorHolder extends SubsystemBase {
    Servo servo;

    public EndEffectorHolder(int channel) {
        servo = new Servo(channel);
    }

    public void hold() {
        servo.set(0);
    }

    public void release() {
        servo.set(0.1);
    }

    public double getPosition() {
        return servo.getPosition();
    }
}