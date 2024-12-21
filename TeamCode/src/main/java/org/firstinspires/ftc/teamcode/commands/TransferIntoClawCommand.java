package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.controls.IntakeSpitCommand;
import org.firstinspires.ftc.teamcode.commands.controls.IntakeWristTransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;

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
