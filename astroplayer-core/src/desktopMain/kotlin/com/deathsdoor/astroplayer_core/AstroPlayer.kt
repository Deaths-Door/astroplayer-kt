package com.deathsdoor.astroplayer_core

import com.deathsdoor.astroplayer_core.dataclasses.MediaItem
import com.deathsdoor.astroplayer_core.enums.RepeatMode
import com.deathsdoor.astroplayer_core.equalizer.EqualizerValues

//TODO add unified builder
//TODO add event listeners
/* changed to next/ previous media item ,onPaused / onPlayed/ onFastForward / onFastBackwards like that */
actual class AstroPlayer private actual constructor() {
    internal actual val mediaItems: MutableList<MediaItem>
        get() = TODO("Not yet implemented")

    /**
     * Playback
     * */
    actual fun play() {
    }

    actual fun pause() {
    }

    actual fun stop() {
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

    /**
     * Repeat Modes
     * */
    @ExperimentalMultiplatform
    internal actual var repeatedRange: Pair<Int, Int>
        get() = TODO("Not yet implemented")
        set(value) {}
    @ExperimentalMultiplatform
    actual var repeatMode: RepeatMode
        get() = TODO("Not yet implemented")
        set(value) {}
    @ExperimentalMultiplatform
    actual val shuffleModeEnabled: Boolean get() = TODO("Not yet implemented")
    @ExperimentalMultiplatform
    actual fun repeatByGroup(startIndex: Int, endIndex: Int) {}

    /**
     * Equalizer
     * **/
    @ExperimentalMultiplatform
    actual var isEqualizerEnabled: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    @ExperimentalMultiplatform
    actual var isSmartEqualizerEnabled: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    @ExperimentalMultiplatform
    actual var currentEqualizerValues: EqualizerValues
        get() = TODO("Not yet implemented")
        set(value) {}

    /**
     * PlayBackListener
     * */
    @ExperimentalMultiplatform
    actual var mediaEventListener: MediaEventListener?
        get() = TODO("Not yet implemented")
        set(value) {}

}