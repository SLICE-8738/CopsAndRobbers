// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.revrobotics.CANSparkBase;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;

public class Drive extends Command {

  private final Drivetrain m_drivetrain;
  private final CommandPS4Controller m_teamOneController, m_teamTwoController;
  private Timer totalTimer;
  private Timer teamTimer;

  

  /** Creates a new Drive. */
  public Drive(Drivetrain drivetrain, CommandPS4Controller t1Con, CommandPS4Controller t2Con) {

    m_drivetrain = drivetrain;
    m_teamOneController = t1Con;
    m_teamTwoController = t2Con;

    totalTimer = new Timer();
    teamTimer = new Timer();

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    totalTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //m_drivetrain.drive(m_driverController.getRightX(), m_driverController.getLeftY());
    while(totalTimer.get() < 301){
      teamTimer.start();
      while(teamTimer.get() < 31){
        m_drivetrain.drive(m_teamOneController.getRightX(), m_teamTwoController.getLeftY());
      }
      m_drivetrain.drive(0, 0);
      while(teamTimer.get() < 61) {
        m_drivetrain.drive(m_teamTwoController.getRightX(), m_teamTwoController.getLeftY());
      }
      m_drivetrain.drive(0, 0);
      teamTimer.reset();
    }
    totalTimer.stop();
    totalTimer.reset();
    isFinished();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
