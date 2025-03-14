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
public class AutoSpecOuttake2 extends SequentialCommandGroup{
    public AutoSpecOuttake2(){
        super(
                new SequentialCommandGroup(
                        new VertSlidesLowSpecCommand(),
                        new WaitCommand(2000),
                        new OuttakeClawOpenCommand()
                )





        );

    }


}
