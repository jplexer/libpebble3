package io.rebble.libpebblecommon.connection.bt.ble.transport.impl

import com.juul.kable.Advertisement
import com.juul.kable.Identifier
import com.juul.kable.Scanner
import io.rebble.libpebblecommon.connection.PebbleBleIdentifier
import io.rebble.libpebblecommon.connection.bt.ble.transport.BleScanner
import kotlinx.coroutines.flow.Flow

actual fun kableBleScanner(): BleScanner = KableBleScanner()

internal actual fun createKableAdvertisementsFlow(): Flow<Advertisement> = Scanner {
    filters {
        match { }
    }
}.advertisements

// On the Kable JVM/btleplug backend the identifier's string form is the device address
// (e.g. "AA:BB:CC:DD:EE:FF" on Linux), which round-trips through PebbleBleIdentifier's wrapper.
actual fun Identifier.asPebbleBleIdentifier(): PebbleBleIdentifier =
    PebbleBleIdentifier(toString())