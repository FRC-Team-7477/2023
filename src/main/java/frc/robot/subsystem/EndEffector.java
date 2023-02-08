package frc.robot.subsystem;

import frc.robot.Util;
import com.revrobotics.CANSparkMax;

public class EndEffector {
    private final CANSparkMax clawMotor = Util.getMotor("clawMotor");
    public boolean isOpened = false;

    public EndEffector() {
        clawMotor.getEncoder().setPosition(0);
    }

    private void setPosition() {
        clawMotor.getEncoder().setPosition(Util.ClawEncoderPositions.get(isOpened));
    }

    public void open() {
        isOpened = true;
        setPosition();
    }

    public void close() {
        isOpened = false;
        setPosition();
    }

    public void toggle() {
        isOpened = !isOpened;
        setPosition();
    }
}
