package org.firstinspires.ftc.teamcode.commands.bucket.low;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesLowBucketCommand;
/*
- Closes outtake claw
- lifts claw arm to bucket outtake pos
- lifts VS to low bucket
- adjusts claw wrist to bucket pos
 */
public class ScoringLowBucketCommand extends SequentialCommandGroup {
    public ScoringLowBucketCommand(){
        super (
                new SequentialCommandGroup(
                        new OuttakeClawCloseCommand(),
                        new ClawArmBucketCommand(),
                        new ClawWristBucketCommand(),
                        new VertSlidesLowBucketCommand()
                )
        );
    }
}
