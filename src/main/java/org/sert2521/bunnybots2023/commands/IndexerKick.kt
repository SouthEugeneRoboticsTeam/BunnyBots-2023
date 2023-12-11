package org.sert2521.bunnybots2023.commands

import edu.wpi.first.wpilibj2.command.CommandBase
import org.sert2521.bunnybots2023.ConfigConstants
import org.sert2521.bunnybots2023.subsystems.Indexer

class IndexerKick : CommandBase() {

    var kickerTime = 0
    init {
        // each subsystem used by the command must be passed into the addRequirements() method
        addRequirements(Indexer)
    }

    override fun initialize() {
        kickerTime = 0
        Indexer.setKickerSpeed(0.7)
        Indexer.setBeltSpeed(0.7)

    }

    override fun execute() {
        kickerTime++
    }

    override fun isFinished(): Boolean {
        return kickerTime >= ConfigConstants.kickTime // calibrate as needed I guess.
    }

    override fun end(interrupted: Boolean) {}
}
