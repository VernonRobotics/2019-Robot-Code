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
import frc.robot.RobotMap;

public class LiftJacksAuto extends Command {

  private CANTalon1989 jackMotor;
  private double speed;
  private boolean safetyEnabled = false;

  public LiftJacksAuto(CANTalon1989 jackMotor, double speed) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.jacks);
    this.jackMotor = jackMotor;
    this.speed = speed;
  }

  public LiftJacksAuto(CANTalon1989 jackMotor, double speed, boolean safetyEnabled) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.jacks);
    this.jackMotor = jackMotor;
    this.speed = speed;
    this.safetyEnabled = safetyEnabled;
  }
  
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.jacks.moveJacksAuto(jackMotor, speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(safetyEnabled && (RobotMap.frontJack.getSelectedSensorPosition() <= -5 && RobotMap.backJack.getSelectedSensorPosition() <= -5)){
      return true;
    } else {
      return false;
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
