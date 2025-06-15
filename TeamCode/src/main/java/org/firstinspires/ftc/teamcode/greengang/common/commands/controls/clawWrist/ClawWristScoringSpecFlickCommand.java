package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.clawWrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class ClawWristScoringSpecFlickCommand extends InstantCommand {
    //Toggles the claw wrist between the high spec scoring position to the straight position to aid in scoring specimens
    public ClawWristScoringSpecFlickCommand(){
        super(
                () -> Robot.getInstance().clawWrist.specFlick()
        );
        addRequirements((Subsystem) Robot.getInstance().clawWrist);
    }
}
