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
import frc.robot.commands.DriveJackMotorAuto;
import frc.robot.commands.LiftJacksAuto;

public class AutoLift extends CommandGroup {

  public AutoLift() {
    requires(Robot.jacks);
    requires(Robot.driveTrain);

    double liftTime = 3;
    double retractTime = 3;
    double liftSpeed = 0.5;
    double driveSpeed = 0.5;

    addParallel(new LiftJacksAuto(RobotMap.frontJack, liftSpeed), liftTime);
    addParallel(new LiftJacksAuto(RobotMap.backJack, liftSpeed), liftTime);
    //addSequential(new DriveJackMotorAuto(RobotMap.jackDrivenMotor, RobotMap.frontJackLimitSwitch, driveSpeed));
    addSequential(new LiftJacksAuto(RobotMap.frontJack, -liftSpeed), retractTime);
    //addSequential(new DriveJackMotorAuto(RobotMap.jackDrivenMotor, RobotMap.backJackLimitSwitch, driveSpeed));
    addSequential(new LiftJacksAuto(RobotMap.backJack, -liftSpeed), retractTime);
  }
  
}
