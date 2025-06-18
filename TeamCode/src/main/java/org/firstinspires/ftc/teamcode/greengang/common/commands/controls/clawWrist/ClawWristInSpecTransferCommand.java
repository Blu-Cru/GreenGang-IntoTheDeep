package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class ClawWristInSpecTransferCommand extends InstantCommand {
    public ClawWristInSpecTransferCommand(){
        //USED TO GET WRIST OUT OF THE WAY TO GO FROM RETRACTED TO INSPEC
        super(
                () -> Robot.getInstance().clawWrist.inspecTransfer()
        );

        addRequirements(Robot.getInstance().clawWrist);
    }
}
