package org.firstinspires.ftc.teamcode.commands.spec.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesHighSpecCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.intake.IntakeTransferCommand;
import org.firstinspires.ftc.teamcode.commands.transfer.TransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesLowSpecCommand;

/*

 */
public class SamplePassThroughCommand extends SequentialCommandGroup {
    public SamplePassThroughCommand() {
        super (
                new SequentialCommandGroup(
                        new IntakeIntakeCommand(),
                        new TransferCommand(),
                        new OuttakeClawCloseCommand(),
                        new ClawArmBucketCommand(),
                        new ClawWristBucketCommand(),
                        new OuttakeClawOpenCommand()
                )
        );
    }

}
