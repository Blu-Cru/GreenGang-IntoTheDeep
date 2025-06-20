package org.firstinspires.ftc.teamcode.greengang.common.commands.transfer;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.horizSlides.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.controls.intakeWrist.WristParallelCommand;
import org.firstinspires.ftc.teamcode.greengang.common.commands.outtake.OuttakeIntakeCommand;

/*
- gets all intake components (arm, wrist, bucket) to transfer
- gets all outtake components (claw, claw arm, claw wrist, slides) to transfer pos
 */
public class TransferCommand extends SequentialCommandGroup {
    public TransferCommand(){
        super (

                    new HorizontalSlidesRetractCommand(),
                    new WristParallelCommand(),
                    new OuttakeIntakeCommand()

        );
    }
}
