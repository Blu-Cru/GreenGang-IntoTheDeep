package org.firstinspires.ftc.teamcode.common.commands.spec.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.common.commands.controls.clawArm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.clawWrist.ClawWristOutSpecCommand;
import org.firstinspires.ftc.teamcode.common.commands.controls.vs.VertSlidesHighSpecCommand;


public class AutoSpecOuttake extends SequentialCommandGroup {
    //first part of auto specimen outtake
    public AutoSpecOuttake() {
        super (
                new SequentialCommandGroup(

                        new VertSlidesHighSpecCommand(),
                        new WaitCommand(200),
                        new ClawWristOutSpecCommand(),
                        new ClawArmOutSpecCommand()
                )
        );
    }

}
