package org.firstinspires.ftc.teamcode.greengang.common.commands.bucket.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides.HorizontalSlidesExtendCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides.HorizontalSlidesExtendFullyCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeBucket.IntakeInCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeBucket.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeWrist.WristDownCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.transfer.TransferCommand;
/*
- Lifts slides, deposits piece into low bucket
- Resets robot state to init
 */

public class AutoSamplePart1 extends SequentialCommandGroup {
    public AutoSamplePart1(){
        super(
                new SequentialCommandGroup(
                        new SlidesLiftSlightlyCommand(),
                        new IntakeInCommand(),
                        new HorizontalSlidesExtendFullyCommand(),
                        new WristDownCommand(),

                        new WaitCommand(300), // change?
                        new HorizontalSlidesExtendCommand(),
                        new WaitCommand(2000), //orignial 3000
                        new IntakeStopCommand(),
                        new TransferCommand()
                )
        );
    }
}
