package org.firstinspires.ftc.teamcode.commands.spec.auto;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristOutSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesStartCommand;

/*
- Slides lowered
- Claw arm outwards
- Claw wrist outwards
- Opens claw
 */
public class AutoSpecIntake extends SequentialCommandGroup {
    public AutoSpecIntake() {
        super (
                new SequentialCommandGroup(
                        new VertSlidesStartCommand(),
                        new ClawArmOutSpecCommand(),
                        new ClawWristOutSpecCommand(),
                        new OuttakeClawOpenCommand(),
                        new WaitCommand(1000),
                        new OuttakeClawCloseCommand()
                )
        );
    }

}
