package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesStartCommand;

public class Outtake_IntakingCommand extends SequentialCommandGroup {
    public Outtake_IntakingCommand() {
        super(
                new SequentialCommandGroup(
                        new ClawWristIntakeCommand(),
                        new VertSlidesStartCommand(),
                        new OuttakeClawOpenCommand(),
                        new ClawArmIntakeCommand()

                )
        );
    }
}
