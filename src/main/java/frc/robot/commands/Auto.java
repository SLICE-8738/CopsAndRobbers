// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.Constants;
import frc.robot.LimelightHelpers;
import frc.robot.LimelightTable;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IntakeLimelight;

public class Auto extends Command {

  private final Drivetrain m_drivetrain;

  private final PIDController rotationController;


  private final Timer timer;
  private boolean timerOn;

  /** Creates a new AlignWithNoteCommand. */
  public Auto(Drivetrain drivetrain) {

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);

    m_drivetrain = drivetrain;

    rotationController = new PIDController(Constants.kPNoteAlignRotation, Constants.kINoteAlignRotation, Constants.kDNoteAlignRotation);
    rotationController.setSetpoint(0);
    rotationController.setTolerance(2);

    timer = new Timer();
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    timer.reset();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double rotation;

    if (IntakeLimelight.getTable().getTargetDetected()) {
      rotation = rotationController.calculate(IntakeLimelight.getTable().getXOffset());
      if (timerOn) {
        timer.reset();
        timerOn = false;
      }
    }
    else {
      if (!timerOn) {
        timer.start();
        timerOn = true;
      }
      rotation = 15 * (timer.get() + 3.5) * Math.sin(5 * (timer.get() + 3.5));
    }
    double error = rotationController.getPositionError();
    double power = (40 - Math.abs(error)) / 20.0;

    double height = IntakeLimelight.getTable().getYOffset() - 18.8;
    double multiplier = (height + 20) / 15.0;
    multiplier = MathUtil.clamp(multiplier, 0.45, 2);

    multiplier = IntakeLimelight.getTable().getTargetDetected() ? multiplier : 0.3;

    power *= multiplier;

    m_drivetrain.drive(0,.25);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    m_drivetrain.drive(0,0);
    LimelightHelpers.setLEDMode_ForceOff("limelight-intake");

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return IntakeLimelight.getTable().getTargetDetected();
  }

}