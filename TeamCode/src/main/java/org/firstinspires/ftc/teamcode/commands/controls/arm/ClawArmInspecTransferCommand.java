package org.firstinspires.ftc.teamcode.commands.controls.arm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class ClawArmInspecTransferCommand extends InstantCommand {
    public ClawArmInspecTransferCommand(){
        super(
                () -> Robot.getInstance().clawArm.inspecTransfer()
        );
        addRequirements((Subsystem) Robot.getInstance().clawArm);
    }
}
