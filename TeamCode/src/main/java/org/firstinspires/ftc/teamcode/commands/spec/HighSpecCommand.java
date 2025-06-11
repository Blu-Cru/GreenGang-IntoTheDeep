package org.firstinspires.ftc.teamcode.commands.spec;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.controls.arm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.wrist.ClawWristOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vertSlides.VertSlidesHighSpecCommand;

public class HighSpecCommand extends SequentialCommandGroup {
    public HighSpecCommand(){
        super (
                new SequentialCommandGroup(
                        new VertSlidesHighSpecCommand(),
                        new ClawWristOutSpecCommand(),
                        new ClawArmOutSpecCommand()
                )
        );
    }
}
