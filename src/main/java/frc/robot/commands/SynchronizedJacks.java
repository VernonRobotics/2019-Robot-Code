/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SynchronizedJacks extends Command {

  private double speed;
  private int encoderGoal;

  public SynchronizedJacks(double speed, int encoderGoal) {
    // Use requires() here to declare subsystem dependencies
    this.speed = speed;
    this.encoderGoal = encoderGoal;
    requires(Robot.jacks);
  }

  public SynchronizedJacks(double speed) {
    // Use requires() here to declare subsystem dependencies
    this.speed = speed;
    requires(Robot.jacks);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.jacks.synchronizedJacks(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(encoderGoal == 0) {
      if(!RobotMap.frontJackLimitSwitch.get() || !RobotMap.backJackLimitSwitch.get()) {
        return true;
      } else {
        return false;
      }
    } else {
      if(RobotMap.frontJack.getSelectedSensorPosition() >= encoderGoal && RobotMap.backJack.getSelectedSensorPosition() >= encoderGoal) {
        return true;
      } else {
        return false;
      }
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.jacks.stopJacks();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
