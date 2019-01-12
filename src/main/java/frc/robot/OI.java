/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.commands.AssistedDrive;

public class OI {

  private final int leftJoystickPort = 0;
  private final int rightJoystickPort = 1; 

  public JsScaled driveStick = new JsScaled(leftJoystickPort);
  public JsScaled utilityStick = new JsScaled(rightJoystickPort);

  public Button visionAssist = new JoystickButton(driveStick, 1);

  public OI() {
    visionAssist.whileHeld(new AssistedDrive());
  }

}
