/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.command_group;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.*;

public class TestDriveAuto extends CommandGroup {
  /**
   * Add your docs here.
   */
  public TestDriveAuto() {
    addSequential(new DriveAuto(.5), 10);
  }
}
