package org.firstinspires.ftc.teamcode.commands;

import android.util.Log;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.outtake.wrist.ClawWrist;

public class TransferHBucketCommand extends SequentialCommandGroup {
    public TransferHBucketCommand(){
        super (
                new SequentialCommandGroup(
                        new IntakeWristTransferCommand(),
                        new OuttakeClawOpenCommand(),
                        new IntakeSpitCommand(),
                        new WaitCommand(200),

                        new OuttakeClawCloseCommand(),
                        new VertSlidesHighLiftCommand(),
                        new WaitCommand(200),

                        new ClawArmBucketCommand(),
                        new ClawWristBucketCommand(),
                        new OuttakeClawOpenCommand(),
                        new WaitCommand(200),

                        new OuttakeClawCloseCommand(),
                        new VertSlidesLowerCommand(),
                        new WaitCommand(200),
                        new ClawWristIntakeCommand(),
                        new ClawArmIntakeCommand()
                )
        );

    }
}
