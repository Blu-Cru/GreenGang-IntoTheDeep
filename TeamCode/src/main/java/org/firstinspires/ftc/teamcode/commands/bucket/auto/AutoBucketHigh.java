package org.firstinspires.ftc.teamcode.commands.bucket.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.transfer.TransferCommand;

/*
- Intakes piece (intake spins for 3 seconds)
- Transfers piece into claw
- Lifts slides, deposits piece into high bucket
- Resets robot state to init
 */
public class AutoBucketHigh extends SequentialCommandGroup {
    public AutoBucketHigh(){
        super(
                new SequentialCommandGroup(
                        new IntakeIntakeCommand(),
                        new IntakeInCommand(),
                        new WaitCommand(3000), // more?
                        new TransferCommand(),
                        new ScoringHighBucketCommand(),
                        new ResetCommand()
                )
        );
    }
}
