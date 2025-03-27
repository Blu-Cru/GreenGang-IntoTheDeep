package org.firstinspires.ftc.teamcode.commands.controls.clawArm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class ClawArmSpecInitCommand extends InstantCommand {
    public ClawArmSpecInitCommand(){
        super(
                () -> Robot.getInstance().clawArm.specInit()
        );
        addRequirements((Subsystem) Robot.getInstance().clawArm);

    }
}
