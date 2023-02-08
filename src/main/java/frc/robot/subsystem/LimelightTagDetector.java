package frc.robot.subsystem;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimelightTagDetector {
    private NetworkTable table;

    public boolean isTargetVisible() {
        return table.getEntry("tv").getDouble(0) == 1;
    }

    public double getTargetX() {
        return table.getEntry("tx").getDouble(0);
    }

    public double getTargetY() {
        return table.getEntry("ty").getDouble(0);
    }

    public double getTargetArea() {
        return table.getEntry("ta").getDouble(0);
    }

    public LimelightTagDetector() {
        table = NetworkTableInstance.getDefault().getTable("limelight");

        while (true) {
            if (isTargetVisible()) {
                System.out.println("Target X: " + getTargetX());
                System.out.println("Target Y: " + getTargetY());
                System.out.println("Target Area: " + getTargetArea());
            } else {
                System.out.println("No target visible");
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
