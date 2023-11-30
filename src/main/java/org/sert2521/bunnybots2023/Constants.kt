package org.sert2521.bunnybots2023

import edu.wpi.first.math.MatBuilder
import edu.wpi.first.math.Matrix
import edu.wpi.first.math.Nat
import edu.wpi.first.math.geometry.Translation2d
import edu.wpi.first.math.numbers.N1
import edu.wpi.first.math.numbers.N3
import kotlin.math.PI

/*
 * The Constants file provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This file should not be used for any other purpose.
 * All String, Boolean, and numeric (Int, Long, Float, Double) constants should use
 * `const` definitions. Other constant types should use `val` definitions.
 */

class SwerveModuleData(val position: Translation2d, val powerMotorID: Int, val angleMotorID: Int, val angleEncoderID: Int, val angleOffset: Double, val inverted: Boolean)

object PhysicalConstants {
    //Measurement is when robot is 28 inches without bumpers
    const val halfSideLength = 0.286378246381


    const val powerEncoderMultiplierPosition = PI * 0.1016 / 8.14
    const val powerEncoderMultiplierVelocity = powerEncoderMultiplierPosition / 60.0
    const val angleEncoderMultiplier = 0.01745329251

    const val wristEncoderMultiplier = 0.0
    const val wristEncoderTransform = 0.0

    const val wristSetpointGround = -PI/16.0
    const val wristSetpointTote = PI/4.0
    const val wristSetpointRest = PI/2.0

    const val wristSetpointMin = -PI/16
    const val wristSetpointMax = PI/2.0

}

object TunedConstants {
    //I think this is filtering
    val encoderDeviations: Matrix<N3, N1> = MatBuilder(Nat.N3(), Nat.N1()).fill(1.0, 1.0, 0.01)
    val defaultVisionDeviations: Matrix<N3, N1> = MatBuilder(Nat.N3(), Nat.N1()).fill(1.0, 1.0, 1000.0)


    //Feedforward constants
    const val swervePowerS = 0.3
    const val swervePowerV = 2.0
    const val swervePowerA = 0.0

    //PID loop constants
    const val swervePowerP = 1.5
    const val swervePowerI = 0.0
    const val swervePowerD = 0.0

    const val swerveAngleP = 0.5
    const val swerveAngleI = 0.0
    const val swerveAngleD = 0.0


    const val wristP = 0.0
    const val wristI = 0.0
    const val wristD = 0.0

    //ESTIMATIONS
    const val wristS = 0.0
    const val wristG = 0.31
    const val wristV = 1.30
    const val wristA = 0.01

    const val visionP = 0.01

    const val visionSusness = 2.0
}

object ConfigConstants {

    const val drivetrainOptimized = true
    //Controller constants
    const val powerDeadband = 0.075
    const val rotDeadband = 0.075

    //Drive speed constants
    const val driveSpeed = 3.5
    const val driveSecondarySpeed = 2.75
    const val rotSpeed = 3.5
    const val rotSecondarySpeed = 2.75

    //Acceleration of drivetrain
    const val driveSpeedupChangeSpeed = 6.5
    const val driveSlowdownChangeSpeed = 36.0

    const val wristSlideSpeed = PI/200

    const val visionCenter = 0.0
}

object ElectronicIDs {
    val swerveModuleData = listOf(
        SwerveModuleData(Translation2d(PhysicalConstants.halfSideLength, -PhysicalConstants.halfSideLength), 13, 3, 17, 0.0, false), //Front Right
        SwerveModuleData(Translation2d(-PhysicalConstants.halfSideLength, -PhysicalConstants.halfSideLength), 10, 5, 16, 0.0, false), //Back Right
        SwerveModuleData(Translation2d(PhysicalConstants.halfSideLength, PhysicalConstants.halfSideLength), 12, 7, 15, 0.0, false), //Back Left
        SwerveModuleData(Translation2d(-PhysicalConstants.halfSideLength, PhysicalConstants.halfSideLength), 11, 8, 14, 0.0, false) //Front Left
    )

    const val wristMotor = -1
    const val wristTrueEncoder = -1
}

object RuntimeConstants {
    var wristSetPoint = 0.0

    var disableRightStick = false
    var visionRightStick = 0.0
}



