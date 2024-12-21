package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;

public class DepositHighBucketCommand extends SequentialCommandGroup {
    public DepositHighBucketCommand(){
        super (
                new SequentialCommandGroup(
                        new OuttakeClawCloseCommand(),
                        new WaitCommand(200),
                        new OuttakeClawOpenCommand()

                )
        );
    }
}
