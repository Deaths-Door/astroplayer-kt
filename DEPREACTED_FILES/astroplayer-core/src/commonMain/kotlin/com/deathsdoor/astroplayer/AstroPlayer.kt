package com.deathsdoor.astroplayer

import com.deathsdoor.astroplayer.dataclasses.MediaItem
import com.deathsdoor.astroplayer.enums.RepeatMode
import com.deathsdoor.astroplayer.equalizer.EqualizerValues

expect class AstroPlayer private constructor(){
    internal val mediaItems : MutableList<MediaItem>
    /**
     * Playback
     * */
    fun play()
    fun pause()
    fun stop()

    val isPlaying : Boolean
    val isPaused : Boolean

    var playBackSpeed : Float

    /**
     * Volume
     * */
    internal var previousUnMutedVolume : Float
    var volume : Float

    /**
     * Seeking
     * **/
    //TODO these functions seeking need to take into account repeat mode shuffle etc
    fun seekTo(milliseconds : Long)

    fun seekToNextMediaItem()
    fun seekToPreviousMediaItem()

    /**
     *  MediaItem
     * **/
    val currentMediaItemIndex : Int
    val currentMediaItemPosition : Long

    //To update media players after adding to mediaitems list
    internal fun updatePlayerAfterClear()
    internal fun updatePlayerAfterAddMediaItem()
    internal fun updatePlayerAfterAddMediaItem(index:Int,mediaItem: MediaItem)
    internal fun updatePlayerAfterAddMediaItems(mediaItems: List<MediaItem>)
    internal fun updatePlayerAfterAddMediaItems(index:Int,mediaItems: List<MediaItem>)
    internal fun updatePlayerAfterRemoveAt(index:Int)
    internal fun updatePlayerAfterRearrange(from:Int, to:Int)

    /**
     * Repeat Modes
     * */
    internal var repeatedRange : Pair<Int,Int>
    @ExperimentalMultiplatform
    var repeatMode : RepeatMode
    @ExperimentalMultiplatform
    val shuffleModeEnabled : Boolean
    @ExperimentalMultiplatform
    fun repeatByGroup(startIndex:Int,endIndex : Int)

    /**
     * Equalizer
     * **/
    @ExperimentalMultiplatform
    var isEqualizerEnabled : Boolean
    @ExperimentalMultiplatform
    var isSmartEqualizerEnabled : Boolean
    @ExperimentalMultiplatform
    var currentEqualizerValues : EqualizerValues

    /**
     * PlayBackListener
     * */
    @ExperimentalMultiplatform
    var mediaEventListener : MediaEventListener?
}