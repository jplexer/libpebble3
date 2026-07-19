package io.rebble.libpebblecommon.connection

actual fun platformCreatePlatformIdentifier(): CreatePlatformIdentifier =
    object : CreatePlatformIdentifier {
        override fun identifier(identifier: PebbleIdentifier, name: String): PlatformIdentifier? =
            when (identifier) {
                is PebbleBleIdentifier ->
                    PlatformIdentifier.BluezBlePlatformIdentifier(identifier)
                is PebbleBtClassicIdentifier ->
                    PlatformIdentifier.BtClassicPlatformIdentifier(identifier)
                else -> error("unknown identifier type: $identifier")
            }
    }
