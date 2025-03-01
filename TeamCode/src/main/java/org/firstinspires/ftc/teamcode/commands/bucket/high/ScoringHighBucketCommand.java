package org.firstinspires.ftc.teamcode.commands.bucket.high;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesHighBucketCommand;
/*
- Closes claw
- Adjusts arm to bucket outtake position
- lifts slides to high bucket
- flicks wrist to face bucket
 */
public class ScoringHighBucketCommand extends SequentialCommandGroup {
    public ScoringHighBucketCommand(){
        super (
                new SequentialCommandGroup(
                        new OuttakeClawCloseCommand(),
                        new VertSlidesHighBucketCommand(),
                        new WaitCommand(500),
                        new ClawArmBucketCommand(),
                        new ClawWristBucketCommand()
                )
        );
    }
}
