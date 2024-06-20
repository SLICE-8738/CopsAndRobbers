// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
  //current limit = 40 amphs
  public static final class DriveConstants{

    //public static final double kGearRatio = 1/36;
    //public static final double kWheelRadiusInches = 0;

    //public static final double kLinearDistanceConversionFactor = (Units.inchesToMeters(1/(kGearRatio * 2 * Math.PI*Units.inchesToMeters(kWheelRadiusInches)) * 10));

    public static final double kEncoderTick2Meter = 1.0 / 4096.0 * 0.128 * Math.PI;
    public static final int kLeftMotorPort = 0;
    public static final int kRightMotorPort = 1;


    }

  public static final class ArmConstants{

    public static final double ArmKP = 0;
    public static final double ArmKI = 0;
    public static final double ArmKD = 0;
    public static final double ArmFF = 0;

  }

  public static final class AutoConstants {
    public static final double kAutoDriveDistanceInches = 60;
    public static final double kAutoBackupDistanceInches = 20;
    public static final double kAutoDriveSpeed = 0.5;
  }

  public static final class PIDConstants {
    public static final double kP = 4.0; // TODO: tune the PID values
    public static final double kI = 2.0;
    public static final double kD = 2.0;
  }



  private static final double SELF_RIGHTING_ARM_ERROR_TOLERANCE = 0;
  public static final double kPSpeakerAlignRotation = 3.2;
  public static final double kISpeakerAlignRotation = 0;
  public static final double kDSpeakerAlignRotation = 0.8;
  public static double kPNoteAlignRotation = 3;
  public static double kINoteAlignRotation = 0;
  public static double kDNoteAlignRotation = 0;

}
