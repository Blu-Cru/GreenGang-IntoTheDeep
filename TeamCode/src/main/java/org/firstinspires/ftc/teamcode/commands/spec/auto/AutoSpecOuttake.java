package org.firstinspires.ftc.teamcode.commands.spec.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesHighSpecCommand;
import org.firstinspires.ftc.teamcode.commands.transfer.TransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesLowSpecCommand;


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
