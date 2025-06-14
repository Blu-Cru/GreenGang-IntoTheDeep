package org.firstinspires.ftc.teamcode.commands.spec;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.controls.arm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristHighOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vertSlides.VertSlidesHighSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.turret.TurretFlipCommand;

public class HighSpecCommand extends SequentialCommandGroup {
    public HighSpecCommand(){
        super (
                new SequentialCommandGroup(
                        new VertSlidesHighSpecCommand(),
                        new ClawWristHighOutSpecCommand(),
                        new ClawArmOutSpecCommand(),
                        new TurretFlipCommand()
                )
        );
    }
}
