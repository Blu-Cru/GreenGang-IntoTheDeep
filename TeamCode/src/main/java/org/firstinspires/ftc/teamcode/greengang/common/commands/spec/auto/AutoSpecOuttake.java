package org.firstinspires.ftc.teamcode.greengang.common.commands.spec.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristHighOutSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist.ClawWristLowOutSpecCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.vertSlides.VertSlidesHighSpecCommand;


public class AutoSpecOuttake extends SequentialCommandGroup {
    //first part of auto specimen outtake
    public AutoSpecOuttake() {
        super (
                new SequentialCommandGroup(

                        new VertSlidesHighSpecCommand(),
                        new WaitCommand(200),
                        new ClawWristHighOutSpecCommand(),
                        new ClawArmOutSpecCommand()
                )
        );
    }

}
