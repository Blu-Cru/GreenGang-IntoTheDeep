package org.firstinspires.ftc.teamcode.greengang.opmodes.test;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.greengang.common.util.StickyGamepad;
import org.firstinspires.ftc.teamcode.greengang.common.util.MotionProfile;
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
        RESET,
        SMOOTHSERVO
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
        profileTimer.reset();
        while(opModeIsActive()) {
            if (currentState == State.NORMAL) {
                // Start normal profile if just entered state
                if (profileTimer.seconds() < 0.5) {
                    mp = new MotionProfile(0.8, test.getPosition(), vMax*4, aMax*4).start();
                }
                // Wait for profile to finish
                if (mp.done()) {
                    currentState = State.SPIKE;
                    profileTimer.reset();
                }

            }
            else if (currentState == State.SPIKE) {

                if (profileTimer.seconds() < 0.5) {
                    mp = new MotionProfile(xF, test.getPosition(), vMax/2, aMax/2).start();
                }

                if (mp.done()) {
                    currentState = State.RESET;
                    profileTimer.reset();
                }
            }
            else if(currentState == State.RESET){
                if(profileTimer.seconds() < 0.5) {
                    mp = new MotionProfile(0, test.getPosition(), vMax, aMax).start();
                }
                if (mp.done()) {
                    currentState = State.SMOOTHSERVO;
                    profileTimer.reset();
                }

            }
            else if(currentState == State.SMOOTHSERVO){
                if(profileTimer.seconds() < 0.5) {

                }

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
