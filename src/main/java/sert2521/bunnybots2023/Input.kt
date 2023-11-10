package sert2521.bunnybots2023

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj2.command.button.JoystickButton
import com.pathplanner.lib.auto.SwerveAutoBuilder
import com.pathplanner.lib.util.PIDConstants
import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.DriverStation.Alliance
import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.*
import sert2521.bunnybots2023.subsystems.Drivetrain
import org.sert2521.bunnybots2023.commands.*

object Input {
    // Replace with constants
    private val driverController = XboxController(0)
    private val gunnerController = Joystick(1)

    private val resetAngle = JoystickButton(driverController, 4)
    private val slowButton = JoystickButton(driverController, 5)
    //private val coneAlignButton = JoystickButton(driverController, 6)
    //private val coneAlignConeRot = JoystickButton(driverController, 1)

    private val outtake = JoystickButton(gunnerController, 13).or(JoystickButton(driverController, 6))
    private val intake = JoystickButton(gunnerController, 14)

    private val liftDrive = JoystickButton(gunnerController, 5)
    private val liftConeHigh = JoystickButton(gunnerController, 6)
    private val liftCubeHigh = JoystickButton(gunnerController, 7)
    private val liftMid = JoystickButton(gunnerController, 8)
    private val liftLow = JoystickButton(gunnerController, 9)
    private val liftIntakeTippedCone = JoystickButton(gunnerController, 10)
    private val liftIntakeCube = JoystickButton(gunnerController, 16)
    private val liftIntakeCone = JoystickButton(gunnerController, 15)
    private val liftSingleSubstation = JoystickButton(gunnerController, 11)

    // Has to do this function thing so the robot can do andThen(auto) more than once
    private val autoChooser = SendableChooser<() -> Command?>()
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



    var slowMode = false

    init {
        // Put these strings in constants maybe
        autoChooser.setDefaultOption("Nothing") { null }
        for (path in ConfigConstants.paths) {
            autoChooser.addOption(path.first) { autoBuilder.fullAuto(path.second) }
        }



        SmartDashboard.putData("Auto Chooser", autoChooser)

        slowButton.onTrue(InstantCommand({ slowMode = !slowMode }))
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
    fun getFast(): Double {
        return if (!slowMode) {
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