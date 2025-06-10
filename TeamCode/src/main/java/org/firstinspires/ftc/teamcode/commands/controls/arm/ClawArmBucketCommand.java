package org.firstinspires.ftc.teamcode.commands.controls.arm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class ClawArmBucketCommand extends InstantCommand {
    public ClawArmBucketCommand(){
        super(
                () -> Robot.getInstance().clawArm.perpendicular()
        );
        addRequirements((Subsystem) Robot.getInstance().clawArm);
    }
}
