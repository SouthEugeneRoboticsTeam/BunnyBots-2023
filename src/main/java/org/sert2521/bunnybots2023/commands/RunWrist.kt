package org.sert2521.bunnybots2023.commands


import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel
import com.revrobotics.SparkMaxAbsoluteEncoder
import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.math.controller.ArmFeedforward
import edu.wpi.first.wpilibj.DutyCycleEncoder
import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.motorcontrol.Spark
import edu.wpi.first.wpilibj2.command.CommandBase
import org.sert2521.bunnybots2023.ElectronicIDs
import org.sert2521.bunnybots2023.PhysicalConstants
import org.sert2521.bunnybots2023.RuntimeConstants
import org.sert2521.bunnybots2023.TunedConstants
import org.sert2521.bunnybots2023.subsystems.Wrist
import kotlin.math.PI
import kotlin.math.sign

class RunWrist : CommandBase() {


    private var wristAngle = Wrist.getRadians()
    init {
        // each subsystem used by the command must be passed into the addRequirements() method
        addRequirements(Wrist)
    }

    override fun initialize() {}

    override fun execute() {
        wristAngle = Wrist.getRadians()


    }

    override fun isFinished(): Boolean {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false
    }

    override fun end(interrupted: Boolean) {}
}
