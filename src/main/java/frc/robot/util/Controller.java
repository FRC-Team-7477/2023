package frc.robot.util;

import java.util.Map;
import edu.wpi.first.wpilibj.XboxController;

/**
 * A wrapper for the XboxController class that allows for easier access to
 * controller ports and deadbanding.
 */
public class Controller extends XboxController {
    /**
     * A mapping of port names to port numbers.
     */
    public static final Map<String, Integer> ControllerMapping = Map.of(
        "driver_arcade", 0,
        "driver_tank", 1,
        "operator", 2,
        "all_purpose", 5
    );

    /**
     * Applies a deadband to the an axis value to prevent small values from being transmitted.
     * This will automatically correct for not hard capping the axis value at 0.8.
     *
     * @param value The axis value.
     * @return The axis value with deadband applied.
     */
    public static double deadband(double value) {
        return deadband(value, 0.2);
    }

    /**
     * Applies a deadband to the an axis value to prevent small values from being transmitted.
     * This will automatically correct for not hard capping the axis value at the given amount.
     *
     * @param value The axis value.
     * @param amount The amount of deadband to apply.
     * @return The axis value with deadband applied.
     */
    public static double deadband(double value, double amount) {
        if (Math.abs(value) < amount) return 0;
        else return (value - Math.copySign(amount, value)) / (1 - amount);
    }

    /**
     * Create a new controller on the given port.
     *
     * @param port The port the controller is plugged into.
     */
    public Controller(int port) {
        super(port);
    }

    /**
     * Create a new controller on the given port with the given port name.
     *
     * @param portName The name of the port the controller is plugged into.
     */
    public Controller(String portName) {
        super(ControllerMapping.get(portName));
    }

    /**
     * Get the X axis vlaue of left side of the controller.
     * Also, applies deadband to the value.
     *
     * @return The axis value.
     */
    public double getLeftX() {
        return deadband(super.getLeftX());
    }

    /**
     * Get the Y axis vlaue of left side of the controller.
     * Also, applies deadband to the value.
     *
     * @return The axis value.
     */
    public double getLeftY() {
        return deadband(super.getLeftY());
    }

    /**
     * Get the X axis vlaue of right side of the controller.
     * Also, applies deadband to the value.
     *
     * @return The axis value.
     */
    public double getRightX() {
        return deadband(super.getRightX());
    }

    /**
     * Get the Y axis vlaue of right side of the controller.
     * Also, applies deadband to the value.
     *
     * @return The axis value.
     */
    public double getRightY() {
        return deadband(super.getRightY());
    }

    /**
     * Get the left trigger (LT) axis value of the controller. Note that this axis is bound to the
     * range of [0, 1] as opposed to the usual [-1, 1].
     * Also, applies deadband to the value.
     *
     * @return The axis value.
     */
    public double getLeftTriggerAxis() {
        return deadband(super.getLeftTriggerAxis());
    }

    /**
     * Get the right trigger (RT) axis value of the controller. Note that this axis is bound to the
     * range of [0, 1] as opposed to the usual [-1, 1].
     * Also, applies deadband to the value.
     *
     * @return The axis value.
     */
    public double getRightTriggerAxis() {
        return deadband(super.getRightTriggerAxis());
    }
}
