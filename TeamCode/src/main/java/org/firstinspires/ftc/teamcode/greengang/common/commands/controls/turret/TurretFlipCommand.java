package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.turret;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class TurretFlipCommand extends InstantCommand {
    public TurretFlipCommand(){
        super(
                () -> Robot.getInstance().turret.flip()
        );



        addRequirements(Robot.getInstance().turret);
    }
}
