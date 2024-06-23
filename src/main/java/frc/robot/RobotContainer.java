// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Auto;
import frc.robot.commands.Drive;
import frc.robot.commands.DriveBoost;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.MoveArms;
import frc.robot.commands.MoveArmsSeperate;
import frc.robot.subsystems.Arms;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.SliceLimelight;
import edu.wpi.first.util.concurrent.Event;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  ///private final Limelight m_Limelight = new Limelight();

  private final Drivetrain m_drivetrain = new Drivetrain(); // Drivetrain Subsystem defined
  private final Arms m_Arms = new Arms();
  

  private final CommandXboxController m_driveController = new CommandXboxController(0);
  //private final XboxController m_operatorController = new XboxController(1);


  private final Trigger m_MoveArmsTrigger = new Trigger(m_driveController.leftBumper());
  private final Trigger m_DriveBoostTrigger = new Trigger(m_driveController.rightTrigger());

  private final Drive m_drivecommand = new Drive(m_drivetrain, m_driveController); // Drive Command defined
  private final DriveBoost m_DriveBoostCommand = new DriveBoost(m_drivetrain, m_driveController);
  private final MoveArms m_MoveArms = new MoveArms(m_Arms);
  //private final MoveArmsSeperate m_MoveArmsSeperate = new MoveArmsSeperate(m_Arms, m_driveController, m_operatorJoystick);

  private final Auto m_Auto = new Auto(m_drivetrain);
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    m_drivetrain.setDefaultCommand(m_drivecommand);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {

    m_MoveArmsTrigger.toggleOnTrue(m_MoveArms);
    m_DriveBoostTrigger.whileTrue(m_DriveBoostCommand);

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_Auto;
  }
}
