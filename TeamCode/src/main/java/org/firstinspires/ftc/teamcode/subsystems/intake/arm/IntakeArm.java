package org.firstinspires.ftc.teamcode.subsystems.intake.arm;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Subsystem;

public class IntakeArm implements Subsystem {
    public DcMotorEx armRotate;
    public static double armP = 0.003, armI = 0, armD = 0.0001;
    private final PIDController armRotatePID;
    public int armRotateTargetPos;
    public double armRotatePower;

    public static int
        VERTICAL_POS = 73,
        DOWN_POS = 182,
        INIT = 0;


    public IntakeArm(HardwareMap hardwareMap) {
        armRotate = hardwareMap.get(DcMotorEx.class, "armRotate");
        armRotatePID = new PIDController(armP, armI, armD);
        armRotateTargetPos = 0;
        armRotatePower = 0;
    }

    public void init() {
        armRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRotate.setPower(0); // check what power it should be
        armRotate.setDirection(DcMotorSimple.Direction.REVERSE);
        armRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRotate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void intake(){
        armRotateTargetPos = DOWN_POS;
    }
    public void rest() {
        armRotateTargetPos = VERTICAL_POS;
    }

    public void transfer() {
        armRotateTargetPos = INIT;
    }

    public void update() {
        int armRotateCurrentPos = getArmRotatePosition();
        armRotatePower = Range.clip(armRotatePID.calculate(armRotateCurrentPos, armRotateTargetPos), -0.6, 0.75);
        setArmRotatePower(armRotatePower);
    }

    public int getArmRotatePosition() {
        return armRotate.getCurrentPosition();
    }

    public void autoArmRotate(double power, int targetPos) {
        armRotate.setPower(power);
        armRotate.setTargetPosition(targetPos);
        armRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void setArmRotatePower(double power) {
        armRotate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armRotate.setPower(power);
    }

    public String telemetry(Telemetry telemetry) {
        telemetry.addData("ARM CURRENT POS: ", armRotate.getCurrentPosition());
        return null;
    }

    @Override
    public void read() {

    }

    @Override
    public void write() {

    }

}
