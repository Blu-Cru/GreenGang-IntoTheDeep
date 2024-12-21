package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class ClawArmBucketCommand extends InstantCommand {
    public ClawArmBucketCommand(){
        super(
                () -> Robot.getInstance().clawArm.bucket()
        );
        addRequirements((Subsystem) Robot.getInstance().clawArm);
    }
}
