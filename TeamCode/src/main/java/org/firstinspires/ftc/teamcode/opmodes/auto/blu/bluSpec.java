package org.firstinspires.ftc.teamcode.opmodes.auto.blu;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.commands.spec.auto.AutoSpecIntake;
import org.firstinspires.ftc.teamcode.commands.spec.auto.AutoSpecOuttake;
import org.firstinspires.ftc.teamcode.commands.spec.auto.SamplePassThroughCommand;
import org.firstinspires.ftc.teamcode.opmodes.GreenLinearOpMode;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous(name = "blue spec auto", group = "paths")
public class bluSpec extends GreenLinearOpMode {

    TrajectorySequence farBlue;
    SampleMecanumDrive mecDrive;
    boolean done;

    @Override
    public void initialize() {
        addDrivetrain();
        addIntake();
        addStickyG1();
        addClawArm();
        addOuttakeClaw();
        addHorizontalSlides();
        addIntakeWrist();
        addClawWrist();
        addVertSlides();
        addHang();
        addIntakeColorSensor();

        mecDrive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-24, 64, Math.toRadians(-90));
        mecDrive.setPoseEstimate(startPose);

        farBlue = mecDrive.trajectorySequenceBuilder(startPose)

                // SPEC 3 PLACEMENT
                .splineToLinearHeading(new Pose2d(-48, 13, Math.toRadians(-90)), Math.toRadians(180))
                .waitSeconds(1)
                .setTangent(90)

                // Give sample to human player
                .splineToLinearHeading(new Pose2d(-48, 55, Math.toRadians(-90)), Math.toRadians(90))
                .setTangent(-90)
                .waitSeconds(1)

//                .addTemporalMarker(() -> {
//                    new SamplePassThroughCommand().schedule();
//                })
                // SPEC 4 PLACEMENT
                .splineToLinearHeading(new Pose2d(-58, 13, Math.toRadians(-90)), Math.toRadians(180))
                .waitSeconds(1)
                .setTangent(90)

                .splineToLinearHeading(new Pose2d(-58, 55, Math.toRadians(-90)), Math.toRadians(90))
                .setTangent(-90)
                .waitSeconds(1)

//                .addTemporalMarker(() -> {
//                    new SamplePassThroughCommand().schedule();
//                })
                // SPEC 5 PLACEMENT

                .splineToLinearHeading(new Pose2d(-65, 13, Math.toRadians(-90)), Math.toRadians(180))
                .waitSeconds(1)
                .setTangent(90)
                .splineToLinearHeading(new Pose2d(-65, 55, Math.toRadians(-90)), Math.toRadians(90))
                .waitSeconds(1)
                .build();

        }

    @Override
    public void periodic() {


        if (!drivetrain.isBusy() && !done){
            mecDrive.followTrajectorySequenceAsync(farBlue);
            done = true;
        }
        try {
            mecDrive.updateTrajectory();
        } catch (Exception e){
//            requestOpModeStop();
        }
    }


}
