package org.firstinspires.ftc.teamcode.commands.spec.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.controls.arm.ClawArmBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.wrist.ClawWristBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.horizSlides.HorizontalSlidesExtendCommand;
import org.firstinspires.ftc.teamcode.commands.controls.claw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.transfer.TransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.claw.OuttakeClawOpenCommand;

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
                        new ClawWristBucketCommand(),
                        new OuttakeClawOpenCommand()
                )
        );
    }

}
