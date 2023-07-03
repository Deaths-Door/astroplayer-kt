package com.deathsdoor.astroplayer.core

import com.deathsdoor.astroplayer.core.dataclasses.MediaItem
import com.deathsdoor.astroplayer.core.enums.RepeatMode
import com.deathsdoor.astroplayer.core.equalizer.EqualizerValues
import com.deathsdoor.astroplayer.core.interfaces.MediaEventListener

expect class AstroPlayer internal constructor(){
    internal val mediaItems : MutableList<MediaItem>

    /**
     * Playback
     * */
    fun play()
    fun pause()

    val isPlaying : Boolean

    val isPaused : Boolean

    var playBackSpeed : Float

    /**
     * Volume
     * */
    internal var previousUnMutedVolume : Float

    var volume : Float

    /**
     * Equalizer
     * **/
    var isEqualizerEnabled : Boolean

    var isSmartEqualizerEnabled : Boolean

    var currentEqualizerValues : EqualizerValues

    /**
     * PlayBackListener
     * */
    fun addMediaListener(mediaEventListener: MediaEventListener) : Int
    fun removeMediaListener(mediaEventListenerID: Int)

    /**
     * Repeat Modes
     * */
    var repeatMode : RepeatMode

    var shuffleModeEnabled : Boolean
    /**
     * Seeking
     * **/
    fun seekTo(milliseconds : Long)
    fun seekToNextMediaItem()
    fun seekToPreviousMediaItem()

    /**
     *  MediaItem
     * **/
    val currentMediaItemIndex : Int
    val currentMediaItemPosition : Long
    internal fun updatePlayerAfterClear()
    internal fun updatePlayerAfterAddMediaItem()
    internal fun updatePlayerAfterAddMediaItem(index:Int,mediaItem: MediaItem)
    internal fun updatePlayerAfterAddMediaItems(mediaItems: List<MediaItem>)
    internal fun updatePlayerAfterAddMediaItems(index:Int,mediaItems: List<MediaItem>)
    internal fun updatePlayerAfterRemoveAt(index:Int)
    internal fun updatePlayerAfterRearrange(from:Int, to:Int)
}