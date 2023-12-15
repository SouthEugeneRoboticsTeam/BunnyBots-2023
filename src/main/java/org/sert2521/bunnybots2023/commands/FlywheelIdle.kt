package org.sert2521.bunnybots2023.commands

import edu.wpi.first.math.controller.SimpleMotorFeedforward
import edu.wpi.first.wpilibj2.command.CommandBase
import org.sert2521.bunnybots2023.TunedConstants
import org.sert2521.bunnybots2023.subsystems.Flywheel

class FlywheelIdle : CommandBase() {
    private val flywheelSubsystem = Flywheel


    init {
        // each subsystem used by the command must be passed into the addRequirements() method
        addRequirements(flywheelSubsystem)
    }
    val feedForward = SimpleMotorFeedforward(TunedConstants.flywheelS, TunedConstants.flywheelV, TunedConstants.flywheelA)
    override fun initialize() {}

    override fun execute() {
        Flywheel.setVoltage(feedForward.calculate(TunedConstants.flywheelIdle))
    }

    override fun isFinished(): Boolean {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false
    }

    override fun end(interrupted: Boolean) {
        Flywheel.stop()
    }
}
