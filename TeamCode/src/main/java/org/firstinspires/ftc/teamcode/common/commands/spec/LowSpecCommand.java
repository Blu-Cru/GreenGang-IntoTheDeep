package org.firstinspires.ftc.teamcode.common.commands.spec;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.common.commands.controls.clawArm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.clawWrist.ClawWristOutSpecCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.vs.VertSlidesLowSpecCommand;

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
