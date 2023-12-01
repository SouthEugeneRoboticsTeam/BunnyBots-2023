package org.sert2521.bunnybots2023.commands

import edu.wpi.first.wpilibj2.command.CommandBase
import org.sert2521.bunnybots2023.subsystems.Intake

class Intake(private val intakeSpeed:Double) : CommandBase() {

    init {
        // each subsystem used by the command must be passed into the addRequirements() method
        addRequirements(Intake)
    }

    override fun initialize() {
        //I don't know if this is supposed to be the subsystem "intake" or command "Intake"
        Intake.setMotor(intakeSpeed)
    }
    override fun execute() {}

    override fun end(interrupted: Boolean) {
        Intake.stop()
    }
}
