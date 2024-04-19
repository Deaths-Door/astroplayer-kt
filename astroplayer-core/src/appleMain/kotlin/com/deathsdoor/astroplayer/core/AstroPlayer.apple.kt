package com.deathsdoor.astroplayer.core

import com.deathsdoor.astroplayer.core.equalizer.EqualizerValues
import com.deathsdoor.astroplayer.core.listeners.AstroListener
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryPlayback
import platform.AVFAudio.setActive
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.AVPlayerItemDidPlayToEndTimeNotification
import platform.AVFoundation.AVPlayerTimeControlStatusPlaying
import platform.AVFoundation.addPeriodicTimeObserverForInterval
import platform.AVFoundation.currentItem
import platform.AVFoundation.isPlaybackLikelyToKeepUp
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVFoundation.removeTimeObserver
import platform.AVFoundation.replaceCurrentItemWithPlayerItem
import platform.AVFoundation.seekToTime
import platform.AVFoundation.timeControlStatus
import platform.CoreMedia.CMTime
import platform.CoreMedia.CMTimeMakeWithSeconds
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue
import platform.Foundation.NSURL
import platform.darwin.NSEC_PER_SEC
/**
 * An class representing the underlying native media player implementation.
 **/
actual typealias NativeMediaPLayer = AVPlayer


// TODO : Implement IOS Media Player
/**
 * The main class for controlling media playback.
 *
 * AstroPlayer provides a set of methods for playing, pausing, seeking, and managing media items in a playlist.
 *
 * @constructor Creates a new AstroPlayer instance.
 *
 * @param nativeMediaPlayer An implementation of the underlying native media player that AstroPlayer delegates
 * playback functionality to. The specific type of [NativeMediaPLayer] will vary depending on the platform
 * and the [AstroPlayer] implementation.
 */
