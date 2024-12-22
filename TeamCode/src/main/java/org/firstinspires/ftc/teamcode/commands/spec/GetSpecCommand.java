package org.firstinspires.ftc.teamcode.commands.spec;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesStartCommand;

public class GetSpecCommand extends SequentialCommandGroup {
    public GetSpecCommand(){
        super (
                new SequentialCommandGroup( //Goes from intake position to spec pick-up position
                        new VertSlidesStartCommand(),
                        new ClawArmOutSpecCommand(),
                        new WaitCommand(300),
                        new ClawWristOutSpecCommand(),
                        new OuttakeClawOpenCommand()
                )
        );
    }
}
