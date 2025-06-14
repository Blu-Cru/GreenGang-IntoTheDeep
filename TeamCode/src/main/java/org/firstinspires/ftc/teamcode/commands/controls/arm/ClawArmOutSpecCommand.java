package org.firstinspires.ftc.teamcode.commands.controls.arm;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;
public class ClawArmOutSpecCommand extends InstantCommand {
    public ClawArmOutSpecCommand(){
        super (
                () -> Robot.getInstance().clawArm.outSpec()
        );
        addRequirements((Subsystem) Robot.getInstance().clawArm);
    }
}
