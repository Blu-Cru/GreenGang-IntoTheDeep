package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.arm;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;
public class ClawArmInSpecCommand extends InstantCommand {
    public ClawArmInSpecCommand(){
        super (
                () -> Robot.getInstance().clawArm.inSpec()
        );
        addRequirements(Robot.getInstance().clawArm);
    }
}
