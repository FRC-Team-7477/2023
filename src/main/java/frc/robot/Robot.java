package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.Util;
import frc.robot.subsystem.*;


public class Robot extends TimedRobot {
  private static final String defaultAuto = "Default";
  private static final String customAuto = "My Auto";
  private String selectedAuto;
  private final SendableChooser<String> sendableChooser = new SendableChooser<>();

  private final Compressor compressor = new Compressor(PneumaticsModuleType.CTREPCM);

  private final XboxController arcadeDriver = new XboxController(Util.Controllers.get("driver_arcade"));
  private final XboxController tankDriver = new XboxController(Util.Controllers.get("driver_tank"));
  private final XboxController operator = new XboxController(Util.Controllers.get("operator"));

  private final DriveTrain driveTrain = new DriveTrain();
  private final Lifter lifter = new Lifter();

  @Override
  public void robotInit() {
    sendableChooser.setDefaultOption("Default Auto", defaultAuto);
    sendableChooser.addOption("My Auto", customAuto);
    SmartDashboard.putData("Auto choices", sendableChooser);
  }

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Compressor pressure", compressor.getCurrent());
  }

  @Override
  public void autonomousInit() {
    selectedAuto = sendableChooser.getSelected();
    System.out.println("Auto selected: " + selectedAuto);
  }

  @Override
  public void autonomousPeriodic() {
    switch (selectedAuto) {
      case customAuto:
        break;
      case defaultAuto:
      default:
        break;
    }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    if (arcadeDriver.isConnected()) {
      System.out.println("Arcade driver connected");
      double arcadeSpeed = Util.deadband(arcadeDriver.getLeftY());
      double arcadeTurn = Util.deadband(arcadeDriver.getRightX());

      driveTrain.arcadeDrive(arcadeSpeed, arcadeTurn);
    } else if (tankDriver.isConnected()) {
      System.out.println("Tank driver connected");
      double tankLeftY = Util.deadband(tankDriver.getLeftY());
      double tankRightY = Util.deadband(tankDriver.getRightY());

      driveTrain.tankDrive(tankLeftY, tankRightY);
    }

    if (!operator.isConnected()) System.out.println("Operator not connected");
    if (operator.getAButton()) lifter.switchMode();
    double operatorLeftY = Util.deadband(operator.getLeftY());
    lifter.lift(operatorLeftY);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
