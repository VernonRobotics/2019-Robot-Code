/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.CANTalon1989;
import frc.robot.JsScaled;
import frc.robot.RobotMap;
import frc.robot.commands.*;

/**
 * Add your docs here.
 */
public class Jacks extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private CANTalon1989 jackDrivenMotor = RobotMap.jackDrivenMotor;
  private CANTalon1989 frontJack = RobotMap.frontJack;
  private CANTalon1989 backJack = RobotMap.backJack;

  //public MotorSynchronization motorSync = new MotorSynchronization(frontJack, backJack);

  int frontJackEncoderValue;
  int backJackEncoderValue;

  int frontJackPreviousEncoderValue;
  int backJackPreviousEncoderValue;
  
  int delta;
  int overallDelta;

  int speed;
  int counter = 0;

  static double error = 0;
  static final double kP = 0.005;
  double powerChange;

  double frontJackSpeed;
  double backJackSpeed;
  
  @Override
  public void initDefaultCommand() {
    //setDefaultCommand();
  }

  public void driveForward(double speed) {
    jackDrivenMotor.set(speed);
  }

  public void moveJacks(JsScaled joystick) {
    RobotMap.jackDrivenMotor.set(joystick.sgetY());
  }


  public boolean checkLimitSwitch(DigitalInput limitSwitch) {
    if(limitSwitch.get()) {
      return false;
    } else {
      return true;
    }
  }

  public void moveJacksAuto(CANTalon1989 motor, double speed) {
    motor.set(speed);
  }

  public void calibrateJacks() {
    frontJack.setSelectedSensorPosition(0);
    backJack.setSelectedSensorPosition(0);
  }

  private void getEncoderValues() {
    frontJackEncoderValue = frontJack.getSelectedSensorPosition();
    backJackEncoderValue = backJack.getSelectedSensorPosition();
  }

  public void moveJacks(double frontJackSpeed, double backJackSpeed) {
    frontJack.set(frontJackSpeed);
    backJack.set(backJackSpeed);
  }
  
  public void synchronizedJacks(double speed) {
    if(counter == 0) {
      calibrateJacks();
    }
    
    getEncoderValues();
    int encoderDelta = frontJackEncoderValue - backJackEncoderValue;
    if(speed == 0) {
      frontJack.set(0);
      backJack.set(0);
    } else if(encoderDelta > 0) {
      encoderDelta = Math.abs(encoderDelta);
      backJack.set(speed-(encoderDelta * kP));
      frontJack.set(speed+(encoderDelta * kP));
    } else {
      encoderDelta = Math.abs(encoderDelta);
      backJack.set(speed+(encoderDelta * kP));
      frontJack.set(speed-(encoderDelta * kP));
    }

    counter = 1;

  }

  public void stopDrivenMotor() {
    jackDrivenMotor.stopMotor();
  }

  public void stopJacks() {
    backJack.set(0);
    frontJack.set(0);
    frontJack.stopMotor();
    backJack.stopMotor();
  }

}
