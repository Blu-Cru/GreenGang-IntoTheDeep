package org.firstinspires.ftc.teamcode.common.commands.controls.clawWrist;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.common.subsystems.Robot;

public class ClawWristInSpecCommand extends InstantCommand {
    public ClawWristInSpecCommand(){
        super(
                () -> Robot.getInstance().clawWrist.inspec()
        );

        addRequirements((Subsystem) Robot.getInstance().clawWrist);
    }
}
