package com.deathsdoor.astroplayer.core.listeners

import com.deathsdoor.astroplayer.core.AstroMediaItem
import com.deathsdoor.astroplayer.core.RepeatMode
import com.deathsdoor.astroplayer.core.equalizer.EqualizerValues

/**
 * Interface representing a listener for events related to playback and media state changes in AstroPlayer.
 */
interface AstroListener {
    // Buffering
    /**
     * Called when the media player starts buffering data before playback.
     */
    fun onMediaItemBuffering() = Unit

    // Playback Errors
    /**
     * Called when a general playback error occurs during playback.
     *
     * @param exception The `Exception` object representing the playback error. This can provide more details about the error.
     */
    fun onPlaybackError(exception: Exception) = Unit

    // Seeking

    /**
     * Called when the playback position is being moved forward (seeking forward).
     */
    fun onSeekForward() = Unit

    /**
     * Called when the playback position is being moved backward (seeking backward).
     */
    fun onSeekBackward() = Unit

    /**
     * Called when the playback position is seeked to the next media item in the playlist (if applicable).
     */
    fun onSeekToNextMediaItem() = Unit

    /**
     * Called when the playback position is seeked to the previous media item in the playlist (if applicable).
     */
    fun onSeekToPreviousMediaItem() = Unit

    // Playback State

    /**
     * Called when playback has started.
     */
    fun onPlaybackStarted() = Unit

    /**
     * Called when playback has been paused.
     */
    fun onPlaybackPaused() = Unit

    // Media Item Changes

    /**
     * Called when the currently playing media item has changed. The new media item might be null if playback has stopped.
     *
     * @param mediaItem The new `AstroMediaItem` object that is currently playing, or null if playback has stopped.
     */
    fun onCurrentMediaItemChanged(mediaItem: AstroMediaItem?) = Unit

    // Playback Settings

    /**
     * Called when the playback speed has changed.
     *
     * @param speed The new playback speed as a float value (e.g., 1.0 for normal speed, 2.0 for double speed).
     */
    fun onPlaybackSpeedChanged(speed: Float) = Unit

    /**
     * Called when the repeat mode for playback has changed.
     *
     * @param repeatMode The new `RepeatMode` enum value representing the current repeat mode (e.g., `RepeatMode.NONE`, `RepeatMode.ONE`, `RepeatMode.ALL`).
     */
    fun onRepeatModeChanged(repeatMode: RepeatMode) = Unit

    /**
     * Called when the shuffle mode for playback has changed.
     *
     * @param shuffleModeEnabled The new `ShuffleMode` enum value representing the current shuffle mode (e.g., `ShuffleMode.OFF`, `ShuffleMode.ON`).
     */
    fun onShuffleModeChanged(shuffleModeEnabled: Boolean) = Unit
    /**
     * Called when the playback volume has changed.
     *
     * @param volume The new playback volume level as a float value between 0.0 (silent) and 1.0 (maximum volume).
     */
    fun onVolumeChanged(volume: Float) = Unit

    /**
     * Called when the mute state of playback has changed.
     *
     * @param isMuted A boolean indicating whether playback is currently muted (true) or unmuted (false).
     */
    fun onMuteStateChanged(isMuted: Boolean) = Unit

    /**
     * Called when the overall equalizer state (enabled/disabled) has changed.
     *
     * @param isEqualizerEnabled A boolean indicating whether the equalizer is currently enabled (true) or disabled (false).
     */
    fun onEqualizerEnabledChanged(isEqualizerEnabled: Boolean)= Unit

    /**
     * Called when the smart equalizer state (enabled/disabled) has changed.
     * Smart equalizer might be a specific type of equalization that applies automatic adjustments.
     *
     * @param isSmartEqualizerEnabled A boolean indicating whether the smart equalizer is currently enabled (true) or disabled (false).
     */
    fun onSmartEqualizerEnabledChanged(isSmartEqualizerEnabled: Boolean)= Unit

    /**
     * Called when the current equalizer values have changed.
     *
     * @param currentValues An `EqualizerValues` object representing the current settings of the equalizer bands and preamp.
     */
    fun onCurrentEqualizerValuesChanged(currentValues: EqualizerValues?)= Unit
}