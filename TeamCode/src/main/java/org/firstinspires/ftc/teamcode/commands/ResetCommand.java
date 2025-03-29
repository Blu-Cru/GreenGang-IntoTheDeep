package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.clawArm.ClawArmIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.controls.hs.HorizontalSlidesExtendCommand;
import org.firstinspires.ftc.teamcode.commands.controls.hs.HorizontalSlidesRetractCommand;
import org.firstinspires.ftc.teamcode.commands.controls.intakeWrist.WristParallelCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawCloseCommand;
import org.firstinspires.ftc.teamcode.commands.controls.outtakeClaw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.SlidesLiftSlightlyCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesLowSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.commands.outtake.OuttakeIntakeCommand;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.subsystems.outtake.arm.ClawArm;
import org.firstinspires.ftc.teamcode.subsystems.slides.HorizontalSlides;

/*
resets all subsystems to how they were in initialization state
 */
public class ResetCommand extends SequentialCommandGroup {
    public ResetCommand(){
        super (

//                new OuttakeClawCloseCommand(),
                new OuttakeClawOpenCommand(),
                new ClawWristIntakeCommand(),
                new ClawArmIntakeCommand(),
                new SlidesLiftSlightlyCommand(),
                new HorizontalSlidesRetractCommand(),
                new WristParallelCommand(),
//                new WaitCommand(500), // 700 before
//                new OuttakeClawOpenCommand(),
                new ConditionalCommand(
                        new WaitCommand(500),//previous 100

                            // false;
                        new WaitCommand(50),

                        () -> Robot.getInstance().clawArm.state == ClawArm.STATE.INSPEC || Robot.getInstance().clawArm.state == ClawArm.STATE.PARK || Robot.getInstance().horizontalSlides.loc == HorizontalSlides.LOC.EXTENDED
                ),
                new VertSlidesStartCommand() //may need to swap
        );
    }

}
