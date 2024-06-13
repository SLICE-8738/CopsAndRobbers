// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class Arms extends SubsystemBase {

  CANSparkMax leftArm, rightArm;
  RelativeEncoder leftArmEncoder, rightArmEncoder;
  SparkPIDController leftArmPIDs, rightArmPIDs;

  double startPosition;
  double targetPosition;

  /** Creates a new Arms. */
  public Arms() {
    leftArm = new CANSparkMax(0, MotorType.kBrushed);
    rightArm = new CANSparkMax(0, MotorType.kBrushed);

    leftArmEncoder = leftArm.getEncoder();
    rightArmEncoder = rightArm.getEncoder();

    startPosition = getPositionAverage();
    targetPosition = getPositionAverage();

    leftArmPIDs = leftArm.getPIDController();
    rightArmPIDs = rightArm.getPIDController();

    leftArmPIDs.setP(ArmConstants.ArmKP);
    leftArmPIDs.setI(ArmConstants.ArmKI);
    leftArmPIDs.setD(ArmConstants.ArmKD);
    rightArmPIDs.setP(ArmConstants.ArmKP);
    rightArmPIDs.setI(ArmConstants.ArmKI);
    rightArmPIDs.setD(ArmConstants.ArmKD);

  }

  /**
   * moves the drivetrain with manual speed inputs
   * @param moveLeft
   * @param moveRight
   */
  public void moveArms(double moveLeft, double moveRight){
    leftArm.set(moveLeft);
    rightArm.set(moveRight);
  }

  public double getPositionLeft() {
    return leftArmEncoder.getPosition();
  }

  public double getPositionRight() {
    return rightArmEncoder.getPosition();
  }

  public double getPositionAverage(){
    return (rightArmEncoder.getPosition() + leftArmEncoder.getPosition()) / 2;
  }

  public double[] getPositionBoth(){
    double[] both = {leftArmEncoder.getPosition(), rightArmEncoder.getPosition()};
    return both;
  }
  
  public void setPIDController(double positionSet){
    targetPosition = positionSet;
    
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
