package io.rebble.libpebblecommon.connection.bt.ble.transport.impl

import com.juul.kable.Peripheral
import com.juul.kable.toIdentifier
import io.rebble.libpebblecommon.connection.PebbleBleIdentifier

actual fun peripheralFromIdentifier(identifier: PebbleBleIdentifier, name: String): Peripheral? =
    Peripheral(identifier.asString.toIdentifier())

// btleplug auto-negotiates the MTU; the common client re-adds the 3-byte ATT overhead in
// getMtu(), so there's nothing platform-specific to request here. (Dead on Sailfish, which uses
// the BlueZ connector, not Kable — but the actuals still have to exist for the jvm target.)
actual suspend fun Peripheral.requestMtuNative(mtu: Int): Int = mtu

actual suspend fun Peripheral.refreshServicesNative(): Boolean = false
