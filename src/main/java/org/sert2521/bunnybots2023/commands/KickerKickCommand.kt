package org.sert2521.bunnybots2023.commands

import edu.wpi.first.wpilibj2.command.CommandBase
import org.sert2521.bunnybots2023.ConfigConstants
import org.sert2521.bunnybots2023.subsystems.BeltSubsystem
import java.io.ObjectInputFilter.Config

class KickerKickCommand : CommandBase() {


    init {
        // each subsystem used by the command must be passed into the addRequirements() method
        addRequirements(BeltSubsystem)
    }

    override fun initialize() {
        BeltSubsystem.kickerTime = 0
        BeltSubsystem.setKickerSpeed(0.7)
        BeltSubsystem.setBeltSpeed(0.7)

    }

    override fun execute() {
        BeltSubsystem.kickerTime++
    }

    override fun isFinished(): Boolean {
        return BeltSubsystem.kickerTime >= ConfigConstants.kickTime // calibrate as needed I guess.
    }

    override fun end(interrupted: Boolean) {}
}
