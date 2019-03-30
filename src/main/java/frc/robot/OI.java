/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.commands.*;
import frc.robot.command_group.*;
import frc.robot.Robot;

public class OI {

  private final int leftJoystickPort = 0;
  private final int rightJoystickPort = 1; 

  public JsScaled driveStick = new JsScaled(leftJoystickPort);
  public JsScaled utilityStick = new JsScaled(rightJoystickPort);

  //public Button visionAssist = new JoystickButton(driveStick, 1);
  public Button autoJack = new JoystickButton(driveStick, 7);
  public Button setDefaultConfig = new JoystickButton(utilityStick, 8);
  //public Button overrideAutomatedJacks = new JoystickButton(driveStick, 1);
  //public Button towerHalfSpeed = new JoystickButton(utilityStick, 3);

  //public Button hatchPanelControl = new JoystickButton(utilityStick, 1);
  public Button ballIntake = new JoystickButton(utilityStick, 1);
  public Button ballOutput = new JoystickButton(utilityStick, 2);
  //public Button ballHalfSpeed = new JoystickButton(utilityStick, 4);

  //Test Jacks
  //public Button allJacksRetract = new JoystickButton(utilityStick, 9);
  public Button allJacksDown = new JoystickButton(utilityStick, 10);
  public Button allJacksHold = new JoystickButton(utilityStick, 9);
  public Button frontJacksRetract = new JoystickButton(utilityStick, 11);
  public Button backJacksRetract = new JoystickButton(utilityStick, 12);

  //public Button frontJacksDown = new JoystickButton(utilityStick, 7);
  public Button backJacksDown = new JoystickButton(utilityStick, 8);

  public Button calibrateJacks = new JoystickButton(driveStick, 5);
  public Button calibrateTower = new JoystickButton(utilityStick, 5);

  public Button driveJacksForward = new JoystickButton(driveStick, 3);
  public Button driveJacksBackward = new JoystickButton(driveStick, 4);

  public Button stageOne = new JoystickButton(driveStick, 7);
  public Button stageTwo = new JoystickButton(driveStick, 8);
  public Button stageThree = new JoystickButton(driveStick, 9);
  public Button stageFour = new JoystickButton(driveStick, 10);
  public Button stageFive = new JoystickButton(driveStick, 11);
  public Button stageEmergency = new JoystickButton(driveStick, 12);

  public OI() {
    //visionAssist.whileHeld(new AssistedDrive());
    //AutoLift autoLift = new AutoLift();

    autoJack.whenPressed(new AutoLift());
    //setDefaultConfig.whenPressed(new Command);
    //overrideAutomatedJacks.cancelWhenPressed(autoLift);

    // Test Jacks
    /*allJacksUp.whileHeld(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, .5, .5));
    frontJacksRetract.whileHeld(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, -.5, 0));
    backJacksRetract.whileHeld(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, -.5));*/
  
    //allJacksRetract.whenPressed(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, .8, .8));
    frontJacksRetract.whenPressed(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, .5, 0));
    backJacksRetract.whenPressed(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, .5));

    //allJacksRetract.whenReleased(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, 0));
    frontJacksRetract.whenReleased(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, 0));
    backJacksRetract.whenReleased(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, 0));

    //frontJacksDown.whenPressed(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, -1, 0));
    backJacksDown.whenPressed(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, -.3));
    //frontJacksDown.whenReleased(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, 0));
    backJacksDown.whenReleased(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, 0));
    
    //allJacksDown.whenPressed(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, -1, -1));
    //allJacksDown.whenReleased(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, 0));

    allJacksDown.whenPressed(new SynchronizedJacks(-.8));
    allJacksDown.whenReleased(new SynchronizedJacks(0));

    allJacksHold.whenPressed(new SynchronizedJacks(-.3));
    allJacksHold.whenReleased(new SynchronizedJacks(0));

    //allJacksRetract.whenPressed(new SynchronizedJacks(.8));
    //allJacksRetract.whenPressed(new SynchronizedJacks(0));

    calibrateJacks.whenPressed(new CalibrateJacks(-.4, -.4));
    calibrateJacks.whenReleased(new CalibrateJacks(0, 0));

    //hatchPanelControl.whenPressed(new ControlSolenoid("forward"));
    //hatchPanelControl.whenReleased(new ControlSolenoid("reverse"));

    ballIntake.whenPressed(new BallMechanism(.8));
    ballIntake.whenReleased(new BallMechanism(0));

    ballOutput.whenPressed(new BallMechanism(-.8));
    ballOutput.whenReleased(new BallMechanism(0));

    stageOne.whenPressed(new SynchronizedJacks(-.8));
    stageOne.whenReleased(new SynchronizedJacks(0));
    stageTwo.whenPressed(new SynchronizedJacks(-.3));
    stageTwo.whenReleased(new SynchronizedJacks(0));
    stageThree.whenPressed(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, .5, -.3));
    stageThree.whenReleased(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, 0));
    stageFour.whenPressed(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, -.3));
    stageFour.whenReleased(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, 0));
    stageFive.whenPressed(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, .5));
    stageFive.whenReleased(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, 0));
    stageEmergency.whenPressed(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, -.5));
    stageEmergency.whenReleased(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, 0));

    driveJacksForward.whenPressed(new DriveJacks(0.3));
    driveJacksForward.whenReleased(new DriveJacks(0));
    driveJacksBackward.whenPressed(new DriveJacks(-0.3));
    driveJacksBackward.whenReleased(new DriveJacks(0));

    //towerHalfSpeed.whenPressed(new SetTowerSpeed(.5));
    //towerHalfSpeed.whenReleased(new SetTowerSpeed(1));

    //ballHalfSpeed.toggleWhenPressed(new SetBallSpeed());;

  }

}
