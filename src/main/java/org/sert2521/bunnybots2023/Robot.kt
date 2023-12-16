package org.sert2521.bunnybots2023

import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.kinematics.ChassisSpeeds
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.CommandScheduler
import edu.wpi.first.wpilibj2.command.InstantCommand
import org.sert2521.bunnybots2023.commands.*
import org.sert2521.bunnybots2023.subsystems.Claw
import org.sert2521.bunnybots2023.subsystems.Drivetrain
import org.sert2521.bunnybots2023.subsystems.Flywheel
import org.sert2521.bunnybots2023.subsystems.Wrist


/**
 * The VM is configured to automatically run this object (which basically functions as a singleton class),
 * and to call the functions corresponding to each mode, as described in the TimedRobot documentation.
 * This is written as an object rather than a class since there should only ever be a single instance, and
 * it cannot take any constructor arguments. This makes it a natural fit to be an object in Kotlin.
 *
 * If you change the name of this object or its package after creating this project, you must also update
 * the `Main.kt` file in the project. (If you use the IDE's Rename or Move refactorings when renaming the
 * object or package, it will get changed everywhere.)
 */
object Robot : TimedRobot()
{
    var flywheelCommand = FlywheelRun()
    init {
        Input
        Drivetrain
        Drivetrain.setMode(false)
        Wrist

    }

    override fun robotPeriodic()
    {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run()
        Output.update()
        //println(RuntimeConstants.wristSetPoint)
        /*
        if (Input.flywheelButton){
            flywheelCommand.schedule()
        } else {
            flywheelCommand.cancel()
        }

         */
        //0.5062x + y = -0.718
        //0.856x + y = PI/2
        //println(Wrist.trueEncoder.get())
    }

    /** This method is called once each time the robot enters Disabled mode.  */
    override fun disabledInit()
    {
        CommandScheduler.getInstance().cancelAll()
        Claw.breakingMode(true)
    }

    override fun disabledPeriodic()
    {

    }

    override fun disabledExit() {
        if (isAutonomous) {
            Input.getAuto()?.andThen(InstantCommand({ Drivetrain.stop() }))?.schedule()
            //AutoTest().schedule()
        }
        RuntimeConstants.wristSetPoint=PhysicalConstants.wristSetpointStow
    }

    /** This autonomous runs the autonomous command selected by your [RobotContainer] class.  */
    override fun autonomousInit()
    {
        // We store the command as a Robot property in the rare event that the selector on the dashboard
        // is modified while the command is running since we need to access it again in teleopInit()
        Drivetrain.defaultCommand.cancel()
        Drivetrain.removeDefaultCommand()
    }

    /** This method is called periodically during autonomous.  */
    override fun autonomousPeriodic()
    {
    }

    override fun teleopInit(){
        Drivetrain.defaultCommand = JoystickDrive(true)
        Claw.breakingMode(false)
    }

    /** This method is called periodically during operator control.  */
    override fun teleopPeriodic()
    {

    }

    override fun testInit()
    {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll()
    }

    /** This method is called periodically during test mode.  */
    override fun testPeriodic()
    {

    }

    /** This method is called once when the robot is first started up.  */
    override fun simulationInit()
    {

    }

    /** This method is called periodically whilst in simulation.  */
    override fun simulationPeriodic()
    {

    }
}