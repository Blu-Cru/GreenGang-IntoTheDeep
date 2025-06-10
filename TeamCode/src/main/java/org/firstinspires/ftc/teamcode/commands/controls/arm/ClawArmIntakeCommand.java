package org.firstinspires.ftc.teamcode.commands.controls.arm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class ClawArmIntakeCommand extends InstantCommand {
    public ClawArmIntakeCommand(){
        super (
                () -> Robot.getInstance().clawArm.intake()
        );
        addRequirements((Subsystem) Robot.getInstance().clawArm);
    }
}
