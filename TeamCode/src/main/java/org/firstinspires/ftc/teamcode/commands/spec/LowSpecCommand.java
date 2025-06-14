package org.firstinspires.ftc.teamcode.commands.spec;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.controls.arm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristLowOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vertSlides.VertSlidesLowSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.turret.TurretFlipCommand;

public class LowSpecCommand extends SequentialCommandGroup {
    public LowSpecCommand(){
        super (
                new SequentialCommandGroup(
                        new VertSlidesLowSpecCommand(),
                        new ClawWristLowOutSpecCommand(),
                        new ClawArmOutSpecCommand(),
                        new TurretFlipCommand()


                )
        );
    }
}
