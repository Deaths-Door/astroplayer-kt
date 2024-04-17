package com.deathsdoor.astroplayer.core.listeners

import com.deathsdoor.astroplayer.core.AstroMediaItem
import com.deathsdoor.astroplayer.core.RepeatMode

/**
 * Interface representing a listener for events related to playback and media state changes in AstroPlayer.
 */
interface AstroListener {
    // Buffering
    /**
     * Called when the media player starts buffering data before playback.
     */
    fun onMediaItemBuffering()

    // Playback Errors
    /**
     * Called when a general playback error occurs during playback.
     *
     * @param exception The `Exception` object representing the playback error. This can provide more details about the error.
     */
    fun onPlaybackError(exception: Exception)

    // Seeking

    /**
     * Called when the playback position is being moved forward (seeking forward).
     */
    fun onSeekForward()

    /**
     * Called when the playback position is being moved backward (seeking backward).
     */
    fun onSeekBackward()

    /**
     * Called when the playback position is seeked to the next media item in the playlist (if applicable).
     */
    fun onSeekToNextMediaItem()

    /**
     * Called when the playback position is seeked to the previous media item in the playlist (if applicable).
     */
    fun onSeekToPreviousMediaItem()

    // Playback State

    /**
     * Called when playback has started.
     */
    fun onPlaybackStarted()

    /**
     * Called when playback has been paused.
     */
    fun onPlaybackPaused()

    // Media Item Changes

    /**
     * Called when the currently playing media item has changed. The new media item might be null if playback has stopped.
     *
     * @param mediaItem The new `AstroMediaItem` object that is currently playing, or null if playback has stopped.
     */
    fun onCurrentMediaItemChanged(mediaItem: AstroMediaItem?)

    // Playback Settings

    /**
     * Called when the playback speed has changed.
     *
     * @param speed The new playback speed as a float value (e.g., 1.0 for normal speed, 2.0 for double speed).
     */
    fun onPlaybackSpeedChanged(speed: Float)

    /**
     * Called when the repeat mode for playback has changed.
     *
     * @param repeatMode The new `RepeatMode` enum value representing the current repeat mode (e.g., `RepeatMode.NONE`, `RepeatMode.ONE`, `RepeatMode.ALL`).
     */
    fun onRepeatModeChanged(repeatMode: RepeatMode)

    /**
     * Called when the shuffle mode for playback has changed.
     *
     * @param shuffleModeEnabled The new `ShuffleMode` enum value representing the current shuffle mode (e.g., `ShuffleMode.OFF`, `ShuffleMode.ON`).
     */
    fun onShuffleModeChanged(shuffleModeEnabled: Boolean)
    /**
     * Called when the playback volume has changed.
     *
     * @param volume The new playback volume level as a float value between 0.0 (silent) and 1.0 (maximum volume).
     */
    fun onVolumeChanged(volume: Float)

    /**
     * Called when the mute state of playback has changed.
     *
     * @param isMuted A boolean indicating whether playback is currently muted (true) or unmuted (false).
     */
    fun onMuteStateChanged(isMuted: Boolean)
}