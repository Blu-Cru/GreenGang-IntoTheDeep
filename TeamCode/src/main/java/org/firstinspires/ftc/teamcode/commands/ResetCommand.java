package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeArm.IntakeArmTransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeWrist.IntakeWristTransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesStartCommand;

/*
resets all subsystems to how they were in initialization state
 */
public class ResetCommand extends SequentialCommandGroup {
    public ResetCommand(){
        super (
                new SequentialCommandGroup(
                        new ClawArmIntakeCommand(),
                        new ClawWristIntakeCommand(),
                        new IntakeArmTransferCommand(),
                        new IntakeWristTransferCommand(),
                        new OuttakeClawCloseCommand(),
                        new VertSlidesStartCommand()
                )
        );
    }
}
