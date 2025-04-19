package org.firstinspires.ftc.teamcode.common.commands.spec;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.common.commands.controls.clawArm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.clawWrist.ClawWristOutSpecCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.vs.VertSlidesHighSpecCommand;

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
