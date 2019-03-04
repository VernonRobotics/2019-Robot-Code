/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.CANTalon1989;

/**
 * Add your docs here.
 */
public class JacksDriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  CANTalon1989 jackDrivenMotor = RobotMap.jackDrivenMotor;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void drive(double speed) {
    jackDrivenMotor.set(speed);
  }

  public void stop() {
    jackDrivenMotor.stopMotor();
  }

  public boolean checkLimitSwitch(DigitalInput limitSwitch) {
    if(limitSwitch.get()) {
      return false;
    } else {
      return true;
    }
  }

}
