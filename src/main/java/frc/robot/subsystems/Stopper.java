// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.io.Console;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Stopper extends SubsystemBase {
  CANSparkMax stopper;
  RelativeEncoder encoder;
  ShuffleboardTab encoderTab;
  SimpleWidget encoderPosition;

  public Stopper() {
    stopper = new CANSparkMax(6, MotorType.kBrushless);
    stopper.setInverted(true);
    encoder = stopper.getEncoder();
    encoder.setPositionConversionFactor(0.0625);

    encoderTab = Shuffleboard.getTab("Encoder");
    encoderPosition = encoderTab.add("Encoder Position", encoder.getPosition());
  }

  public double getPosition() {
     return encoder.getPosition();
  }

  public void stopperSpeed(double speed) {
      stopper.set(speed);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}