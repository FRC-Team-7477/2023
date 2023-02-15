package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.autonomous.Chooser;
// import edu.wpi.first.wpilibj.Compressor;
// import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.subsystem.*;


public class Robot extends TimedRobot {
	// private final Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);

	private final XboxController arcadeDriver = new XboxController(Util.Controllers.get("driver_arcade"));
	private final XboxController tankDriver = new XboxController(Util.Controllers.get("driver_tank"));
	private final XboxController operator = new XboxController(Util.Controllers.get("operator"));

	public Chooser chooser;
	public final DriveTrain driveTrain = new DriveTrain();
	public final Lifter lifter = new Lifter();

	@Override
	public void robotInit() {
		chooser = new Chooser();
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
		double arcadeSpeed = Util.deadband(arcadeDriver.getLeftY());
		double arcadeTurn = Util.deadband(arcadeDriver.getRightX());
		// if (arcadeDriver.getRightBumperPressed()) driveTrain.shiftGear();

		driveTrain.arcadeDrive(arcadeSpeed, arcadeTurn);
		} else if (tankDriver.isConnected()) {
		System.out.println("Tank driver connected");
		double tankLeftY = Util.deadband(tankDriver.getLeftY());
		double tankRightY = Util.deadband(tankDriver.getRightY());
		// if (tankDriver.getRightBumperPressed()) driveTrain.shiftGear();

		driveTrain.tankDrive(tankLeftY, tankRightY);
		}

		if (!operator.isConnected()) System.out.println("Operator not connected");
		if (operator.getYButton()) lifter.switchPosition(1);
		if (operator.getAButton()) lifter.switchPosition(-1);
		double operatorLeftY = Util.deadband(operator.getLeftY());
		lifter.lift(operatorLeftY);
	}

	@Override
	public void disabledInit() {
		driveTrain.stop();
		lifter.stop();
	}

	@Override
	public void disabledPeriodic() {}

	@Override
	public void testInit() {}

	@Override
	public void testPeriodic() {}
}
