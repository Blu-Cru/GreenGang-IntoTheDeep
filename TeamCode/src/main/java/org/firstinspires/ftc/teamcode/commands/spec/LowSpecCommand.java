package org.firstinspires.ftc.teamcode.commands.spec;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.controls.arm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.wrist.ClawWristOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vertSlides.VertSlidesLowSpecCommand;

public class LowSpecCommand extends SequentialCommandGroup {
    public LowSpecCommand(){
        super (
                new SequentialCommandGroup(
                        new VertSlidesLowSpecCommand(),
                        new ClawWristOutSpecCommand(),
                        new ClawArmOutSpecCommand()
                )
        );
    }
}
