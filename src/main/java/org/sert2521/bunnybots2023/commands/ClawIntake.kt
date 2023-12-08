package org.sert2521.bunnybots2023.commands

import edu.wpi.first.wpilibj2.command.CommandBase
import org.sert2521.bunnybots2023.subsystems.Claw

class ClawIntake(private val intakeSpeed:Double) : CommandBase() {

    init {
        // each subsystem used by the command must be passed into the addRequirements() method
        addRequirements(Claw)
    }

    override fun initialize() {
        Claw.setMotor(intakeSpeed)
    }
    override fun execute() {}

    override fun end(interrupted: Boolean) {
        Claw.stop()
    }
}
