package com.deathsdoor.astroplayer

import com.deathsdoor.astroplayer.dataclasses.MediaItem
import com.deathsdoor.astroplayer.enums.RepeatMode
import com.deathsdoor.astroplayer.equalizer.EqualizerValues
import com.deathsdoor.astroplayer.interfaces.MediaEventListener

actual class AstroPlayer actual constructor() {
    private val mediaElement = js("new MediaElement('audio',{features: [],success: function (media, node, instance) {media.load();}})")

    internal actual val mediaItems: MutableList<MediaItem> = mutableListOf()
    

    /**
     * Playback
     * */
    actual fun play() = mediaElement.play()
    
    actual fun pause() = mediaElement.pause()

    actual val isPlaying: Boolean get() = mediaElement.paused == false && mediaElement.ended == false
    
    actual val isPaused: Boolean get() = mediaElement.paused == true && mediaElement.ended == false

    actual var playBackSpeed: Float = 1f
        set(value){
            field = value
            mediaElement.playbackRate = field
        }

    /**
     * Volume
     * */
    internal actual var previousUnMutedVolume: Float = 1f
    actual var volume: Float = 1f
        set(value){
            if(value !in 0f..1f) throw IllegalArgumentException("Volume has to be between 0 and 1")
            field = value
            mediaElement.setVolume(value.toDouble())
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

}