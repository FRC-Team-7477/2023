package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.autonomous.AutoChooser;
// import frc.robot.autonomous.AutoChooser;
// import edu.wpi.first.wpilibj.Compressor;
// import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.subsystem.*;
import frc.robot.util.Controller;

public class Robot extends TimedRobot {
	// private final Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);

	//public final Controller allPurpose = new Controller("all_purpose");
	//public final Controller arcadeDriver = new Controller("driver_arcade");
	public final Controller tankDriver = new Controller("driver_tank");
	public final Controller operator = new Controller("operator");
	public final EndEffectorHolder effectorHolder = new EndEffectorHolder(0);
	public AutoChooser chooser = new AutoChooser();
	public final DriveTrain driveTrain = new DriveTrain();
	public final Lifter lifter = new Lifter();
	public final SpringedEndEffector endEffector = new SpringedEndEffector(effectorHolder);

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
		SmartDashboard.putNumber("End Effector Holder Position", endEffector.getPosition());
	}

	@Override
	public void autonomousInit() {
		chooser.solveSelection(this);
		//chooser.run();
	}

	@Override
	public void autonomousPeriodic() {
		chooser.run();
	}

	boolean isTank = false;
	@Override
	public void teleopInit() {
		if (tankDriver.isConnected())
		{
			isTank = true;
		}
	}

	@Override
	public void teleopPeriodic() {
			// System.out.println("Tank driver connected");
			double tankLeftY = tankDriver.getLeftY()/2;
			double tankRightY = tankDriver.getRightY()/2;
			// if (tankDriver.getRightBumperPressed()) driveTrain.shiftGear();

			driveTrain.tankDrive(tankLeftY, tankRightY);


		double operatorLeftY = operator.getLeftY();
		lifter.lift(operatorLeftY);
		double operatorRightY = operator.getRightX();
		endEffector.open(operatorRightY);
		if (operator.getAButton()){
			effectorHolder.release();
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
