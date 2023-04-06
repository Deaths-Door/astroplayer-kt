package com.deathsdoor.astroplayer_core

import com.deathsdoor.astroplayer_core.dataclasses.MediaItem
import com.deathsdoor.astroplayer_core.dataclasses.MediaMetadata
import com.deathsdoor.astroplayer_core.enums.RepeatMode
import com.deathsdoor.astroplayer_core.equalizer.EqualizerValues

//TODO add unified builder
//TODO add event listeners
/* changed to next/ previous media item ,onPaused / onPlayed/ onFastForward / onFastBackwards like that */
expect class AstroPlayer private constructor(){
    internal val mediaItems : ArrayList<MediaItem>
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
    var repeatMode : RepeatMode
    val shuffleModeEnabled : Boolean
    fun repeatByGroup(startIndex:Int,endIndex : Int)

    /**
     * Equalizer
     * **/
    var isEqualizerEnabled : Boolean
    var isSmartEqualizerEnabled : Boolean
    var currentEqualizerValues : EqualizerValues

    /**
     * PlayBackListener
     * */
     var mediaEventListener : MediaEventListener?
}