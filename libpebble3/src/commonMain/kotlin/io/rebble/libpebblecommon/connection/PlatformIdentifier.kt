package io.rebble.libpebblecommon.connection

import com.juul.kable.Peripheral
import io.rebble.libpebblecommon.connection.bt.ble.transport.impl.peripheralFromIdentifier

sealed class PlatformIdentifier {
    class BlePlatformIdentifier(val peripheral: Peripheral) : PlatformIdentifier()
    /** BLE identifier for platforms whose central role isn't Kable (jvm/Linux via BlueZ). */
    class BluezBlePlatformIdentifier(val identifier: PebbleBleIdentifier) : PlatformIdentifier()
    class SocketPlatformIdentifier(val addr: String) : PlatformIdentifier()
    class BtClassicPlatformIdentifier(val identifier: PebbleBtClassicIdentifier) : PlatformIdentifier()
}


interface CreatePlatformIdentifier {
    fun identifier(identifier: PebbleIdentifier, name: String): PlatformIdentifier?
}

/** Platform factory: Kable-peripheral-backed on android/ios, BlueZ-backed on jvm/Linux. */
expect fun platformCreatePlatformIdentifier(): CreatePlatformIdentifier

class RealCreatePlatformIdentifier : CreatePlatformIdentifier {
    override fun identifier(identifier: PebbleIdentifier, name: String): PlatformIdentifier? = when (identifier) {
        is PebbleBleIdentifier -> peripheralFromIdentifier(identifier, name)?.let {
            PlatformIdentifier.BlePlatformIdentifier(
                it
            )
        }

        is PebbleBtClassicIdentifier -> PlatformIdentifier.BtClassicPlatformIdentifier(identifier)

        else -> error("unknown identifier type: $identifier")
    }
}