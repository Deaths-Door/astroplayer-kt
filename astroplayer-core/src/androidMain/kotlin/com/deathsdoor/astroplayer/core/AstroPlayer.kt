package com.deathsdoor.astroplayer.core

import android.content.Context
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.deathsdoor.astroplayer.core.dataclasses.MediaItem
import com.deathsdoor.astroplayer.core.enums.RepeatMode
import com.deathsdoor.astroplayer.core.equalizer.Equalizer
import com.deathsdoor.astroplayer.core.equalizer.EqualizerValues
import com.deathsdoor.astroplayer.core.interfaces.MediaEventListener
import kotlin.random.Random
import android.media.audiofx.Equalizer as ExoplayerEqualizer
import androidx.media3.common.MediaItem as ExoplayerMediaItem

actual class AstroPlayer internal actual constructor() {
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
        set(value) {
            field = value

            if(!field) return

            val equalizer = ExoplayerEqualizer(0, exoplayer!!.audioSessionId)
            equalizer.enabled = true
            val bandLevels = intArrayOf(
                (currentEqualizerValues.hz60 / 100).toInt(),
                (currentEqualizerValues.hz230 / 100).toInt(),
                (currentEqualizerValues.hz910 / 100).toInt(),
                (currentEqualizerValues.hz3600 / 100).toInt(),
                (currentEqualizerValues.hz14000 / 100).toInt()
            )
            for(x in 0 until equalizer.numberOfBands){
                equalizer.setBandLevel(x.toShort(),bandLevels[x].toShort())
            }
        }
    actual var isSmartEqualizerEnabled: Boolean = false
        set(value) {
            if(!isEqualizerEnabled) throw IllegalArgumentException("Can't enable SmartEqualizer preset if isEqualizerEnabled = false")
            field = value
            /*addMediaListener(object : MediaEventListener {
                override fun onMediaItemChange() {
                    //TODO based on the type aka genre set equalizer
                }
            })*/
        }

    actual var currentEqualizerValues: EqualizerValues = Equalizer.Default
        set(value){
            if(!isEqualizerEnabled) throw IllegalArgumentException("Can't set equalizer preset if isEqualizerEnabled = false")
            field = value
        }

    /**
     * PlayBackListener
     * */
    private val playerListeners : MutableMap<Int, MediaEventListener> = mutableMapOf()

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
            playerListeners.values.forEach { it.onMediaItemChange() }
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

    actual fun removeMediaListener(mediaEventListenerID: Int) {
        playerListeners.remove(mediaEventListenerID)
    }


    /**
     * Repeat Modes
     * */
    private val RepeatMode.asInt : Int get() = when(this){
        RepeatMode.Alle -> 2
        RepeatMode.Auf -> 0
        is RepeatMode.Current -> 1
        is RepeatMode.Group -> TODO()
    }

    actual var repeatMode: RepeatMode = RepeatMode.Alle
        set(value){
            if(value == field) return
            field = value
            exoplayer!!.repeatMode = field.asInt
        }

    actual var shuffleModeEnabled: Boolean = false
        set(value) {
            field = value
            exoplayer!!.shuffleModeEnabled = field
        }
    /**
     * Seeking
     * **/
    actual fun seekTo(milliseconds: Long) = exoplayer!!.seekTo(milliseconds)

    actual fun seekToNextMediaItem() = exoplayer!!.seekToNextMediaItem()

    actual fun seekToPreviousMediaItem() = exoplayer!!.seekToPreviousMediaItem()

    /**
     *  MediaItem
     * **/
    private val MediaItem.asExoplayerMediaItem : ExoplayerMediaItem get() = ExoplayerMediaItem.Builder()
        .setMediaId(id.toString())
        .setUri(media.toString())
        .setMediaMetadata(
            MediaMetadata.Builder()
                .setTitle(metadata.title)
                .setArtist(metadata.artist?.toString())
                .build()
        )
        .build()
    private val List<MediaItem>.asExoplayerMediaItems : List<ExoplayerMediaItem> get() = this.map { it.asExoplayerMediaItem }

    actual val currentMediaItemIndex: Int get() = exoplayer!!.currentMediaItemIndex

    actual val currentMediaItemPosition: Long get() = exoplayer!!.currentPosition

    internal actual fun updatePlayerAfterClear() = exoplayer!!.clearMediaItems()

    internal actual fun updatePlayerAfterAddMediaItem() = exoplayer!!.addMediaItem(mediaItems.last().asExoplayerMediaItem)
    internal actual fun updatePlayerAfterAddMediaItem(index: Int, mediaItem: MediaItem) = exoplayer!!.addMediaItem(index,mediaItem.asExoplayerMediaItem)
    internal actual fun updatePlayerAfterAddMediaItems(mediaItems: List<MediaItem>) = exoplayer!!.addMediaItems(mediaItems.asExoplayerMediaItems)
    internal actual fun updatePlayerAfterAddMediaItems(index: Int, mediaItems: List<MediaItem>) = exoplayer!!.addMediaItems(index,mediaItems.asExoplayerMediaItems)
    internal actual fun updatePlayerAfterRemoveAt(index: Int) = exoplayer!!.removeMediaItem(index)

    internal actual fun updatePlayerAfterRearrange(from: Int, to: Int) = exoplayer!!.moveMediaItem(from,to)
}