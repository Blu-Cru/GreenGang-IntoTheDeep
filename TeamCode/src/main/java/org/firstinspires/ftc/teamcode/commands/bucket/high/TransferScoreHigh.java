package org.firstinspires.ftc.teamcode.commands.bucket.high;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.transfer.TransferCommand;

/*
- Performs transfer method (assumes sample in intake)
- Closes claw
- Adjusts arm to bucket outtake position
- lifts slides to high bucket
- flicks wrist to face bucket
 */
public class TransferScoreHigh extends SequentialCommandGroup {
    public TransferScoreHigh(){
        super(
                new SequentialCommandGroup(
                        new TransferCommand(),
                        new ScoringHighBucketCommand()
                )
        );
    }
}
