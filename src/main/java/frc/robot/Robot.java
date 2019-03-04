/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
  public static DriveTrain driveTrain = new DriveTrain(RobotMap.frontLeft, RobotMap.backLeft, RobotMap.frontRight, RobotMap.backRight);
  public static Jacks jacks = new Jacks();
  public static JacksDriveTrain jacksDriveTrain = new JacksDriveTrain();
  public static TowerSubsystem towerSubsystem = new TowerSubsystem();
  public static ArmSubsystem armSubsystem = new ArmSubsystem();
  public static OI oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  NetworkTableInstance inst;
  NetworkTable visionTable;
  NetworkTableEntry robotState;
  NetworkTableEntry yEntry;
  NetworkTableEntry angleEntry;

  NetworkTableInstance cameraInstance;
  NetworkTable cameraTable;
  UsbCamera camera1;
  UsbCamera camera2;
  boolean prevTrigger = false;

  private static double y;
  private static double angle;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    y = 0;
    angle = 0;
    oi = new OI();
    //RobotMap.gyro.calibrate();
    RobotMap.backRight.setInverted(true);
    RobotMap.frontRight.setInverted(true);

    RobotMap.backLeft.setInverted(true);
    RobotMap.frontLeft.setInverted(true);

    RobotMap.frontJack.setNeutralMode(NeutralMode.Brake);
    RobotMap.backJack.setNeutralMode(NeutralMode.Brake);

    RobotMap.tower.setNeutralMode(NeutralMode.Brake);

    inst = NetworkTableInstance.getDefault();
    visionTable = inst.getTable("VisionTable");
    robotState = visionTable.getEntry("RobotState");
    yEntry = visionTable.getEntry("Y");
    angleEntry = visionTable.getEntry("Angle");
    inst.startClientTeam(1989);

    Thread networkTableThread = new Thread(() -> {

      while (!Thread.interrupted()) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException ex) {
          return;
        }
        y = yEntry.getDouble(0.0);
        angle = angleEntry.getDouble(0.0);
        //System.out.println(y + " " + angle);
      }
    });
    networkTableThread.start();

    //camera1 = CameraServer.getInstance().startAutomaticCapture(0);
    //camera2 = CameraServer.getInstance().startAutomaticCapture(1);
    
    /*camera1 = new UsbCamera("Front Camera", 0);
    MjpegServer mjpegServer1 = new MjpegServer("serve_USB Camera",  1181);
    mjpegServer1.setSource(camera1);*/


    /*new Thread(() -> {
      camera1 = CameraServer.getInstance().startAutomaticCapture(0);
      camera1.setResolution(640, 480);
      CvSink cvSink1 = CameraServer.getInstance().getVideo();

      camera2 = CameraServer.getInstance().startAutomaticCapture(1);
      camera2.setResolution(640, 480);
      CvSink cvSink2 = CameraServer.getInstance().getVideo();
      


      while(!Thread.interrupted()) {

      }

      cameraInstance = NetworkTableInstance.getDefault();
      cameraTable = cameraInstance.getTable("");
      
      
    });*/

    //m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    //SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    if(oi.driveStick.getRawButton(11)) {
      
    } else {

    }
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
    robotState.setString("disabled");
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    robotState.setString("autonomous");
    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    
    robotState.setString("teleop");
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    //System.out.print(RobotMap.jackDrivenMotor.getSelectedSensorPosition());
    //System.out.print(RobotMap.frontJack.getOutputCurrent());
    System.out.print(" " + RobotMap.frontJack.getSelectedSensorPosition());
    System.out.print(" " + RobotMap.backJack.getSelectedSensorPosition()+ "\n");
    //System.out.println(" " + RobotMap.frontJack.get() + " " + RobotMap.backJack.get() + " ");
    /*if(RobotMap.frontJack.getOutputCurrent() > 2 || RobotMap.backJack.getOutputCurrent() > 2) {
      System.out.print(RobotMap.frontJack.getOutputCurrent()+" ");
      System.out.println(RobotMap.backJack.getOutputCurrent());
    }*/

    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  public static double getAngle() {
    return angle;
  }

  public static double getY() {
    return y;
  }

}
