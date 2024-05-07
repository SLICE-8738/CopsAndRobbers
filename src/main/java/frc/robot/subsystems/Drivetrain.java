// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  private CANSparkMax backLeft, backRight, frontLeft, frontRight; // Back and front motors on left and right.
  private DifferentialDrive drivetrain; // Differential drive

  /** Creates a new Drivetrain. */
  public Drivetrain() {
    backLeft = new CANSparkMax(15, MotorType.kBrushless);
    backRight = new CANSparkMax(18, MotorType.kBrushless);
    frontLeft = new CANSparkMax(16, MotorType.kBrushless);
    frontRight = new CANSparkMax(17, MotorType.kBrushless);

    

    backLeft.follow(frontLeft);
    backRight.follow(frontRight);

    drivetrain = new DifferentialDrive(frontLeft, frontRight);
  }

  public void drive(double forwardSpeed, double rotationalSpeed){
    drivetrain.arcadeDrive(forwardSpeed, rotationalSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
