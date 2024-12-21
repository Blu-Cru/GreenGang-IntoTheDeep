package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

public class DepositHighBucketCommand extends SequentialCommandGroup {
    public DepositHighBucketCommand(){
        super (
                new SequentialCommandGroup(

                        new ClawArmBucketCommand(),
                        new ClawWristBucketCommand()
//                        new OuttakeClawOpenCommand()
                )
        );
    }
}
