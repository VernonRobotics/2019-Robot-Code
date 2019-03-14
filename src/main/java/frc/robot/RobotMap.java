/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;

public class RobotMap {

	//Talons for drivetrain
  	public static CANTalon1989 frontLeft = new CANTalon1989(6);
	public static CANTalon1989 backLeft = new CANTalon1989(7);
	public static CANTalon1989 frontRight = new CANTalon1989(9);
	public static CANTalon1989 backRight = new CANTalon1989(1);

	//Talons for jack
	public static CANTalon1989 frontJack = new CANTalon1989(5);
	public static CANTalon1989 backJack = new CANTalon1989(3);

	public static CANTalon1989 tower = new CANTalon1989(2);

	//public static Relay hatchPanelSolenoid = new Relay(0);
	public static CANTalon1989 ballMotor = new CANTalon1989(8);
	
	//Talon for jack motor
	public static CANTalon1989 jackDrivenMotor = new CANTalon1989(4);
	
	//public static ADXRS450_Gyro gyro = new ADXRS450_Gyro(); 
	
	//Limit switches for jacks
	public static DigitalInput frontJackLimitSwitch = new DigitalInput(0);
	public static DigitalInput backJackLimitSwitch = new DigitalInput(1);

	/*public Encoder frontJackEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	public Encoder backJackEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	public Encoder backDrivenJackEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);*/
  
}
