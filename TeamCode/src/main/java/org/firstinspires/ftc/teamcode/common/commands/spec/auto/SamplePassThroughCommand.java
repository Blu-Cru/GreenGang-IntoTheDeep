package org.firstinspires.ftc.teamcode.common.commands.spec.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.common.commands.controls.clawArm.ClawArmBucketCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.clawWrist.ClawWristBucketCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.hs.HorizontalSlidesExtendCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.common.commands.transfer.TransferCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.outtakeClaw.OuttakeClawOpenCommand;

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
