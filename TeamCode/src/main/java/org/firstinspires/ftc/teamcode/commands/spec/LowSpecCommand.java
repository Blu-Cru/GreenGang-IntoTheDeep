package org.firstinspires.ftc.teamcode.commands.spec;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesHighSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesLowSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesStartCommand;

public class LowSpecCommand extends SequentialCommandGroup {
    public LowSpecCommand(){
        super (
                new SequentialCommandGroup(
                        new VertSlidesLowSpecCommand(),
                        new ClawWristOutSpecCommand(),
                        new ClawArmOutSpecCommand()
                )
        );
    }
}
