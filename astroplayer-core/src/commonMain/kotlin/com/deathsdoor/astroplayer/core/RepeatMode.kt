package com.deathsdoor.astroplayer.core

/**
 * An enum class representing the repeat mode of the AstroPlayer.
 */
enum class RepeatMode {

    /**
     * Playback will not be repeated.
     */
    Off,

    /**
     * The entire playlist will be repeated after reaching the end.
     */
    All,

    /**
     * The current media item will be repeated continuously.
     */
    One
}
