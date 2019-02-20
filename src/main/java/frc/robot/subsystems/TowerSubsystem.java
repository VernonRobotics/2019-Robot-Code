/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.CANTalon1989;
import frc.robot.JsScaled;
import frc.robot.RobotMap;
import frc.robot.commands.MoveTower;

/**
 * Add your docs here.
 */
public class TowerSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  CANTalon1989 tower = RobotMap.tower;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new MoveTower());
  }

  public void moveTower(JsScaled joystsick) {
    tower.set(joystsick.sgetX());
  }

  public void moveTowerAuto(double speed) {
    tower.set(speed);
  }

  public void stopTower() {
    tower.set(0);
  }

}