package org.firstinspires.ftc.teamcode.greengang.common.commands.bucket.high;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmBucketCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawLooseCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristBucketCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.VertSlidesHighBucketCommand;

/*
- Closes claw
- Adjusts arm to bucket outtake position
- lifts slides to high bucket
- flicks wrist to face bucket
 */
public class ScoringHighBucketCommand extends SequentialCommandGroup {
    public ScoringHighBucketCommand(){
        super (
                    new OuttakeClawLooseCloseCommand(),
                    new VertSlidesHighBucketCommand(),
                    new WaitCommand(500),
                    new ClawArmBucketCommand(),
                    new ClawWristBucketCommand()
        );
    }
}
