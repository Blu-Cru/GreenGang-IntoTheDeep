package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class ClawWristFlatCommand extends InstantCommand {
    public ClawWristFlatCommand(){
        super(
                () -> Robot.getInstance().clawWrist.lowOutspec()
        );

        addRequirements(Robot.getInstance().clawWrist);
    }
}
