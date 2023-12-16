package org.sert2521.bunnybots2023.commands

import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.kinematics.ChassisSpeeds
import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj2.command.CommandBase
import org.sert2521.bunnybots2023.subsystems.Drivetrain

class AutoTest : CommandBase() {

    var time = 0.0
    init {
        // each subsystem used by the command must be passed into the addRequirements() method
        addRequirements(Drivetrain)
        time = Timer.getFPGATimestamp()
    }

    override fun initialize() {
        Drivetrain.setNewPose(Pose2d())
    }

    override fun execute() {
        Drivetrain.drive(ChassisSpeeds(2.0, 0.0, 0.0))
    }

    override fun isFinished(): Boolean {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return Timer.getFPGATimestamp() > time + 5
    }

    override fun end(interrupted: Boolean) {}
}
