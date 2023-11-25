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

class RunWrist : CommandBase() {

    private val motorPID = PIDController(TunedConstants.wristP, TunedConstants.wristI, TunedConstants.wristD)
    private val feedforward = ArmFeedforward(TunedConstants.wristS, TunedConstants.wristG,
        TunedConstants.wristV, TunedConstants.wristA
    )
    private val trueEncoder = DutyCycleEncoder(ElectronicIDs.wristTrueEncoder)
    init {
        // each subsystem used by the command must be passed into the addRequirements() method
        addRequirements(Wrist)
    }

    override fun initialize() {}

    override fun execute() {
        Wrist.setVoltage(motorPID.calculate(trueEncoder.distance*PhysicalConstants.wristEncoderMultiplier, RuntimeConstants.wristSetPoint)+feedforward.calculate(RuntimeConstants.wristSetPoint, 0.0))
    }

    override fun isFinished(): Boolean {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return false
    }

    override fun end(interrupted: Boolean) {}
}
