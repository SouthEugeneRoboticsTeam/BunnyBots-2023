package org.sert2521.bunnybots2023

import com.ctre.phoenix.motorcontrol.TalonFXControlMode
import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj2.command.button.JoystickButton
import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.DriverStation.Alliance
import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj2.command.*
import org.sert2521.bunnybots2023.commands.ClawIntake
import org.sert2521.bunnybots2023.commands.IndexerIntakeCommand
import org.sert2521.bunnybots2023.commands.IndexerReverseCommand
import org.sert2521.bunnybots2023.subsystems.Drivetrain
import org.sert2521.bunnybots2023.subsystems.Wrist


object Input {
    private val driverController = XboxController(0)
    private val gunnerController = Joystick(1)

    private val resetAngle = JoystickButton(driverController, 4)
    private val secondarySpeedButton = JoystickButton(driverController, 5)

    private val clawIntake = JoystickButton(gunnerController, 3)
    private val clawOuttake = JoystickButton(driverController, 6)


    private val wristGround = JoystickButton(gunnerController, 5)
    private val wristTote = JoystickButton(gunnerController, 6)
    private val wristStow = JoystickButton(gunnerController, 7)

    private val wristUp = JoystickButton(gunnerController, 17)
    private val wristDown = JoystickButton(gunnerController, 19)

    private val indexerIntake = JoystickButton(gunnerController, 8)
    private val indexerReverse = JoystickButton(gunnerController, 9)



    private val autoChooser = SendableChooser<() -> Command?>()
    /*
    private val autoBuilder = SwerveAutoBuilder(
        Drivetrain::getPose,
        { Drivetrain.setNewPose(it); Drivetrain.setNewVisionPose(it) },
        PIDConstants(TunedConstants.swerveAutoDistanceP, TunedConstants.swerveAutoDistanceI, TunedConstants.swerveAutoDistanceD),
        PIDConstants(TunedConstants.swerveAutoAngleP, TunedConstants.swerveAutoAngleI, TunedConstants.swerveAutoAngleD),
        Drivetrain::drive,
        ConfigConstants.eventMap,
        true,
        Drivetrain
    )

     */


    var secondarySpeedMode = false

    init {
        /*
        autoChooser.setDefaultOption("Nothing") { null }
        for (path in ConfigConstants.paths) {
            autoChooser.addOption(path.first) { autoBuilder.fullAuto(path.second) }
        }
         */
        secondarySpeedButton.onTrue(InstantCommand({ secondarySpeedMode = !secondarySpeedMode }))
        /*
        wristGround.onTrue(InstantCommand({RuntimeConstants.wristSetPoint = PhysicalConstants.wristSetpointGround}))
        wristTote.onTrue(InstantCommand({RuntimeConstants.wristSetPoint = PhysicalConstants.wristSetpointTote}))
        wristRest.onTrue(InstantCommand({RuntimeConstants.wristSetPoint = PhysicalConstants.wristSetpointRest}))

        wristUp.whileTrue(SlideWristSetpoint(-ConfigConstants.wristSlideSpeed))
        wristDown.whileTrue(SlideWristSetpoint(ConfigConstants.wristSlideSpeed))

         */

        clawIntake.whileTrue(ClawIntake(0.8))
        clawOuttake.whileTrue(ClawIntake(-1.0))
        resetAngle.onTrue(InstantCommand({Drivetrain.setNewPose(Pose2d())}))
        //resetAngle.onTrue(InstantCommand({Wrist.resetEncoder()}))

        wristTote.onTrue(InstantCommand({ RuntimeConstants.wristSetPoint=PhysicalConstants.wristSetpointTote }))
        wristGround.onTrue(InstantCommand({ RuntimeConstants.wristSetPoint=PhysicalConstants.wristSetpointGround }))
        wristStow.onTrue(InstantCommand({ RuntimeConstants.wristSetPoint=PhysicalConstants.wristSetpointStow }))

        //indexerIntake.whileTrue(IndexerIntakeCommand())
        //indexerReverse.whileTrue(IndexerReverseCommand())
    }

    fun getAuto(): Command? {
        val selected = autoChooser.selected
        return if (selected == null) {
            null
        } else {
            selected()
        }
    }

    fun getBrakePos(): Boolean {
        return driverController.xButton
    }

    // Rename fast stuff because it actually slows it
    fun getSecondarySpeed(): Double {
        return if (!secondarySpeedMode) {
            driverController.leftTriggerAxis
        } else {
            1.0
        }
    }

    fun getX(): Double {
        return -driverController.leftY
    }

    fun getY(): Double {
        return -driverController.leftX
    }

    fun getRot(): Double {
        return -driverController.rightX
    }

    fun getSlider(): Double {
        return gunnerController.getRawAxis(3)
    }

    fun getColor(): Alliance {
        return DriverStation.getAlliance()
    }

    // This kinda violates the spirit of Input and Output
    fun rumble(amount: Double) {
        driverController.setRumble(GenericHID.RumbleType.kBothRumble, amount)
    }
}