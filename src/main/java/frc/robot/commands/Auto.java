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
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    boolean detected = IntakeLimelight.getTable().getTargetDetected();
    System.out.println("Value: " + detected);
    if(detected){
      
      m_drivetrain.drive(0, -0.3);
    } 
    else {
      m_drivetrain.drive(0.3, 0);
    }
    

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    m_drivetrain.drive(0,0);
    LimelightHelpers.setLEDMode_ForceOff("limelight-slice");

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(IntakeLimelight.getTable().getTargetDetected() == true){
      return true;
    } else{
      return false;
    }
    
  }

}