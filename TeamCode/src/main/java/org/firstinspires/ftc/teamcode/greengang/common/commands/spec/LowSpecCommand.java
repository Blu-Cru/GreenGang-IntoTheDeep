package org.firstinspires.ftc.teamcode.greengang.common.commands.spec;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristFlatCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret.TurretFlipCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.VertSlidesLowSpecCommand;

public class LowSpecCommand extends SequentialCommandGroup {
    public LowSpecCommand(){
        super (
                new VertSlidesLowSpecCommand(),
                new OuttakeClawCloseCommand(),
//                new ClawWristFlatCommand(),
                new ClawArmOutSpecCommand(),
                new TurretFlipCommand()
        );
    }
}
