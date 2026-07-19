package io.rebble.libpebblecommon.connection.bt.ble.transport

import io.rebble.libpebblecommon.connection.BleScanResult
import kotlinx.coroutines.flow.Flow

/** Platform BLE scanner: Kable on android/ios, BlueZ over D-Bus on jvm/Linux. */
expect fun libpebbleBleScanner(): BleScanner

fun bleScanner(): BleScanner = libpebbleBleScanner()

interface BleScanner {
    fun scan(): Flow<BleScanResult>
}
