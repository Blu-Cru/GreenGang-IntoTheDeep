package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class ClawArmInspecTransferCommand extends InstantCommand {
    public ClawArmInspecTransferCommand(){
        super(
                () -> Robot.getInstance().clawArm.inSpecTransfer()
        );
        addRequirements((Subsystem) Robot.getInstance().clawArm);
    }
}
