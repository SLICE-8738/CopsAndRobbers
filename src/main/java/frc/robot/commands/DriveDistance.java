// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

public class DriveDistance extends Command {
  /** Creates a new DriveDistance. */
  double distance;
  public DriveDistance(double inches) {
    // Use addRequirements() here to declare subsystem dependencies.
    distance = inches;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //TODO:resetDriveEncoder method

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Drivetrain.drive(0, 0);
    //TODO: drivetrain auto values
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
