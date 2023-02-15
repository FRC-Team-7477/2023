package frc.robot.autonomous;

import java.util.Map;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

interface TemplateAuto {
	public void run();
}

public class Chooser {
	public final SendableChooser<String> sendableChooser = new SendableChooser<>();
	public TemplateAuto autoClass;

	public static final Map<String, String> Autos = Map.of(
		"Do Nothing", "DoNothing",
		"Middle Auto", "Middle",
		"Left Auto", "Left",
		"Right Auto", "Right"
	);

	public Chooser() {
		String doNothing = Autos.get("Do Nothing");
		sendableChooser.setDefaultOption("Default Option (Do Nothing)", doNothing);

		for (String key : Autos.keySet()) {
			sendableChooser.addOption(key, Autos.get(key));
		}

		SmartDashboard.putData("Auto choices", sendableChooser);
	}

	public void solveSelection(Robot robot) {
		String selectedAuto = sendableChooser.getSelected();
		System.out.println("Auto selected: " + selectedAuto);

		switch (selectedAuto) {
			case "Middle":
				autoClass = new Middle(robot);
				break;
			case "Left":
				autoClass = new Left(robot);
				break;
			case "Right":
				autoClass = new Right(robot);
				break;
			case "DoNothing":
			default:
				autoClass = new DoNothing(robot);
				break;
		}
	}

	public void run() {
		autoClass.run();
	}
}
