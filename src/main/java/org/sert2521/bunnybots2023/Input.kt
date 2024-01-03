package org.sert2521.bunnybots2023

import com.pathplanner.lib.auto.PIDConstants
import com.pathplanner.lib.auto.SwerveAutoBuilder
import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj2.command.button.JoystickButton
import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.DriverStation.Alliance
import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.event.EventLoop
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.*
import edu.wpi.first.wpilibj2.command.button.Trigger
import org.sert2521.bunnybots2023.commands.SlideWristSetpoint
import org.sert2521.bunnybots2023.subsystems.Drivetrain
import org.sert2521.bunnybots2023.commands.*
import org.sert2521.bunnybots2023.subsystems.Flywheel
import org.sert2521.bunnybots2023.subsystems.Vision
import java.util.function.BooleanSupplier
import java.util.function.Supplier
import kotlin.math.PI


object Input {
    private val driverController = XboxController(0)
    private val gunnerController = Joystick(1)

    private val resetAngle = JoystickButton(driverController, 4)
    private val secondarySpeedButton = JoystickButton(driverController, 2)

    private val clawIntake = JoystickButton(gunnerController, 3)
    private val clawOuttake = JoystickButton(driverController, 6)


    private val wristGround = JoystickButton(gunnerController, 5)
    private val wristTote = JoystickButton(gunnerController, 6)
    private val wristStow = JoystickButton(gunnerController, 7)

    private val wristUp = Trigger({ gunnerController.pov == 0})
    private val wristDown = Trigger({ gunnerController.pov == 180})

    private val flywheelButton = Trigger({ driverController.rightTriggerAxis >= 0.2})

    private val indexerIntake = JoystickButton(gunnerController, 8)
    private val indexerReverse = JoystickButton(gunnerController, 9)

    val visionAlignRev = JoystickButton(driverController, 1)
    val visionRenew = JoystickButton(driverController, 3)

    private val indexerKick = JoystickButton(driverController, 5)

    val flywheel = JoystickButton(driverController, 8)


    //fun flywheelBool():Boolean = driverController.rightTriggerAxis>=0.2
    //val flywheelButton = Trigger(BooleanSupplier(driverController::getRightTriggerAxis))

    var secondarySpeedMode = false

    private val autoChooser = SendableChooser<() -> Command?>()
    private val autoBuilder = SwerveAutoBuilder(
            Drivetrain::getPose,
            { Drivetrain.setNewPose(Pose2d())},
            PIDConstants(TunedConstants.swerveAutoDistanceP, TunedConstants.swerveAutoDistanceI, TunedConstants.swerveAutoDistanceD),
            PIDConstants(TunedConstants.swerveAutoAngleP, TunedConstants.swerveAutoAngleI, TunedConstants.swerveAutoAngleD),
            Drivetrain::drive,
            ConfigConstants.eventMap,
            false,
            Drivetrain
    )
    init {

        autoChooser.setDefaultOption("Nothing") { null }
        for (path in ConfigConstants.paths) {
            autoChooser.addOption(path.first) { autoBuilder.fullAuto(path.second) }
        }

        secondarySpeedButton.onTrue(InstantCommand({ secondarySpeedMode = !secondarySpeedMode }))

        clawIntake.whileTrue(ClawIntake(0.8))
        clawOuttake.whileTrue(ClawIntake(-1.0))
        resetAngle.onTrue(InstantCommand({Drivetrain.setNewPose(Pose2d())}))

        wristTote.onTrue(InstantCommand({ RuntimeConstants.wristSetPoint=PhysicalConstants.wristSetpointTote }))
        wristGround.onTrue(InstantCommand({ RuntimeConstants.wristSetPoint=PhysicalConstants.wristSetpointGround }))
        wristStow.onTrue(InstantCommand({ RuntimeConstants.wristSetPoint=PhysicalConstants.wristSetpointStow }))

        wristUp.whileTrue(SlideWristSetpoint(ConfigConstants.wristSlideSpeed))
        wristDown.whileTrue(SlideWristSetpoint(-ConfigConstants.wristSlideSpeed))

        indexerIntake.whileTrue(IndexerIntake())
        indexerReverse.whileTrue(IndexerReverse())
        indexerKick.onTrue(IndexerKick())

        visionAlignRev.whileTrue(FlywheelRun().alongWith(VisionLock()))
        visionAlignRev.onTrue(InstantCommand({Vision.switchLights(true)}))

        //visionRenew.onTrue(VisionLock())

        flywheel.whileTrue(InstantCommand({Vision.switchLights(true)}))

        flywheelButton.whileTrue(FlywheelRun())

        visionAlignRev.onFalse(InstantCommand({Vision.switchLights(false)}))



        SmartDashboard.putData("Auto Chooser", autoChooser)
    }


    fun getAuto(): Command? {
        val selected = autoChooser.selected
        return if (selected == null) {
            null
        } else {
            AutoTest()
            //selected()
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