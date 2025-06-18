package org.firstinspires.ftc.teamcode.greengang.common.commands.bucket.low;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmBucketCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawLooseCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristBucketCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret.TurretTurn90Command;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.VertSlidesLowBucketCommand;

/*
- Closes outtake claw
- lifts claw arm to bucket outtake pos
- lifts VS to low bucket
- adjusts claw wrist to bucket pos
 */
public class ScoringLowBucketCommand extends SequentialCommandGroup {
    public ScoringLowBucketCommand(){
        super (
                new VertSlidesLowBucketCommand(),
                new ClawArmBucketCommand(),
//                new ClawWristBucketCommand(),
                new TurretTurn90Command()
        );
    }
}
