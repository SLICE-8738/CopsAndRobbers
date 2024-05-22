// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arms extends SubsystemBase {

  CANSparkMax leftArm, rightArm;
  RelativeEncoder leftArmEncoder, rightArmEncoder;

  /** Creates a new Arms. */
  public Arms() {
    leftArm = new CANSparkMax(0, MotorType.kBrushed);
    rightArm = new CANSparkMax(0, MotorType.kBrushed);

    leftArmEncoder = leftArm.getEncoder();
    rightArmEncoder = rightArm.getEncoder();

  }

  public void moveArms(double moveLeft, double moveRight){
    leftArm.set(moveLeft);
    rightArm.set(moveRight);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
