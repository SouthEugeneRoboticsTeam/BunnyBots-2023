package org.sert2521.bunnybots2023.commands

import edu.wpi.first.math.kinematics.ChassisSpeeds
import org.sert2521.bunnybots2023.subsystems.Drivetrain
import org.sert2521.bunnybots2023.Input
import org.sert2521.bunnybots2023.RuntimeConstants

class JoystickDrive(private val fieldOrientated: Boolean) : JoystickCommand() {
    init {
        addRequirements(Drivetrain)
    }


    override fun execute() {
        val joystickData = readJoystick()

        if (joystickData.x == 0.0 && joystickData.y == 0.0 && joystickData.z == 0.0) {
            if (Input.getBrakePos()) {
                Drivetrain.enterBrakePos()
            } else {
                Drivetrain.stop()
            }
        } else {
            if (fieldOrientated && !RuntimeConstants.disableRightStick) {
                Drivetrain.drive(ChassisSpeeds.fromFieldRelativeSpeeds(joystickData.x, joystickData.y, joystickData.z, Drivetrain.getPose().rotation))
            } else if (!fieldOrientated && !RuntimeConstants.disableRightStick) {
                Drivetrain.drive(ChassisSpeeds(joystickData.x, joystickData.y, joystickData.z))
            }else if (fieldOrientated) {
                Drivetrain.drive(ChassisSpeeds.fromFieldRelativeSpeeds(joystickData.x, joystickData.y, RuntimeConstants.visionRightStick, Drivetrain.getPose().rotation))
            } else {
                Drivetrain.drive(ChassisSpeeds(joystickData.x, joystickData.y, RuntimeConstants.visionRightStick))
            }
        }
    }

    override fun end(interrupted: Boolean) {
        Drivetrain.stop()
    }
}