// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

<<<<<<< HEAD
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.ADIS16448_IMU;
=======
import edu.wpi.first.wpilibj.Joystick;
>>>>>>> 09d5733dcff1669d6aec4e102eee711ed7625325
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;

public class Drive extends Command {

  private final Drivetrain m_drivetrain;
<<<<<<< HEAD
  private final CommandPS4Controller m_driverController;
  public static final AHRS navX = AHRS(SPI.Port.)
=======
  //private final CommandPS4Controller m_driverController;
  private final Joystick m_Joystick;

>>>>>>> 09d5733dcff1669d6aec4e102eee711ed7625325
  /** Creates a new Drive. */
  public Drive(Drivetrain drivetrain, Joystick joy) {
    m_drivetrain = drivetrain;
    m_Joystick = joy;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.drive(m_Joystick.getY(), m_Joystick.getX());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
