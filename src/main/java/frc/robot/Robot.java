package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.autonomous.AutoChooser;
// import edu.wpi.first.wpilibj.Compressor;
// import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.subsystem.*;
import frc.robot.util.Controller;


public class Robot extends TimedRobot {
	// private final Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);

	public final Controller arcadeDriver = new Controller("driver_arcade");
	public final Controller tankDriver = new Controller("driver_tank");
	public final Controller operator = new Controller("operator");

	public AutoChooser chooser;
	public DriveTrain driveTrain;
	public Lifter lifter;
	public SpringedEndEffector endEffector;

	@Override
	public void robotInit() {
		chooser = new AutoChooser();
		driveTrain = new DriveTrain();
		lifter = new Lifter();
		endEffector = new SpringedEndEffector();
	}

	@Override
	public void robotPeriodic() {
		// SmartDashboard.putNumber("Compressor pressure", compressor.getCurrent());
	}

	@Override
	public void autonomousInit() {
		chooser.solveSelection(this);
		chooser.run();
	}

	@Override
	public void autonomousPeriodic() {
		chooser.run();
	}

	@Override
	public void teleopInit() {}

	@Override
	public void teleopPeriodic() {
		if (arcadeDriver.isConnected()) {
			System.out.println("Arcade driver connected");
			double arcadeSpeed = arcadeDriver.getLeftY();
			double arcadeTurn = arcadeDriver.getRightX();
			// if (arcadeDriver.getRightBumperPressed()) driveTrain.shiftGear();

			driveTrain.arcadeDrive(arcadeSpeed, arcadeTurn);
		} else if (tankDriver.isConnected()) {
			System.out.println("Tank driver connected");
			double tankLeftY = tankDriver.getLeftY();
			double tankRightY = tankDriver.getRightY();
			// if (tankDriver.getRightBumperPressed()) driveTrain.shiftGear();

			driveTrain.tankDrive(tankLeftY, tankRightY);
		}

		if (!operator.isConnected()) System.out.println("Operator not connected");
		double operatorLeftY = operator.getLeftY();
		lifter.lift(operatorLeftY);
		double operatorRightY = operator.getRightY();
		endEffector.open(operatorRightY / 10);
	}

	@Override
	public void disabledInit() {
		driveTrain.stop();
		lifter.lifter.set(0);
		endEffector.stop();
	}

	@Override
	public void disabledPeriodic() {}

	@Override
	public void testInit() {}

	@Override
	public void testPeriodic() {}
}
