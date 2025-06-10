package org.firstinspires.ftc.teamcode.commands.spec.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.arm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.wrist.ClawWristOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vertSlides.VertSlidesHighSpecCommand;


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
