package org.sert2521.bunnybots2023.commands

import edu.wpi.first.math.controller.SimpleMotorFeedforward
import edu.wpi.first.wpilibj2.command.CommandBase
import org.sert2521.bunnybots2023.TunedConstants
import org.sert2521.bunnybots2023.subsystems.FlywheelSubsystem

class FlywheelRun : CommandBase() {
    private val flywheelSubsystem = FlywheelSubsystem


    init {
        // each subsystem used by the command must be passed into the addRequirements() method
        addRequirements(flywheelSubsystem)
    }
    val feedForward = SimpleMotorFeedforward(TunedConstants.flywheelS, TunedConstants.flywheelV)
    override fun initialize() {}

    override fun execute() {
        FlywheelSubsystem.setVoltage(feedForward.calculate(TunedConstants.flywheelVelocitySetpoint))
    }

    override fun isFinished(): Boolean {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false
    }

    override fun end(interrupted: Boolean) {
        FlywheelSubsystem.stop()
    }
}
