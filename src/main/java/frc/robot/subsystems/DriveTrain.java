/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.CANTalon1989;
import frc.robot.JsScaled;
import frc.robot.RobotMap;
import frc.robot.commands.Drive;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  
  private CANTalon1989 frontLeft;
  private CANTalon1989 frontRight;
  private CANTalon1989 backLeft;
  private CANTalon1989 backRight;

  private double xSpeed;
  private double ySpeed;
  private double zRotation;
  private double gyroAngle;

  //private ADXRS450_Gyro gyro = RobotMap.gyro;

  private MecanumDrive mDrive;

  static double error = 0;
  static double kP = 0.00975;

  public DriveTrain(CANTalon1989 frontLeft, CANTalon1989 backLeft, CANTalon1989 frontRight, CANTalon1989 backRight) {
    this.frontLeft = frontLeft;
    this.frontRight = frontRight;
    this.backLeft = backLeft;
    this.backRight = backRight;
    mDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);
    mDrive.setSafetyEnabled(true);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new Drive());
  }

  public void drive(JsScaled joy) {
    getJoystickValues(joy);
    if(xSpeed == 0 && zRotation == 0) {
      //gyroAngle = gyro.getAngle();
      error = -gyroAngle;
      zRotation = kP * error;
    }
    mDrive.driveCartesian(xSpeed, ySpeed, zRotation);
  }

  public void visionAssistedDrive(JsScaled joy, double computerYSpeed, double angle) {
    getJoystickValues(joy);
    //gyroAngle = gyro.getAngle(); 
    xSpeed = computerYSpeed;
    zRotation = angle;
    mDrive.driveCartesian(xSpeed, ySpeed, zRotation);
  }

  private void getJoystickValues(JsScaled joy) {
    ySpeed = joy.sgetY();
    xSpeed = -joy.sgetX();
    zRotation = -joy.sgetTwist();
  }

  public void rotate(double angle) {
    //error = angle - gyro.getAngle();
    mDrive.driveCartesian(0, 0, error*kP);
  }

  public void stop() {
    frontLeft.set(0);
    frontRight.set(0);
    backLeft.set(0);
    backRight.set(0);
  }

}
