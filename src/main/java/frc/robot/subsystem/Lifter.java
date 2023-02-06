package frc.robot.subsystem;

import frc.robot.Util;
import com.revrobotics.CANSparkMax;

public class Lifter {
    private final CANSparkMax lifterMotor = Util.getMotor("lifterMotor");
    private boolean isPositional = false;

    public Lifter() {
        lifterMotor.getEncoder().setPosition(0);
    }

    public void lift(double speed) {
        if (isPositional) liftPositional(speed);
        else {
            if (speed > 0) lifterMotor.set(speed);
            else stop();
        }
    }

    public void stop() {
        if (getPosition() <= 0) lifterMotor.set(0);
        else lifterMotor.set(0.1);
    }

    public void setPosition(double position) {
        lifterMotor.getEncoder().setPosition(position);
    }

    public double getPosition() {
        return lifterMotor.getEncoder().getPosition();
    }

    public void liftPositional(double position) {
        double closest = (double) Util.getClosestLifterEncoderPosition(position);
        setPosition(closest);
    }
    
    public void switchMode() {
        isPositional = !isPositional;

        if (isPositional) {
            System.out.println("Lifter mode: Positional");
            stop();
            liftPositional(getPosition());
        } else {
            System.out.println("Lifter mode: Continuous");
        }
    }
}