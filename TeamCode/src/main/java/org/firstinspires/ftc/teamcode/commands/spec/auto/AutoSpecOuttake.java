package org.firstinspires.ftc.teamcode.commands.spec.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesHighSpecCommand;
import org.firstinspires.ftc.teamcode.commands.transfer.TransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesLowSpecCommand;

/*
- Closes claw
- Lifts slides to high specimen rung
- Lowers slides to low specimen rung
- opens outtake claw
- resets robot to init state
 */
public class AutoSpecOuttake extends SequentialCommandGroup {
    public AutoSpecOuttake() {
        super (
                new SequentialCommandGroup(
                        new ClawWristOutSpecCommand(),
                        new ClawArmOutSpecCommand(),
                        new VertSlidesHighSpecCommand(),
                        new WaitCommand(1000),
                        new VertSlidesLowSpecCommand(),
                        new OuttakeClawOpenCommand(),
                        new WaitCommand(1000),
                        new ResetCommand()
                        // bot drive away then reset
                )
        );
    }

}
