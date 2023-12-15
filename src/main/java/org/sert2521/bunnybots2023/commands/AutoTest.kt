package org.sert2521.bunnybots2023.commands

import edu.wpi.first.math.kinematics.ChassisSpeeds
import edu.wpi.first.wpilibj2.command.CommandBase
import org.sert2521.bunnybots2023.subsystems.Drivetrain

class AutoTest : CommandBase() {


    init {
        // each subsystem used by the command must be passed into the addRequirements() method
        addRequirements(Drivetrain)
    }

    override fun initialize() {}

    override fun execute() {
        println("its working")
        Drivetrain.drive(ChassisSpeeds(0.0, 0.0, 0.0))
    }

    override fun isFinished(): Boolean {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false
    }

    override fun end(interrupted: Boolean) {}
}
