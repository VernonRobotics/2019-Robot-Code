/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.CANTalon1989;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DigitalInput;

public class DriveJackMotorAuto extends Command {

  CANTalon1989 motor;
  DigitalInput limitSwitch;
  double speed;

  public DriveJackMotorAuto(DigitalInput limitSwitch, double speed) {
    requires(Robot.jacksDriveTrain);
    this.limitSwitch = limitSwitch;
    this.speed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.jacksDriveTrain.drive(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.jacksDriveTrain.checkLimitSwitch(limitSwitch);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.jacksDriveTrain.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
