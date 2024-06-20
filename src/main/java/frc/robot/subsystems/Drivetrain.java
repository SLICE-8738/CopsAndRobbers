// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.PIDConstants;

public class Drivetrain extends SubsystemBase {

  private CANSparkMax frontLeft, frontRight, backLeft, backRight;
  private RelativeEncoder frontLeftEncoder, frontRightEncoder, backLeftEncoder, backRightEncoder;
  private DifferentialDrive driveTrain;


  /** Creates a new Drivetrain. */
  public Drivetrain() {

    frontLeft = new CANSparkMax(15, MotorType.kBrushless);
    frontRight = new CANSparkMax(17, MotorType.kBrushless);
    backLeft = new CANSparkMax(3, MotorType.kBrushless);
    backRight = new CANSparkMax(18, MotorType.kBrushless);

    frontLeft.setSmartCurrentLimit(35);
    frontRight.setSmartCurrentLimit(35);
    backLeft.setSmartCurrentLimit(35);
    backRight.setSmartCurrentLimit(35);

    frontLeftEncoder = frontLeft.getEncoder();
    frontRightEncoder = frontRight.getEncoder();
    backLeftEncoder = backLeft.getEncoder();
    backRightEncoder = backRight.getEncoder();

    backLeft.follow(frontLeft);
    backRight.follow(frontRight);

    driveTrain = new DifferentialDrive(frontLeft, frontRight);
  }

  public void drive(double forwardSpeed, double rotationSpeed){
    driveTrain.arcadeDrive(forwardSpeed, rotationSpeed);
  }
    
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
}
