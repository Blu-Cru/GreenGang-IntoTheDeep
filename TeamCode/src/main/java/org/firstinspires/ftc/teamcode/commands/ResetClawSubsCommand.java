package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

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
