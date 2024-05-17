// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.subsystems.Drivetrain;

public class DriveJoystick extends Command {

  private final Drivetrain m_drivetrain;
  private final Joystick m_teamOneController, m_teamTwoController;
  private Timer totalTimer;
  private Timer teamTimer;

  /** Creates a new DriveJoystick. */
  public DriveJoystick(Drivetrain drivetrain, Joystick t1Con, Joystick t2Con) {
    m_drivetrain = drivetrain;
    m_teamOneController = t1Con;
    m_teamTwoController = t2Con;
    totalTimer = new Timer();
    teamTimer = new Timer();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    teamTimer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(teamTimer.get() < 31){
      m_drivetrain.drive(m_teamOneController.getX(), m_teamOneController.getY());
    }
    else if(teamTimer.get() < 61) {
      m_drivetrain.drive(m_teamTwoController.getX(), m_teamTwoController.getY());
    }
    if (teamTimer.get() > 61) {
      teamTimer.reset();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
