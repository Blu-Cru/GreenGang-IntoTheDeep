package org.firstinspires.ftc.teamcode.greengang.common.commands.spec;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.claw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristLowOutSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret.TurretFlipCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.VertSlidesLowSpecCommand;

public class LowSpecCommand extends SequentialCommandGroup {
    public LowSpecCommand(){
        super (
                new SequentialCommandGroup(
                        new VertSlidesLowSpecCommand(),
                        new OuttakeClawCloseCommand(),
                        new ClawWristLowOutSpecCommand(),
                        new ClawArmOutSpecCommand(),
                        new TurretFlipCommand()


                )
        );
    }
}
