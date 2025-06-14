package org.firstinspires.ftc.teamcode.commands.controls.arm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class ClawArmInitCommand extends InstantCommand {
    public ClawArmInitCommand(){
        super (
                () -> Robot.getInstance().clawArm.init()
        );
        addRequirements((Subsystem) Robot.getInstance().clawArm);
    }
}
