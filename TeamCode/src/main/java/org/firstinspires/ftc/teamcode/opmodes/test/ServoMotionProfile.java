package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.profile.MotionProfile;
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator;
import com.acmerobotics.roadrunner.profile.MotionState;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "ServoMotionProfile", group = "test")
public class ServoMotionProfile extends LinearOpMode {

    @Override
    public void runOpMode() {
        Servo servo = hardwareMap.get(Servo.class, "wrist");

        FtcDashboard dashboard = FtcDashboard.getInstance();

        MotionProfile profile = MotionProfileGenerator.generateSimpleMotionProfile(
                new MotionState(0, 0),
                new MotionState(0.5, 0),
                20.0,
                120.0,
                10.0
        );

        waitForStart();
        ElapsedTime timer = new ElapsedTime();

        while (opModeIsActive() && timer.seconds() < profile.duration()) {
            MotionState state = profile.get(timer.seconds());
            double degrees = state.getX();
            servo.setPosition(degrees / 180.0);
            sleep(10);

            // Dashboard telemetry
            TelemetryPacket packet = new TelemetryPacket();
            packet.put("ServoPosition", servo.getPosition());
            packet.put("TargetDegrees", degrees);
            packet.put("Time", timer.seconds());
            dashboard.sendTelemetryPacket(packet);

            // Optional Driver Station telemetry
            telemetry.addData("ServoPosition", servo.getPosition());
            telemetry.addData("TargetDegrees", degrees);
            telemetry.update();
        }
    }
}
