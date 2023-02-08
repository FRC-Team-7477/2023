package frc.robot.subsystem;

import frc.robot.Util;
import com.revrobotics.CANSparkMax;

public class Lifter {
    private final CANSparkMax lifterMotor = Util.getMotor("lifterMotor");
    public int currentStage = 0;

    public Lifter() {
        lifterMotor.getEncoder().setPosition(0);
    }

    public void lift(double speed) {
        if (speed > 0) lifterMotor.set(speed);
        else stop();
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
    
    public void switchPosition(int additive) {
        currentStage += additive;
        if (currentStage < 0) currentStage = 0;
        else if (currentStage > Util.LifterEncoderPositions.size() - 1) currentStage = Util.LifterEncoderPositions.size() - 1;
        setPosition(Util.LifterEncoderPositions.get(currentStage));
    }
}