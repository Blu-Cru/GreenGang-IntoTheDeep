package org.firstinspires.ftc.teamcode.commands.bucket.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.high.ScoringHighBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.hs.HorizontalSlidesExtendCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeInCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeBucket.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeWrist.WristDownCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.commands.transfer.TransferCommand;
/*
- Lifts slides, deposits piece into low bucket
- Resets robot state to init
 */

public class AutoSamplePart1 extends SequentialCommandGroup {
    public AutoSamplePart1(){
        super(
                new SequentialCommandGroup(
                        new SlidesLiftSlightlyCommand(),
                        new WristDownCommand(),
                        new IntakeInCommand(),
                        new HorizontalSlidesExtendCommand(),
                        new WaitCommand(3000), // change?
                        new IntakeStopCommand(),
                        new TransferCommand()
                )
        );
    }
}
