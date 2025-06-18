package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class ClawArmBucketCommand extends InstantCommand {
    public ClawArmBucketCommand(){
        super(
                () -> Robot.getInstance().clawArm.sampleOuttake()
        );
        addRequirements(Robot.getInstance().clawArm);
    }
}
