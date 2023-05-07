package com.deathsdoor.astroplayer

import com.deathsdoor.astroplayer.dataclasses.MediaItem
import com.deathsdoor.astroplayer.enums.RepeatMode
import com.deathsdoor.astroplayer.equalizer.EqualizerValues
import com.deathsdoor.astroplayer.interfaces.MediaEventListener

actual class AstroPlayer {
    internal actual val mediaItems: MutableList<MediaItem>
        get() = TODO("Not yet implemented")

    /**
     * Playback
     * */
    actual fun play() {
    }

    actual fun pause() {
    }

    actual val isPlaying: Boolean
        get() = TODO("Not yet implemented")
    actual val isPaused: Boolean
        get() = TODO("Not yet implemented")
    actual var playBackSpeed: Float
        get() = TODO("Not yet implemented")
        set(value) {}

    /**
     * Volume
     * */
    internal actual var previousUnMutedVolume: Float
        get() = TODO("Not yet implemented")
        set(value) {}
    actual var volume: Float
        get() = TODO("Not yet implemented")
        set(value) {}

    /**
     * Equalizer
     * **/
    actual var isEqualizerEnabled: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    actual var isSmartEqualizerEnabled: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    actual var currentEqualizerValues: EqualizerValues
        get() = TODO("Not yet implemented")
        set(value) {}

    /**
     * PlayBackListener
     * */
    actual fun addMediaListener(mediaEventListener: MediaEventListener): Int {
        TODO("Not yet implemented")
    }

    actual fun removeMediaListener(mediaEventListenerID: Int) {
    }

    /**
     * Repeat Modes
     * */
    actual var repeatMode: RepeatMode
        get() = TODO("Not yet implemented")
        set(value) {}
    actual val shuffleModeEnabled: Boolean
        get() = TODO("Not yet implemented")

    actual fun repeatByGroup(startIndex: Int, endIndex: Int) {
    }

    /**
     * Seeking
     * **/
    actual fun seekTo(milliseconds: Long) {
    }

    actual fun seekToNextMediaItem() {
    }

    actual fun seekToPreviousMediaItem() {
    }

    /**
     *  MediaItem
     * **/
    actual val currentMediaItemIndex: Int
        get() = TODO("Not yet implemented")
    actual val currentMediaItemPosition: Long
        get() = TODO("Not yet implemented")

    internal actual fun updatePlayerAfterClear() {
    }

    internal actual fun updatePlayerAfterAddMediaItem() {
    }

    internal actual fun updatePlayerAfterAddMediaItem(
        index: Int,
        mediaItem: MediaItem
    ) {
    }

    internal actual fun updatePlayerAfterAddMediaItems(mediaItems: List<MediaItem>) {
    }

    internal actual fun updatePlayerAfterAddMediaItems(
        index: Int,
        mediaItems: List<MediaItem>
    ) {
    }

    internal actual fun updatePlayerAfterRemoveAt(index: Int) {
    }

    internal actual fun updatePlayerAfterRearrange(from: Int, to: Int) {
    }
}