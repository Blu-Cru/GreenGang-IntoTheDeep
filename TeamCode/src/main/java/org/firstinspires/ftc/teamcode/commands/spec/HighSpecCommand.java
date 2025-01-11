package org.firstinspires.ftc.teamcode.commands.spec;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesHighSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesLowSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesStartCommand;

public class HighSpecCommand extends SequentialCommandGroup {
    public HighSpecCommand(){
        super (
                new SequentialCommandGroup(
                        new VertSlidesHighSpecCommand(),
                        new ClawWristOutSpecCommand(),
                        new ClawArmOutSpecCommand()
                )
        );
    }
}
