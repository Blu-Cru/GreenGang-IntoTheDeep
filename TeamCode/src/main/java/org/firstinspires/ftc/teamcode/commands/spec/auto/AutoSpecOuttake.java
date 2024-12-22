package org.firstinspires.ftc.teamcode.commands.spec.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.spec.OutSpecCommand;
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
                        new OutSpecCommand(),
                        new WaitCommand(2000),
                        new VertSlidesLowSpecCommand(),
                        new WaitCommand(3000),
                        new OuttakeClawOpenCommand(),
                        new WaitCommand(3000),
                        new ResetCommand()
                )
        );
    }

}
