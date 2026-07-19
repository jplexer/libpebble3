package io.rebble.libpebblecommon.connection

actual fun platformCreatePlatformIdentifier(): CreatePlatformIdentifier =
    RealCreatePlatformIdentifier()
