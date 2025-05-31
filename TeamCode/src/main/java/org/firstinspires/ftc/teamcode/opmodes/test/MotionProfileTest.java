package org.firstinspires.ftc.teamcode.opmodes.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator;
import com.acmerobotics.roadrunner.profile.MotionSegment;
import com.acmerobotics.roadrunner.profile.MotionState;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.gamepad.StickyGamepad;
import org.firstinspires.ftc.teamcode.subsystems.util.MotionProfile;
@Config
@TeleOp(name="mpt", group="test")

public class MotionProfileTest extends LinearOpMode {
    public StickyGamepad StickyG1;

    public static String name = "wrist";
    public static double vMax = 0.2, aMax = 1, xI = 0, xF = 1;
    public MotionProfile mp;

    public enum State {
        NORMAL,
        SPIKE,
        RESET
    }
    private State currentState = State.NORMAL;
    private ElapsedTime profileTimer = new ElapsedTime();
    private double profileDuration = 0;


    @Override
    public void runOpMode() throws InterruptedException {
        FtcDashboard dashboard = FtcDashboard.getInstance();
        Servo test = hardwareMap.get(Servo.class, name);
        ServoControllerEx controller = (ServoControllerEx) test.getController();
        controller.setServoPwmDisable(test.getPortNumber());
        mp = new MotionProfile(0,0,0,0);

        waitForStart();

        while(opModeIsActive()) {
            if (currentState == State.NORMAL) {
                // Start normal profile if just entered state
                if (profileTimer.seconds() == 0) {
                    mp = new MotionProfile(0.5, test.getPosition(), vMax, aMax).start();
                }
                // Wait for profile to finish
                if (mp.done()) {
                    currentState = State.SPIKE;
                    profileTimer.reset();
                }

            } else if (currentState == State.SPIKE) {

                if (profileTimer.seconds() == 0) {
                    mp = new MotionProfile(xF, test.getPosition(), vMax/3, aMax/3).start();
                }

                if (mp.done()) {
                    currentState = State.NORMAL;
                    profileTimer.reset();
                }
            }

            if(gamepad1.dpad_down){
                mp = new MotionProfile(0, test.getPosition(), vMax, aMax).start();
                currentState = State.NORMAL;
                profileTimer.reset();
            }

            TelemetryPacket packet = new TelemetryPacket();
            packet.put("ServoPosition", test.getPosition());
            dashboard.sendTelemetryPacket(packet);

            test.setPosition(mp.getInstantTargetPosition());
            mp.telemetry(telemetry);
            telemetry.addData("name", name);
            telemetry.addData("position", test.getPosition());
            telemetry.addData("state", currentState);
            telemetry.addData("elapsed time", profileTimer);
            telemetry.update();
        }
    }

}
