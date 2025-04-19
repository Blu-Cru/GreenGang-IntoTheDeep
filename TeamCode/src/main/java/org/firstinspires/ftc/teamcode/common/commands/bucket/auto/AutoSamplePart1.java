package org.firstinspires.ftc.teamcode.common.commands.bucket.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.common.commands.controls.hs.HorizontalSlidesExtendCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.hs.HorizontalSlidesExtendHalfwayCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.intake.IntakeInCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.intake.IntakeStopCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.intakeWrist.WristDownCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.vs.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.common.commands.transfer.TransferCommand;
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
                        new HorizontalSlidesExtendHalfwayCommand(),
                        new WristDownCommand(),

                        new WaitCommand(300), // change?
                        new HorizontalSlidesExtendCommand(),
                        new WaitCommand(1800), //orignial 3000
                        new IntakeStopCommand(),
                        new TransferCommand()
                )
        );
    }
}
