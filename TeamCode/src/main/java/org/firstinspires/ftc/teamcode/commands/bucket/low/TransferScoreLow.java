package org.firstinspires.ftc.teamcode.commands.bucket.low;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.low.ScoringLowBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.transfer.TransferCommand;

/*
- performs transfer method (assumes sample in intake)
- Closes outtake claw
- lifts claw arm to bucket outtake pos
- lifts VS to low bucket
- adjusts claw wrist to bucket pos
 */

public class TransferScoreLow extends SequentialCommandGroup {
    public TransferScoreLow(){
        super(
                new SequentialCommandGroup(
                        new TransferCommand(),
                        new ScoringLowBucketCommand()
                )
        );
    }
}
