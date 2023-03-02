package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.autonomous.AutoChooser;
// import edu.wpi.first.wpilibj.Compressor;
// import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.subsystem.*;
import frc.robot.util.Controller;

public class Robot extends TimedRobot {
	// private final Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);

	public final Controller allPurpose = new Controller("all_purpose");
	public final Controller arcadeDriver = new Controller("driver_arcade");
	public final Controller tankDriver = new Controller("driver_tank");
	public final Controller operator = new Controller("operator");

	// public AutoChooser chooser = new AutoChooser();
	public final DriveTrain driveTrain = new DriveTrain();
	public final Lifter lifter = new Lifter();
	public final SpringedEndEffector endEffector = new SpringedEndEffector();

	@Override
	public void robotInit() {
		// compressor.setClosedLoopControl(true);
		// compressor.start();
		endEffector.release();
	}

	@Override
	public void robotPeriodic() {
		// SmartDashboard.putNumber("Compressor pressure", compressor.getCurrent());
		// log the position of the endEffectorHolder, this is accessible from endEffector.holder and will be a double
		SmartDashboard.putNumber("End Effector Holder Position", endEffector.holder.getPosition());
	}

	@Override
	public void autonomousInit() {
		// chooser.solveSelection(this);
		// chooser.run();
	}

	@Override
	public void autonomousPeriodic() {
		// chooser.run();
	}

	@Override
	public void teleopInit() {}

	@Override
	public void teleopPeriodic() {
		if (arcadeDriver.isConnected()) {
			// System.out.println("Arcade driver connected");
			double arcadeSpeed = arcadeDriver.getLeftY() * 0.6;
			double arcadeTurn = arcadeDriver.getRightX() * 0.45;
			// if (arcadeDriver.getRightBumperPressed()) driveTrain.shiftGear();

			driveTrain.arcadeDrive(arcadeSpeed, arcadeTurn);
		} else if (tankDriver.isConnected()) {
			// System.out.println("Tank driver connected");
			double tankLeftY = tankDriver.getLeftY() * 0.6;
			double tankRightY = tankDriver.getRightY() * 0.6;
			// if (tankDriver.getRightBumperPressed()) driveTrain.shiftGear();

			driveTrain.tankDrive(tankLeftY, tankRightY);
		}

		if (operator.isConnected()) {
			double operatorLeftY = operator.getLeftY();
			lifter.lift(operatorLeftY);
			double operatorRightY = operator.getRightX();
			endEffector.open(operatorRightY);
		}
	}

	@Override
	public void disabledInit() {
		driveTrain.stop();
		lifter.stop();
		endEffector.stop();
	}

	@Override
	public void disabledPeriodic() {}

	@Override
	public void testInit() {}

	@Override
	public void testPeriodic() {}
}
