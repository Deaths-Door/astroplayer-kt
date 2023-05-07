package com.deathsdoor.astroplayer

import android.content.Context
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.deathsdoor.astroplayer.dataclasses.MediaItem
import com.deathsdoor.astroplayer.enums.RepeatMode
import com.deathsdoor.astroplayer.equalizer.Equalizer
import com.deathsdoor.astroplayer.equalizer.EqualizerValues
import com.deathsdoor.astroplayer.interfaces.MediaEventListener
import kotlin.random.Random

actual class AstroPlayer actual constructor() {
    constructor(context: Context) : this() {
        exoplayer = ExoPlayer.Builder(context).build()
        exoplayer!!.prepare()
        exoplayer!!.addListener(playerListener)

    }
    constructor(exoplayer: ExoPlayer) : this() {
        this.exoplayer = exoplayer
        this.exoplayer!!.prepare()
        this.exoplayer!!.addListener(playerListener)
    }

    private var exoplayer : ExoPlayer? = null
    internal actual val mediaItems: MutableList<MediaItem> = mutableListOf()

    /**
     * Playback
     * */
    actual fun play() = exoplayer!!.play()

    actual fun pause() = exoplayer!!.pause()

    actual val isPlaying: Boolean get() = exoplayer!!.isPlaying

    actual val isPaused: Boolean get() = !isPlaying && currentMediaItemIndex >= 0

    actual var playBackSpeed: Float = 1f
        set(value){
            field = value
            exoplayer!!.setPlaybackSpeed(field)
        }

    /**
     * Volume
     * */
    internal actual var previousUnMutedVolume: Float = 1f
    actual var volume: Float= 1f
        set(value){
            if(value !in 0f..1f) throw IllegalArgumentException("Volume has to be between 0 and 1")
            previousUnMutedVolume = field
            field = value
        }

    /**
     * Equalizer
     * **/
    actual var isEqualizerEnabled: Boolean = false
    actual var isSmartEqualizerEnabled: Boolean = false
        set(value ){
            field = value
            addMediaListener(
                object : MediaEventListener {
                    //TODO handle it
                }
            )
        }
    actual var currentEqualizerValues: EqualizerValues = Equalizer.Default
        set(value){
            if(!isEqualizerEnabled) throw IllegalArgumentException("Can't set equalizer preset if isEqualizerEnabled = false")

            field = value

            val equalizer = android.media.audiofx.Equalizer(0, exoplayer!!.audioSessionId)
            equalizer.enabled = true
            val bandLevels = intArrayOf(
                (field.hz60 / 100).toInt(),
                (field.hz230 / 100).toInt(),
                (field.hz910 / 100).toInt(),
                (field.hz3600 / 100).toInt(),
                (field.hz14000 / 100).toInt()
            )
            for(x in 0 until equalizer.numberOfBands){
                equalizer.setBandLevel(x.toShort(),bandLevels[x].toShort())
            }
        }

    /**
     * PlayBackListener
     * */
    private val playerListeners : MutableMap<Int,MediaEventListener> = mutableMapOf()

    private var playerListener : Player.Listener = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            if(playbackState != Player.STATE_READY) return

            if (exoplayer!!.playWhenReady) playerListeners.values.forEach { it.onPlaybackPlayed() }
            else playerListeners.values.forEach { it.onPlaybackPaused() }
        }

        override fun onPositionDiscontinuity(oldPosition: Player.PositionInfo, newPosition: Player.PositionInfo, reason: Int) {
            // Same media item, no change in playback position
            if (newPosition.mediaItemIndex == oldPosition.mediaItemIndex) return

            // Transition to next media item
            if (newPosition.mediaItemIndex > oldPosition.mediaItemIndex)  playerListeners.values.forEach { it.onSeekToNextMediaItem() }
            else playerListeners.values.forEach { it.onSeekToPreviousMediaItem() }
        }

        override fun onSeekForwardIncrementChanged(seekForwardIncrementMs: Long) {
            playerListeners.values.forEach { it.onSeekForward() }
        }

        override fun onSeekBackIncrementChanged(seekBackIncrementMs: Long) {
            playerListeners.values.forEach { it.onSeekBackward() }
        }
    }

    actual fun addMediaListener(mediaEventListener: MediaEventListener) : Int {
        val id = Random.nextInt()
        playerListeners[id] = mediaEventListener
        return id
    }

    actual fun removeMediaListener(mediaEventListenerID: Int) : Unit {
        playerListeners.remove(mediaEventListenerID)
    }


    /**
     * Repeat Modes
     * */
    actual var repeatMode: RepeatMode = RepeatMode.Alle

    actual val shuffleModeEnabled: Boolean get() = TODO("Not yet implemented")

    actual fun repeatByGroup(startIndex: Int, endIndex: Int) : Unit = TODO("Not yet implemented")

    /**
     * Seeking
     * **/
    actual fun seekTo(milliseconds: Long) = exoplayer!!.seekTo(milliseconds)

    actual fun seekToNextMediaItem() = exoplayer!!.seekToNextMediaItem()

    actual fun seekToPreviousMediaItem() = exoplayer!!.seekToPreviousMediaItem()

    /**
     *  MediaItem
     * **/
    actual val currentMediaItemIndex: Int get() = exoplayer!!.currentMediaItemIndex

    actual val currentMediaItemPosition: Long get() = exoplayer!!.currentPosition
}