package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class ClawWristScoringSpecToggleCommand extends InstantCommand {
    //Toggles the claw wrist between the high spec scoring position to the straight position to aid in scoring specimens
    public ClawWristScoringSpecToggleCommand(){
        super(
                () -> Robot.getInstance().clawWrist.specScoringToggle()
        );
        addRequirements((Subsystem) Robot.getInstance().clawWrist);
    }
}
