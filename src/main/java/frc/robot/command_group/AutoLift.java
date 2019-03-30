/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.command_group;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.*;

public class AutoLift extends CommandGroup {

  public AutoLift() {

    final int jackHeight = 1700;
    final double jackHoldSpeed = -0.3;
    final double jackCalibrateSpeed = -0.2;
    final double liftTime = 3;
    final double retractTime = 2.6;
    final double liftSpeed = 0.5;
    final double driveSpeed = .4;

    //addSequential(new CalibrateJacks(jackCalibrateSpeed, jackCalibrateSpeed));
    //addSequential(new CalibrateJacks(jackCalibrateSpeed, jackCalibrateSpeed));
    addSequential(new SynchronizedJacks(-.8, jackHeight));
    addParallel(new SynchronizedJacks(jackHoldSpeed));
    addSequential(new DriveJackMotorAuto(RobotMap.frontJackLimitSwitch, driveSpeed));
    addParallel(new LiftJacksAuto(RobotMap.backJack, jackHoldSpeed));
    addSequential(new LiftJacksAuto(RobotMap.frontJack, .8, true));
    addParallel(new LiftJacksAuto(RobotMap.backJack, jackHoldSpeed));
    addSequential(new DriveJackMotorAuto(RobotMap.backJackLimitSwitch, driveSpeed));
    addSequential(new LiftJacksAuto(RobotMap.backJack, .8, true));
    //addSequential(new DriveAuto(0.1), 3);
  }
}
