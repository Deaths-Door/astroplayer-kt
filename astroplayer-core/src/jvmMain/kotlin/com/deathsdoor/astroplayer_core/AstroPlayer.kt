package com.deathsdoor.astroplayer_core

import com.deathsdoor.astroplayer_core.dataclasses.MediaItem
import com.deathsdoor.astroplayer_core.enums.RepeatMode
import com.deathsdoor.astroplayer_core.equalizer.EqualizerValues

actual class AstroPlayer private actual constructor(){
    internal actual val mediaItems: MutableList<MediaItem> = mutableListOf()
//    private var mediaPlayer: MediaPlayer
   /* init {
        NativeDiscovery().discover()
        mediaPlayer = CallbackMediaPlayerComponent().mediaPlayer()
            //EmbeddedMediaPlayerComponent().mediaPlayer()
    }*/


    fun testStart(){
   //     mediaPlayer.media().start("C:\\Users\\Aarav Aditya Shah\\Desktop\\Alan Walker - Different World (feat. Sofia Carson, K-391  CORSAK).mp3")
        Thread.sleep(10000)
     //   mediaPlayer.release()
    }

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
    internal actual var repeatedRange: Pair<Int, Int>
        get() = TODO("Not yet implemented")
        set(value) {}
    actual var repeatMode: RepeatMode
        get() = TODO("Not yet implemented")
        set(value) {}
    actual val shuffleModeEnabled: Boolean
        get() = TODO("Not yet implemented")

    actual fun repeatByGroup(startIndex: Int, endIndex: Int) {
    }

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
    actual var mediaEventListener: MediaEventListener?
        get() = TODO("Not yet implemented")
        set(value) {}
}