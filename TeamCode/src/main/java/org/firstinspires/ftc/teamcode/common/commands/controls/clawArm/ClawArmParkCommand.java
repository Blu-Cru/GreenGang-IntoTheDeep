package org.firstinspires.ftc.teamcode.common.commands.controls.clawArm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.common.subsystems.Robot;

public class ClawArmParkCommand extends InstantCommand {
    public ClawArmParkCommand(){
        super(
                () -> Robot.getInstance().clawArm.park()
        );
        addRequirements((Subsystem) Robot.getInstance().clawArm);
    }
}
