// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Flywheels;
import frc.robot.subsystems.Stopper;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Bowl extends SequentialCommandGroup {
  private Flywheels flywheels;
  private Stopper stopper;

  /** Creates a new Bowl. */
  public Bowl() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new SpinFlywheels(flywheels), new StopperUp(stopper)); // Spin the flywheels and move the stopper up.
    try {
      wait(1000); // Wait for a second before putting it down again.
    } catch (InterruptedException e){}
    addCommands(new StopperDown(stopper)); // Put the stopper down.
  }
  
}
