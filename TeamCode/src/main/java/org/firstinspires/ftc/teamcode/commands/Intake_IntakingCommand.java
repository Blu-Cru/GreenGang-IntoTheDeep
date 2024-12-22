package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.intakeArm.IntakeArmAssistCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeArm.IntakeArmIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeArm.IntakeArmParallelCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeArm.IntakeArmVerticalCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeWrist.IntakeWristIntakeCommand;

public class Intake_IntakingCommand extends SequentialCommandGroup {
    public Intake_IntakingCommand() {
        super(
                new SequentialCommandGroup(
                        new IntakeWristIntakeCommand(),
                        new IntakeArmVerticalCommand(),
                        new WaitCommand(100),
                        new IntakeArmParallelCommand()

                )
        );
    }
}
