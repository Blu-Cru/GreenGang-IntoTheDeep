package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristIntakeCommand;

public class ResetClawSubsCommand extends SequentialCommandGroup {
    public ResetClawSubsCommand(){
        super (
                new SequentialCommandGroup(
                        new ClawWristIntakeCommand(),
                        new ClawArmIntakeCommand()
                )
        );
    }
}
