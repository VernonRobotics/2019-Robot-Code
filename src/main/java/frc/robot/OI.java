/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.commands.*;
import frc.robot.command_group.*;

public class OI {

  private final int leftJoystickPort = 0;
  private final int rightJoystickPort = 1; 

  public JsScaled driveStick = new JsScaled(leftJoystickPort);
  public JsScaled utilityStick = new JsScaled(rightJoystickPort);

  public Button visionAssist = new JoystickButton(driveStick, 1);
  public Button activateJack = new JoystickButton(driveStick, 9);
  public Button setDefaultConfig = new JoystickButton(utilityStick, 8);
  public Button overrideAutomatedCommands = new JoystickButton(driveStick, 5);

  public Button hatchPanelControl = new JoystickButton(utilityStick, 1);
  public Button ballIntake = new JoystickButton(utilityStick, 2);
  public Button ballOutput = new JoystickButton(utilityStick, 3);

  //Test Jacks
  public Button allJacksRetract = new JoystickButton(utilityStick, 9);
  public Button allJacksDown = new JoystickButton(utilityStick, 10);
  public Button frontJacksRetract = new JoystickButton(utilityStick, 11);
  public Button backJacksRetract = new JoystickButton(utilityStick, 12);

  public Button frontJacksDown = new JoystickButton(utilityStick, 7);
  public Button backJacksDown = new JoystickButton(utilityStick, 8);

  public Button calibrate = new JoystickButton(utilityStick, 2);

  public OI() {
    visionAssist.whileHeld(new AssistedDrive());
    activateJack.whenPressed(new AutoLift());
    //setDefaultConfig.whenPressed(new Command);
    overrideAutomatedCommands.cancelWhenPressed(new AutoLift());

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

    frontJacksDown.whenPressed(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, -1, 0));
    backJacksDown.whenPressed(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, -1));
    frontJacksDown.whenReleased(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, 0));
    backJacksDown.whenReleased(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, 0));
    
    //allJacksDown.whenPressed(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, -1, -1));
    //allJacksDown.whenReleased(new MoveJacksOnButton(RobotMap.frontJack, RobotMap.backJack, 0, 0));

    allJacksDown.whenPressed(new SynchronizedJacks(-.8));
    allJacksDown.whenReleased(new SynchronizedJacks(0));

    allJacksRetract.whenPressed(new SynchronizedJacks(.8));
    allJacksRetract.whenPressed(new SynchronizedJacks(0));

    calibrate.whileHeld(new CalibrateJacks());

    hatchPanelControl.whileHeld(new ControlSolenoid("forward"));
    ballIntake.whileHeld(new BallMechanism(1));
    ballOutput.whileHeld(new BallMechanism(-1));

  }

}
