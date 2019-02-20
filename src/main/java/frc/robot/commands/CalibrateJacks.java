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

public class CalibrateJacks extends Command {

  private double frontJackSpeed, backJackSpeed;

  public CalibrateJacks(double frontJackSpeed, double backJackSpeed) {
    // Use requires() here to declare subsystem dependencies
    this.frontJackSpeed = frontJackSpeed;
    this.backJackSpeed = backJackSpeed;
    requires(Robot.jacks);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.jacks.moveJacks(frontJackSpeed, backJackSpeed);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(RobotMap.frontJack.getOutputCurrent() > 3) {
      frontJackSpeed = 0;
    }
    if(RobotMap.backJack.getOutputCurrent() > 3) {
      backJackSpeed = 0;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(frontJackSpeed == 0 && backJackSpeed == 0) {
      Robot.jacks.calibrateJacks();
      return true;
    } else {
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.jacks.stopJacks();
    Robot.jacks.calibrateJacks();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
