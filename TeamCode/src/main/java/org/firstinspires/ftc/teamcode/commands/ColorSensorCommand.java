package org.firstinspires.ftc.teamcode.commands;

import android.graphics.Color;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.subsystems.Robot;

public class ColorSensorCommand extends InstantCommand {

    public ColorSensorCommand(){
        super(
                () -> Robot.getInstance().color.init()
        );

        addRequirements((Subsystem) Robot.getInstance().color);

    }
}
