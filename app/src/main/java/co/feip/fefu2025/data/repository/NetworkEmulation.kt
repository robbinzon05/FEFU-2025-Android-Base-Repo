package co.feip.fefu2025.data.repository

import kotlinx.coroutines.delay
import kotlin.random.Random

suspend fun imitateDelayAndRandomError(
    millis: Long = 1500,
    errorChance: Float = 0.2f
) {
    delay(millis)
    if (Random.nextFloat() < errorChance) {
        throw RuntimeException("Mock network error")
    }
}
