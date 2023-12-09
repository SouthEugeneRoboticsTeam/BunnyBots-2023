package org.sert2521.bunnybots2023.commands

import edu.wpi.first.wpilibj2.command.CommandBase
import org.sert2521.bunnybots2023.subsystems.Claw
import kotlin.math.sign

class ClawIntake(private val intakeSpeed:Double) : CommandBase() {

    init {
        // each subsystem used by the command must be passed into the addRequirements() method
        addRequirements(Claw)
    }

    override fun initialize() {
        Claw.setMotor(intakeSpeed)
        if (intakeSpeed.sign == -1.0){
            Claw.setCurrentLimit(45)
        } else {
            Claw.setCurrentLimit(20)
        }
    }
    override fun execute() {}

    override fun end(interrupted: Boolean) {
        Claw.stop()
    }
}
