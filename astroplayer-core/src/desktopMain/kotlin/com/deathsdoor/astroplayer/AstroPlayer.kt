package com.deathsdoor.astroplayer

import com.deathsdoor.astroplayer.dataclasses.MediaItem
import com.deathsdoor.astroplayer.enums.RepeatMode
import com.deathsdoor.astroplayer.equalizer.EqualizerValues
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.util.Duration.*
import java.time.Duration

//TODO add unified builder
//TODO add event listeners
/* changed to next/ previous media item ,onPaused / onPlayed/ onFastForward / onFastBackwards like that */
@Suppress("UNUSED")
actual class AstroPlayer private actual constructor() {
    private var mediaPlayer: MediaPlayer = MediaPlayer(Media(""))

    internal actual val mediaItems: MutableList<MediaItem> = mutableListOf()
    /**
     * Playback
     * */
    actual fun play() = mediaPlayer.play()
    actual fun pause() = mediaPlayer.pause()
    actual fun stop() = mediaPlayer.stop()

    actual val isPlaying: Boolean get() = mediaPlayer.status == MediaPlayer.Status.PLAYING
    actual val isPaused: Boolean get() = mediaPlayer.status == MediaPlayer.Status.PAUSED
    actual var playBackSpeed : Float
        get() = mediaPlayer.rate.toFloat()
        set(value) {
            mediaPlayer.rate = value.toDouble()
        }

    /**
     * Volume
     * */
    internal actual var previousUnMutedVolume: Float = 0f
    actual var volume: Float
        get() = mediaPlayer.volume.toFloat()
        set(value) {
            mediaPlayer.volume = value.toDouble()
        }

    /**
     * Seeking
     * **/
    actual fun seekTo(milliseconds: Long) = mediaPlayer.seek(millis(milliseconds.toDouble()))
    actual fun seekToNextMediaItem() = Unit
    actual fun seekToPreviousMediaItem() = Unit

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

    //TODO add all mediaeventlisteners
    @ExperimentalMultiplatform
    actual var mediaEventListener: MediaEventListener? = null
        set(value) {
            field = value
            mediaPlayer.onPlaying = Runnable { value?.onPlaybackPlayed() }
            mediaPlayer.onPaused = Runnable { value?.onPlaybackPaused() }
        }
}