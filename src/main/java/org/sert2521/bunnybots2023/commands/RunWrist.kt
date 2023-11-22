package org.sert2521.bunnybots2023.commands

import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.math.controller.SimpleMotorFeedforward
import edu.wpi.first.wpilibj2.command.CommandBase
import org.sert2521.bunnybots2023.TunedConstants

class RunWrist : CommandBase() {

    private val powerPID = PIDController(TunedConstants.wristP, TunedConstants.wristI, TunedConstants.wristD)
    //private val feedforward = MotorFeed
    init {
        // each subsystem used by the command must be passed into the addRequirements() method
        addRequirements()
    }

    override fun initialize() {}

    override fun execute() {}

    override fun isFinished(): Boolean {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false
    }

    override fun end(interrupted: Boolean) {}
}