@Suppress("UNUSED")
actual open class AstroPlayer actual constructor(nativeMediaPlayer: NativeMediaPLayer) {
    constructor() : this(NativeMediaPLayer())
    /** Prepares the player for playback. This might involve buffering the media stream.**/
    actual open fun prepare() {
    }

    /**
     * Releases the player resources. This should be called when the player is no longer needed.
     */
    actual open fun release() {
    }

    /**
     * Starts playback of the current media item.
     */
    actual open fun play() {
    }

    /**
     * Pauses playback of the current media item.
     */
    actual open fun pause() {
    }

    /**
     * Indicates whether the player is currently playing media.
     */
    actual open val isPlaying: Boolean
        get() = TODO("Not yet implemented")

    /**
     * Indicates whether the player is currently paused.
     */
    actual open val isPaused: Boolean
        get() = TODO("Not yet implemented")

    /**
     * Gets or sets the playback speed. Values greater than 1.0 indicate faster playback,
     * while values less than 1.0 indicate slower playback.
     */
    actual open var playBackSpeed: Float
        get() = TODO("Not yet implemented")
        set(value) {}

    /**
     * The current repeat mode of the player.
     */
    actual open var repeatMode: RepeatMode
        get() = TODO("Not yet implemented")
        set(value) {}

    /**
     * Indicates whether shuffle mode is enabled.
     */
    actual open var shuffleModeEnabled: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}

    /**
     * Gets the current volume level of the player. The value ranges from 0.0 (silent) to 1.0 (maximum volume).
     */
    actual open val volume: Float
        get() = TODO("Not yet implemented")

    /**
     * Increases the player volume.
     */
    actual open fun increaseVolume() {
    }

    /**
     * Decreases the player volume.
     */
    actual open fun decreaseVolume() {
    }

    /**
     * Increases the player volume by a specified offset.
     *
     * @param offset The amount to increase the volume by.
     */
    actual open fun increaseVolumeBy(offset: Float) {
    }

    /**
     * Decreases the player volume by a specified offset.
     *
     * @param offset The amount to decrease the volume by.
     */
    actual open fun decreaseVolumeBy(offset: Float) {
    }

    /**
     * Indicates whether the player is muted.
     */
    actual open val isMuted: Boolean
        get() = TODO("Not yet implemented")

    /**
     * Mutes the player.
     */
    actual open fun mute() {
    }

    /**
     * Unmutes the player.
     */
    actual open fun unMute() {
    }

    /**
     * The default seek backward increment in milliseconds.
     */
    actual open val seekBackIncrement: Long
        get() = TODO("Not yet implemented")

    /**
     * The default seek forward increment in milliseconds.
     */
    actual open val seekForwardIncrement: Long
        get() = TODO("Not yet implemented")

    /**
     * Seeks to a specific position in the current media item in milliseconds.
     *
     * @param milliseconds The target position in milliseconds.
     */
    actual open fun seekTo(milliseconds: Long) {
    }

    /**
     * Selects a media item from the playlist by its index.
     *
     * @param index The index of the media item to play.
     */
    actual open fun seekToMediaItem(index: Int) {
    }

    /**
     * Gets the current playback position in milliseconds.
     */
    actual open val currentPosition: Long
        get() = TODO("Not yet implemented")

    /**
     * Gets the duration of the current media item in milliseconds.
     */
    actual open val contentDuration: Long
        get() = TODO("Not yet implemented")

    /**
     * Gets the currently playing media item, or null if no media item is playing.
     */
    actual open val currentMediaItem: AstroMediaItem?
        get() = TODO("Not yet implemented")

    /**
     * Gets the index of the currently playing media item in the playlist, or -1 if no media item is playing.
     */
    actual open val currentMediaItemIndex: Int
        get() = TODO("Not yet implemented")

    /**
     * Gets the total number of media items in the playlist.
     */
    actual open val mediaItemCount: Int
        get() = TODO("Not yet implemented")

    /**
     * Removes all media items from the playlist.
     */
    actual open fun clearMediaItems() {
    }

    /**
     * Adds a media item to the playlist.
     *
     * @param item The media item to add.
     */
    actual open fun addMediaItem(item: AstroMediaItem) {
    }

    /**
     * Inserts a media item at a specific index in the playlist.
     *
     * @param index The index at which to insert the media item.
     * @param item The media item to add.
     */
    actual open fun addMediaItem(
        index: Int,
        item: AstroMediaItem,
    ) {
    }

    /**
     * Adds a list of media items to the end of the playlist.
     *
     * @param items The list of media items to add.
     */
    actual open fun addMediaItems(items: List<AstroMediaItem>) {
    }

    /**
     * Inserts a list of media items at a specific index in the playlist.
     *
     * @param index The index at which to insert the media items.
     * @param items The list of media items to add.
     */
    actual open fun addMediaItems(
        index: Int,
        items: List<AstroMediaItem>,
    ) {
    }

    /**
     * Moves a media item from one index to another in the playlist.
     *
     * @param currentIndex The current index of the media item to move.
     * @param newIndex The new index for the media item.
     */
    actual open fun moveMediaItem(currentIndex: Int, newIndex: Int) {
    }

    /**
     * Moves a range of media items from one index to another in the playlist.
     *
     * @param fromIndex The starting index of the media items to move.
     * @param toIndex The ending index of the media items to move (exclusive).
     * @param newIndex The new index at which to insert the moved media items.
     */
    actual open fun moveMediaItems(
        fromIndex: Int,
        toIndex: Int,
        newIndex: Int,
    ) {
    }

    /**
     * Removes a media item from the playlist by its index.
     *
     * @param index The index of the media item to remove.
     */
    actual open fun removeMediaItem(index: Int) {
    }

    /**
     * Removes a range of media items from the playlist.
     *
     * @param fromIndex The starting index of the media items to remove (inclusive).
     * @param toIndex The ending index of the media items to remove (exclusive).
     */
    actual open fun removeMediaItems(fromIndex: Int, toIndex: Int) {
    }

    /**
     * Replaces a media item at a specific index in the playlist.
     *
     * @param index The index of the media item to replace.
     * @param mediaItem The new media item to insert.
     */
    actual open fun replaceMediaItem(
        index: Int,
        mediaItem: AstroMediaItem,
    ) {
    }

    /**
     * Replaces a range of media items in the playlist with a new list of media items.
     *
     * @param fromIndex The starting index of the media items to replace (inclusive).
     * @param toIndex The ending index of the media items to replace (exclusive).
     * @param mediaItems The list of new media items to insert.
     */
    actual open fun replaceMediaItems(
        fromIndex: Int,
        toIndex: Int,
        mediaItems: List<AstroMediaItem>,
    ) {
    }

    /**
     * Returns a list containing all media items in the playlist.
     *
     * @return A list of [AstroMediaItem] objects.
     */
    actual open fun allMediaItems(): List<AstroMediaItem> {
        TODO("Not yet implemented")
    }

    /**
     * Applies a transformation function to each media item in the playlist and returns a list of the transformed results.
     *
     * @param transform The transformation function to apply to each media item.
     * @return A list containing the results of applying the transformation function to each media item.
     */
    actual open fun <T> mapMediaItems(transform: (AstroMediaItem) -> T): List<T> {
        TODO("Not yet implemented")
    }

    /**
     * Gets or sets whether the equalizer is enabled.
     */
    actual var isEqualizerEnabled: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}

    /**
     * An optional callback function that allows you to provide custom equalizer values for specific equalizer preset IDs.
     * This property is used for smart equalizers, where the player can dynamically adjust the sound based on the selected preset.
     *
     * If you want to use custom equalizer values, you need to set this property to a function that takes an equalizer preset ID (as a string)
     * and returns an [EqualizerValues] object containing the desired settings for that preset.
     *
     * If this property is null, the player will use its default equalizer settings for each preset.
     */
    actual var smartEqualizerPicker: ((id: String) -> EqualizerValues)?
        get() = TODO("Not yet implemented")
        set(value) {}

    /**
     * Gets or sets whether smart equalizer is enabled. When enabled, the player will attempt to apply an equalizer
     * configuration based on the current media item's equalizer ID and the following logic:
     *
     * 1. **No Equalizer ID:** If the current media item does not have an equalizer ID set, no equalizer will be applied.
     * 2. **Default Presets:** If an equalizer ID is provided, the player will first search for a matching preset
     *    among built-in equalizer options (implementation-specific). If a match is found, the corresponding
     *    equalizer settings will be applied.
     * 3. **Custom Presets:** If no default preset is found and `smartEqualizerPicker` is not null, the player
     *    will call this callback function with the media item's equalizer ID, and those custom values will be applied.
     * 4. **No Equalizer Applied:** If none of the above conditions are met, no equalizer will be applied to the playback.
     */
    actual var isSmartEqualizerEnabled: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}

    /**
     * Gets the current equalizer values, or null if the equalizer is not enabled or not supported.
     */
    actual var currentEqualizerValues: EqualizerValues?
        get() = TODO("Not yet implemented")
        set(value) {}
    internal actual val listenersStore: MutableMap<Int, AstroListener>
        get() = TODO("Not yet implemented")

    internal actual fun registerNativeListenerForAstro() {
    }

    internal actual fun deregisterNativeListenerForAstro() {
    }


}