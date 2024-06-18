// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj.ADIS16448_IMU;


public class Drivetrain extends SubsystemBase {
  
  private CANSparkMax backLeft, backRight, frontLeft, frontRight; // Back and front motors on left and right.
  
  private DifferentialDrive drivetrain; // Differential drive
  RelativeEncoder leftEncoder = frontLeft.getEncoder();
  RelativeEncoder rightEncoder = frontRight.getEncoder();
  
  /** Creates a new Drivetrain. */

  public Drivetrain() {
    backLeft = new CANSparkMax(15, MotorType.kBrushed);
    backRight = new CANSparkMax(18, MotorType.kBrushed);
    frontLeft = new CANSparkMax(16, MotorType.kBrushed);
    frontRight = new CANSparkMax(17, MotorType.kBrushed);

    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);

    backLeft.follow(frontLeft);
    backRight.follow(frontRight);

    drivetrain = new DifferentialDrive(frontLeft, frontRight);

  }

  public double getEncoderMeters() {
    return (getLeftEncoderPosition() + -getRightEncoderPosition()) / 2 * DriveConstants.kEncoderTick2Meter;
  }

  public void resetEncoders() {
    rightEncoder.setPosition(0);
    leftEncoder.setPosition(0);
  }

  public void drive(double forwardSpeed, double rotationalSpeed){
    drivetrain.arcadeDrive(forwardSpeed, rotationalSpeed);
  }

  public double getLeftEncoderPosition() {
    return leftEncoder.getPosition();
  }

  public double getRightEncoderPosition() {
    return rightEncoder.getPosition();
  }

  public double getLeftEncoderVelocity() {
    return leftEncoder.getVelocity();
  }

  public double getRightEncoderVelocity() {
    return rightEncoder.getVelocity();
  }

  public void arcadeDrive(double fwd, double rot) {
    drivetrain.arcadeDrive(fwd, rot);
  }

  public RelativeEncoder getLeftEncoder() {
    return leftEncoder;
  }

  public RelativeEncoder getRightEncoder() {
    return rightEncoder;
  }

  public void setMotors(double leftSpeed, double rightSpeed) {
    backLeft.set(leftSpeed);
    frontLeft.set(leftSpeed);
    backRight.set(-rightSpeed);
    frontRight.set(-rightSpeed);
}


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
