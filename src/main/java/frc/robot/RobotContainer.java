// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.Bowl;
import frc.robot.commands.Drive;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.FlywheelCommand;
import frc.robot.commands.SpinFlywheels;
import frc.robot.commands.StopperDown;
import frc.robot.commands.StopperUp;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Flywheels;
import frc.robot.subsystems.Stopper;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller.Button;
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

  private final Drivetrain m_drivetrain = new Drivetrain(); // Drivetrain Subsystem defined

  private final Flywheels m_flywheels = new Flywheels();

  private final Stopper m_Stopper = new Stopper();
  
  //private final CommandPS4Controller m_drivecontroller = new CommandPS4Controller(0);
  private final Joystick m_Joystick = new Joystick(0);

  private final Drive m_drivecommand = new Drive(m_drivetrain, m_Joystick); // Drive Command defined

  private final SpinFlywheels m_SpinFlywheels = new SpinFlywheels(m_flywheels);
  private final StopperUp m_StopperUp = new StopperUp(m_Stopper);
  private final StopperDown m_StopperDown = new StopperDown(m_Stopper);

  private final FlywheelCommand m_FlywheelCommand = new FlywheelCommand(m_flywheels);

  private final Bowl m_Bowl = new Bowl(m_flywheels, m_Stopper);

  //private final Trigger m_FlywheelsTrigger = new Trigger(m_drivecontroller.circle());
  //private final Trigger m_StopUpTrigger = new Trigger(m_drivecontroller.triangle());
  //private final Trigger m_StopDownTrigger = new Trigger(m_drivecontroller.square());
  //private final JoystickButton m_Trigger = new JoystickButton(m_Joystick, 1);
  private final JoystickButton m_FlywheelTrigger = new JoystickButton(m_Joystick, 1);
  //private final JoystickButton m_FlywheelButton = new JoystickButton(m_Joystick, 2);
  private final JoystickButton m_StopUpButton = new JoystickButton(m_Joystick, 3);
  private final JoystickButton m_StopDownButton = new JoystickButton(m_Joystick, 4);

  

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

    //m_FlywheelsTrigger.toggleOnTrue(m_SpinFlywheels);
    //m_StopUpTrigger.onTrue(m_StopperUp);
    //m_StopDownTrigger.onTrue(m_StopperDown);
    //m_Trigger.onTrue(m_Bowl);
    m_FlywheelTrigger.toggleOnTrue(m_FlywheelCommand);
    m_StopUpButton.onTrue(m_StopperUp);
    m_StopDownButton.onTrue(m_StopperDown);
      
    

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
