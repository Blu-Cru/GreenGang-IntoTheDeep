package org.firstinspires.ftc.teamcode.opmodes.test;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.bucket.auto.AutoSamplePart2;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesHighBucketCommand;
import org.firstinspires.ftc.teamcode.commands.controls.vs.VertSlidesHighSpecCommand;
import org.firstinspires.ftc.teamcode.commands.spec.auto.AutoSpecIntake;
import org.firstinspires.ftc.teamcode.commands.spec.auto.AutoSpecOuttake;
import org.firstinspires.ftc.teamcode.commands.spec.auto.SamplePassThroughCommand;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Robot;

@TeleOp(name = "auto commands test", group = "test")
public class AutoCommandsTest extends GreenLinearOpMode {

    @Override
    public void initialize(){
        addIntake();
        addOuttakeClaw();
        addDrivetrain();
        addIntakeColorSensor();
        addIntake();
        addIntakeWrist();
        addOuttakeClaw();
        addClawWrist();
        addIntakeArm();
        addVertSlides();
        addClawArm();
    }
    @Override
    public void periodic() {

        if (gamepad1.a)
            new AutoSpecOuttake().schedule();
        else if (gamepad1.b)
            new AutoSpecIntake().schedule();
        else if (gamepad1.x)
            new SamplePassThroughCommand().schedule();
        else if (gamepad1.right_bumper)
            new VertSlidesHighBucketCommand().schedule();
        else if (gamepad1.left_bumper)
            new ResetCommand().schedule();

        CommandScheduler.getInstance().run();
    }


}
