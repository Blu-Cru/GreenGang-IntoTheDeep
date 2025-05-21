package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.roadrunner.profile.MotionProfile;
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator;
import com.acmerobotics.roadrunner.profile.MotionSegment;
import com.acmerobotics.roadrunner.profile.MotionState;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;

@TeleOp(name="mpt")
public class MotionProfileTest extends LinearOpMode {

    public static double position = 0.3;
    public static String name = "name";
    public static double vMax = 5, aMax = 25;
    public MotionProfile mp;


    @Override
    public void runOpMode() throws InterruptedException {

        Servo test = hardwareMap.get(Servo.class, name);
        ServoControllerEx controller = (ServoControllerEx) test.getController();
        controller.setServoPwmDisable(test.getPortNumber());
        waitForStart();

        mp = MotionProfileGenerator.generateSimpleMotionProfile(
                new MotionState(0, vMax/2, aMax),
                new MotionState(90, vMax, aMax/2),
                vMax,
                aMax,
                100
        );

        ElapsedTime timer = new ElapsedTime();
        timer.reset();

        while(opModeIsActive()) {

            if (gamepad1.a) {
                while (timer.seconds() < mp.duration()) {
                    MotionState state = mp.get(timer.seconds());
                    double position = state.getX();
                    test.setPosition(position / 60.0);
                    sleep(10);
                }
            }

            telemetry.addData("name", name);
            telemetry.addData("position", test.getPosition());
            telemetry.update();
        }
    }

}
