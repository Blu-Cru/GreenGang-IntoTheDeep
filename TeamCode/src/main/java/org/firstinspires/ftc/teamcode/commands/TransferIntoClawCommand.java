package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

public class TransferIntoClawCommand extends SequentialCommandGroup {
    public TransferIntoClawCommand(){
        super (
                new SequentialCommandGroup(
                        new IntakeWristTransferCommand(),
                        new OuttakeClawOpenCommand(),
                        new IntakeSpitCommand()
                )
        );
    }
}
