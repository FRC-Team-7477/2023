package frc.robot.autonomous;

import java.util.Map;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

interface TemplateAuto {
	public void run();
}

/**
 * This class is used to choose the autonomous mode.
 */
public class AutoChooser {
	public final SendableChooser<String> sendableChooser = new SendableChooser<>();
	public TemplateAuto autoClass;

	/**
	 * This map is used to store the names of the autonomous modes and their class names.
	 */
	public static final Map<String, String> Autos = Map.of(
		"Do Nothing", "DoNothing",
		"Middle Auto", "Middle",
		"Inner Auto", "Inner",
		"Outer Auto", "Outer"
	);

	/**
	 * This constructor adds all the autonomous modes to the SmartDashboard.
	 */
	public AutoChooser() {
		String doNothing = Autos.get("Do Nothing");
		sendableChooser.setDefaultOption("Default Option (Do Nothing)", doNothing);

		for (String key : Autos.keySet()) {
			sendableChooser.addOption(key, Autos.get(key));
		}

		SmartDashboard.putData("Auto choices", sendableChooser);
	}

	/**
	 * This method is used to choose the autonomous mode.
	 * @param robot The robot class.
	 */
	public void solveSelection(Robot robot) {
		String selectedAuto = sendableChooser.getSelected();
		System.out.println("Auto selected: " + selectedAuto);

		switch (selectedAuto) {
			case "Middle":
				autoClass = new Middle(robot);
				break;
			case "Inner":
				autoClass = new Inner(robot);
				break;
			case "Outer":
				autoClass = new Outer(robot);
				break;
			case "DoNothing":
			default:
				autoClass = new DoNothing(robot);
				break;
		}
	}

	/**
	 * This method is used to run the selected autonomous mode.
	 */
	public void run() {
		autoClass.run();
	}
}
