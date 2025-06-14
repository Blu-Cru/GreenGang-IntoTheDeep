package org.firstinspires.ftc.teamcode.greengang.common.commands.bucket.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.greengang.common.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.bucket.high.ScoringHighBucketCommand;
/*
- Lifts slides, deposits piece into low bucket
- Resets robot state to init
 */

public class AutoSamplePart2 extends SequentialCommandGroup {
    public AutoSamplePart2(){
        super(
                new SequentialCommandGroup(
                        new ScoringHighBucketCommand(),
                        new ResetCommand()
                )
        );
    }
}
