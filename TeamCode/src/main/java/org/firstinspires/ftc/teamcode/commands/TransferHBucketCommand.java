package org.firstinspires.ftc.teamcode.commands;

import android.util.Log;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.outtake.wrist.ClawWrist;

public class TransferHBucketCommand extends SequentialCommandGroup {
    public TransferHBucketCommand(){
        super (
                new SequentialCommandGroup(
                        new TransferIntoClawCommand(),
                        new WaitCommand(200),

                        new CatchHighLiftCommand(),
                        new WaitCommand(200),

                        new DepositHighBucketCommand(),
                        new WaitCommand(200),

                        new LowerCommand(),
                        new WaitCommand(200),

                        new ResetClawSubsCommand()

                )
        );

    }
}
