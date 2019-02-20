/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.CANTalon1989;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ArmSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private CANTalon1989 ballMotor = RobotMap.ballMotor;
  private Relay hatchPanelSolenoid = RobotMap.hatchPanelSolenoid;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void controlSolenoid(String status) {
    if(status == "forward") {
      hatchPanelSolenoid.set(Relay.Value.kOn);
    } else if(status == "reverse") {
      hatchPanelSolenoid.set(Relay.Value.kOff);
    } 
  }

  public void ballMechanism(double speed) {
    ballMotor.set(speed);
  }

  public void stop() {
    ballMotor.stopMotor();
  }

}
