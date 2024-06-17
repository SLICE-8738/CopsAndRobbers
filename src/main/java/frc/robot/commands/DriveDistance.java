// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.Constants.DriveConstants;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;

public class DriveDistance extends Command {

  private final Drivetrain drivetrain;
  double distance;

  public DriveDistance(Drivetrain drivetrain, double distance) {
    this.drivetrain = drivetrain;
    this.distance = distance;

    addRequirements(drivetrain);

    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("DriveForwardCmd started!");
    }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.setMotors(DriveConstants.kAutoDriveForwardSpeed, DriveConstants.kAutoDriveForwardSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.setMotors(0, 0);
    System.out.println("DriveForwardCmd ended!");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (drivetrain.getEncoderMeters() > distance) {
    return true;
    } else {
    return false;
    }
  }
}
