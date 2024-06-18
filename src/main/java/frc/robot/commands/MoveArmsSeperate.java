// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Arms;

public class MoveArmsSeperate extends Command {

  private Arms m_Arms;
  private Joystick m_driverJoystick, m_operatorJoystick;

  /** Creates a new MoveArmsSeperate. */
  public MoveArmsSeperate(Arms arm, Joystick dJ, Joystick oJ) {
    m_Arms = arm;
    m_driverJoystick = dJ;
    m_operatorJoystick = oJ;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Arms);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Arms.moveArms(m_driverJoystick.getX() / 4, m_operatorJoystick.getX() / 4);
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
