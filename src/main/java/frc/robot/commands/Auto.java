// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import org.opencv.core.Mat;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.Constants;
import frc.robot.LimelightHelpers;
import frc.robot.LimelightTable;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.SliceLimelight;

public class Auto extends Command {

  private final Drivetrain m_drivetrain;

  private final PIDController rotationController;

  private boolean outOfFrame;

  private boolean stopped;

  /** Creates a new AlignWithNoteCommand. */
  public Auto(Drivetrain drivetrain) {

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);

    m_drivetrain = drivetrain;

    rotationController = new PIDController(Constants.kPNoteAlignRotation, Constants.kINoteAlignRotation, Constants.kDNoteAlignRotation);
    rotationController.setSetpoint(0);
    rotationController.setTolerance(2);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    outOfFrame = false;
    stopped = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double rotation;
    boolean n = LimelightHelpers.getTV("limelight-slice");
    
    System.out.println("TV: " + n);

    if (n) {
      double XOffsetRotation = LimelightHelpers.getTX("limelight-slice");

      rotation = rotationController.calculate(XOffsetRotation);

      System.out.println("Rotation:" + rotation);
      System.out.println("Rotation XOffset: " + XOffsetRotation);

      if (Math.abs(XOffsetRotation) < 5) {
        outOfFrame = true;
        m_drivetrain.drive(0, -0.3);
      } else if (outOfFrame == false) {
        m_drivetrain.drive(-0.3, 0);
      }
    }
    else if (outOfFrame == false) {
      m_drivetrain.drive(-0.3, 0);

      //rotation = 15 * (timer.get() + 3.5) * Math.sin(5 * (timer.get() + 3.5));
    } else {
      stopped = true;
    }

 
    
    //LimelightHelpers.setLEDMode_ForceOn("limelight-slice");

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    m_drivetrain.drive(0,0);
    //LimelightHelpers.setLEDMode_ForceOff("limelight-slice");

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (stopped) {
      return true;
    } else {
      return false;
    }
  }

}