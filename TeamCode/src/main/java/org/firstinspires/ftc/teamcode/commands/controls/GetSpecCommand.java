package org.firstinspires.ftc.teamcode.commands.controls;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesHighLiftCommand;

public class GetSpecCommand extends SequentialCommandGroup {
    public GetSpecCommand(){
        super (
                new SequentialCommandGroup( //Goes from intake position to spec pick-up position
                        new OuttakeClawCloseCommand(),
                        new ClawWristIntakeCommand(),
                        new ClawArmIntakeCommand(),
                        new WaitCommand(700),

                        new ClawArmOutSpecCommand(),
                        new WaitCommand(400),
                        new OuttakeClawOpenCommand(),
                        new ClawWristOutSpecCommand()
                )
        );
    }
}
