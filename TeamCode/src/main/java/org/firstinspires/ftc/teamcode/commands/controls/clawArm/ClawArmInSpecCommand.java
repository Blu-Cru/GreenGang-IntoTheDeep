package org.firstinspires.ftc.teamcode.commands.controls.clawArm;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.subsystems.util.Robot;
public class ClawArmInSpecCommand extends InstantCommand {
    public ClawArmInSpecCommand(){
        super (
                () -> Robot.getInstance().clawArm.inspec()
        );
        addRequirements((Subsystem) Robot.getInstance().clawArm);
    }
}
