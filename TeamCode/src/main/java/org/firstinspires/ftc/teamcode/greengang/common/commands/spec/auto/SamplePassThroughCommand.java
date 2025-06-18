package org.firstinspires.ftc.teamcode.greengang.common.commands.spec.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmBucketCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristBucketCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides.HorizontalSlidesExtendCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.transfer.TransferCommand;

/*

 */
public class SamplePassThroughCommand extends SequentialCommandGroup {
    public SamplePassThroughCommand() {
        super (
                new SequentialCommandGroup(
                        new HorizontalSlidesExtendCommand(),
                        new TransferCommand(),
                        new OuttakeClawCloseCommand(),
                        new ClawArmBucketCommand(),
//                        new ClawWristBucketCommand(),
                        new OuttakeClawOpenCommand()
                )
        );
    }

}
