package org.sert2521.bunnybots2023.commands

import edu.wpi.first.wpilibj2.command.CommandBase
import org.sert2521.bunnybots2023.subsystems.BeltSubsystem

class KickerKickCommand : CommandBase() {


    init {
        // each subsystem used by the command must be passed into the addRequirements() method
        addRequirements(BeltSubsystem)
    }

    override fun initialize() {
        BeltSubsystem.setkickerSpeed(0.7)

    }

    override fun execute() {
        BeltSubsystem.kickerTime++
    }

    override fun isFinished(): Boolean {
        return BeltSubsystem.kickerTime == 20 // calibrate as needed I guess.
    }

    override fun end(interrupted: Boolean) {
        BeltSubsystem.setkickerSpeed(-.1)
        BeltSubsystem.kickerTime = 0
    }
}
