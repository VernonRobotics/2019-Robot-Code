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
import frc.robot.commands.MoveJackMotor;
import frc.robot.CANTalon1989;
import frc.robot.JsScaled;

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
    //setDefaultCommand(new MoveJackMotor());
  }

  public void drive(double speed) {
    jackDrivenMotor.set(speed);
  }

  public void drive(JsScaled joystick) {
    RobotMap.jackDrivenMotor.set(joystick.sgetY());
  }

  public void stop() {
    jackDrivenMotor.stopMotor();
  }

  public boolean checkLimitSwitch(DigitalInput limitSwitch) {
    return limitSwitch.get();
  }

}
