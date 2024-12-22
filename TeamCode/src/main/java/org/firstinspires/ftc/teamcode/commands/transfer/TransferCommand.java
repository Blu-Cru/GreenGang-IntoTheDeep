package org.firstinspires.ftc.teamcode.commands.transfer;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.intake.IntakeTransferCommand;
import org.firstinspires.ftc.teamcode.commands.outtake.OuttakeIntakeCommand;

/*
- gets all intake components (arm, wrist, bucket) to transfer
- gets all outtake components (claw, claw arm, claw wrist, slides) to transfer pos
 */
public class TransferCommand extends SequentialCommandGroup {
    public TransferCommand(){
        super (
                new SequentialCommandGroup(
                        new IntakeTransferCommand(),
                        new OuttakeIntakeCommand()
                )
        );
    }
}
