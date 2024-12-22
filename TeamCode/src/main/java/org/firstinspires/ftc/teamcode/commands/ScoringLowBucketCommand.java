package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeSpitCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeWrist.IntakeWristTransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesHighBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesLowBucketCommand;

public class ScoringLowBucketCommand extends SequentialCommandGroup {
    public ScoringLowBucketCommand(){
        super (
                new SequentialCommandGroup(
                        new OuttakeClawCloseCommand(),
                        new ClawArmBucketCommand(),
                        new VertSlidesLowBucketCommand(),
                        new WaitCommand(100),
                        new ClawWristBucketCommand()
                )
        );
    }
}
