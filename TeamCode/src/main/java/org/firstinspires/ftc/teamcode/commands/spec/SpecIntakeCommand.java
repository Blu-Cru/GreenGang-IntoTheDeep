package org.firstinspires.ftc.teamcode.commands.spec;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.controls.arm.ClawArmInSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.arm.ClawArmInspecTransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristInSpecCommand;
import org.firstinspires.ftc.teamcode.commands.controls.claw.OuttakeClawOpenCommand;
import org.firstinspires.ftc.teamcode.commands.controls.clawWrist.ClawWristInSpecTransferCommand;
import org.firstinspires.ftc.teamcode.commands.controls.turret.TurretInitCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vertSlides.VertSlidesStartCommand;
import org.firstinspires.ftc.teamcode.subsystems.util.Robot;

public class SpecIntakeCommand extends SequentialCommandGroup {
    public SpecIntakeCommand(){
        super(
                new SequentialCommandGroup(
                        new ClawWristInSpecTransferCommand(),
                        new WaitCommand(200),
                        new ClawArmInspecTransferCommand(),
                        new WaitCommand(500),
                        new ClawWristInSpecCommand(),
                        new ClawArmInSpecCommand(),
                        new TurretInitCommand(),

                        new OuttakeClawOpenCommand(),
                        new VertSlidesStartCommand()
//                        new ConditionalCommand(
//                                new VertSlidesStartCommand(),
//                                new WaitCommand(0),
//                                () -> Robot.getInstance().distanceSensor.isFull()
//                        )
                )
        );
    }
}
