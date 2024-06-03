// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import frc.robot.subsystems.Arms;

public class MoveArms extends Command {

  private final Arms m_Arms;
  //CommandPS4Controller m_OperatorController;
  private final Joystick m_Joystick;

  /** Creates a new MoveArms. */
  public MoveArms(Arms a, Joystick joy) {
    m_Arms = a;
    m_Joystick = joy;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Arms);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Arms.moveArms(m_Joystick.getY(), m_Joystick.getY());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Arms.moveArms(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
