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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj.ADIS16448_IMU;

public class Drivetrain extends SubsystemBase {
  
  public static final AHRS navX = new AHRS(SPI.Port.kMXP);
  
  private CANSparkMax backLeft, backRight, frontLeft, frontRight; // Back and front motors on left and right.
  
  private DifferentialDrive drivetrain; // Differential drive
  RelativeEncoder leftEncoder = frontLeft.getEncoder();
  RelativeEncoder rightEncoder = frontRight.getEncoder();
  private final DifferentialDriveOdometry m_odometry;

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

    rightEncoder.setPositionConversionFactor(DriveConstants.kLinearDistanceConversionFactor);
    leftEncoder.setPositionConversionFactor(DriveConstants.kLinearDistanceConversionFactor);
    rightEncoder.setVelocityConversionFactor(DriveConstants.kLinearDistanceConversionFactor / 60);
    leftEncoder.setVelocityConversionFactor(DriveConstants.kLinearDistanceConversionFactor / 60);

    navX.reset();

    resetEncoders();
    
    
    m_odometry = new DifferentialDriveOdometry(navX.getRotation2d()); //MIGHT NEED TO BE CHANGED 
    m_odometry.resetPosition(new Pose2d(), navX.getRotation2d());
  }

  public void resetEncoders() {
    rightEncoder.setPosition(0);
    leftEncoder.setPosition(0);
  }

  public void setBreakMode() {
    backLeft.setIdleMode(IdleMode.kBrake);
    frontLeft.setIdleMode(IdleMode.kBrake);
    backRight.setIdleMode(IdleMode.kBrake);
    frontRight.setIdleMode(IdleMode.kBrake);
  }

  
  public void setCoastMode() {
    backLeft.setIdleMode(IdleMode.kCoast);
    frontLeft.setIdleMode(IdleMode.kCoast);
    backRight.setIdleMode(IdleMode.kCoast);
    frontRight.setIdleMode(IdleMode.kCoast);
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

  public static double getTurnRate() {
    return navX.getRate();
  }
  public static double getHeading() {
    return navX.getRotation2d().getDegrees();
  }
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(pose, navX.getRotation2d());
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftEncoderVelocity(),getRightEncoderVelocity());
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    backLeft.setVoltage(leftVolts);
    frontLeft.setVoltage(leftVolts);
    frontRight.setVoltage(rightVolts);
    backRight.setVoltage(rightVolts);
    drivetrain.feed();
  }

  public double getAverageEncoderDistance() {
    return ((getLeftEncoderPosition() + getRightEncoderPosition()) / 2.0);
  }

  public RelativeEncoder getLeftEncoder() {
    return leftEncoder;
  }

  public RelativeEncoder getRightEncoder() {
    return rightEncoder;
  }

  public void setMaxOutput(double maxOutput) {
    drivetrain.setMaxOutput(maxOutput);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_odometry.update(navX.getRotation2d(), leftEncoder.getPosition(), rightEncoder.getPosition());

    SmartDashboard.putNumber("Left encoder value meters", getLeftEncoderPosition());
    SmartDashboard.putNumber("Right encoder value meters", getRightEncoderPosition());
    SmartDashboard.putNumber("Gyro heading", getHeading());
  }
}
