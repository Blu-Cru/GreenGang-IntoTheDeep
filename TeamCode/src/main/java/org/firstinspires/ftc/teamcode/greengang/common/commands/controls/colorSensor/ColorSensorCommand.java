package org.firstinspires.ftc.teamcode.greengang.common.commands.controls.colorSensor;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;

import org.firstinspires.ftc.teamcode.greengang.common.util.Robot;

public class ColorSensorCommand extends InstantCommand {

    public ColorSensorCommand(){
        super(
                () -> Robot.getInstance().color.init()
        );

        addRequirements(Robot.getInstance().color);

    }
}
