package com.deathsdoor.astroplayer_core

import android.content.Context
import android.net.Uri
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.deathsdoor.astroplayer_core.dataclasses.MediaItem
import com.deathsdoor.astroplayer_core.enums.RepeatMode
import com.deathsdoor.astroplayer_core.equalizer.Equalizer
import com.deathsdoor.astroplayer_core.equalizer.EqualizerValues
import android.media.audiofx.Equalizer as ExoplayerEqualizer
import androidx.media3.common.MediaItem as ExoplayerMediaItem

@Suppress("UNUSED")
actual class AstroPlayer private actual constructor() {
    constructor(context: Context) : this() {
        this.mediaPlayer = ExoPlayer.Builder(context).build().also { it.prepare() }
    }
    constructor(exoplayer: ExoPlayer) : this() {
        this.mediaPlayer = exoplayer.also { it.prepare() }
    }

    private var _mediaPlayer: ExoPlayer? = null
    var mediaPlayer get() = _mediaPlayer!!
        set(value) {
            _mediaPlayer = value
        }
    internal actual val mediaItems : MutableList<MediaItem> = mutableListOf()

    /**
     * Playback
     * */
    actual fun play() = mediaPlayer.play()
    actual fun pause() = mediaPlayer.pause()
    actual fun stop() = mediaPlayer.stop()

    actual val isPlaying: Boolean get() = mediaPlayer.isPlaying
    actual val isPaused: Boolean get() =  !isPlaying && currentMediaItemIndex >= 0

    actual var playBackSpeed: Float = 1f
        set(value){
            field = value
            this.mediaPlayer.setPlaybackSpeed(field)
        }

    /**
     * Volume
     * */
    actual var volume: Float = 1.0f
        set(value){
            if(value !in 0.0f..1.0f) throw IllegalArgumentException("Volume has to be between 0 and 1")
            field = value
        }
    internal actual var previousUnMutedVolume: Float = volume
    /**
     * Seeking
     * **/
    actual fun seekTo(milliseconds: Long) = mediaPlayer.seekTo(milliseconds)

    actual fun seekToNextMediaItem() = mediaPlayer.seekToNextMediaItem()
    actual fun seekToPreviousMediaItem() = mediaPlayer.seekToPreviousMediaItem()

    /**
     *  MediaItem
     * **/
    private val MediaItem.asExoplayerMediaItem : ExoplayerMediaItem get() = ExoplayerMediaItem.Builder()
        .setMediaId(this.mediaID)
        .setUri(Uri.parse(this.mediaSource))
        .setMediaMetadata(
            androidx.media3.common.MediaMetadata.Builder()
                .setTitle(this.metadata.title)
                .setArtist(this.metadata.artist)
                .build()
        )
        .build()
    private val List<MediaItem>.asExoplayerMediaItem : List<ExoplayerMediaItem> get() = this.map { it.asExoplayerMediaItem }

    actual val currentMediaItemIndex: Int get() = mediaPlayer.currentMediaItemIndex
    actual val currentMediaItemPosition: Long get() = mediaPlayer.currentPosition

    internal actual fun updatePlayerAfterClear() = this.mediaPlayer.clearMediaItems()

    internal actual fun updatePlayerAfterAddMediaItem() = this.mediaPlayer.addMediaItem(this.mediaItems.last().asExoplayerMediaItem)
    internal actual fun updatePlayerAfterAddMediaItem(index: Int,mediaItem: MediaItem) = this.mediaPlayer.addMediaItem(index,mediaItem.asExoplayerMediaItem)

    internal actual fun updatePlayerAfterAddMediaItems(mediaItems: List<MediaItem>) = this.mediaPlayer.addMediaItems(mediaItems.asExoplayerMediaItem)
    internal actual fun updatePlayerAfterAddMediaItems(index: Int, mediaItems: List<MediaItem>) = this.mediaPlayer.addMediaItems(index,mediaItems.asExoplayerMediaItem)
    internal actual fun updatePlayerAfterRemoveAt(index: Int) = this.mediaPlayer.removeMediaItem(index)
    internal actual fun updatePlayerAfterRearrange(from: Int, to: Int) = this.mediaPlayer.moveMediaItem(from,to)
    /**
     * Repeat Modes
     * */
    internal actual var repeatedRange: Pair<Int, Int> get() = TODO("Not yet implemented") ; set(value) {}
    actual var repeatMode: RepeatMode = RepeatMode.Auf
        set(value){
            if(repeatMode == RepeatMode.Group) throw IllegalArgumentException("RepeatMode.Group has to be used with a range")
            field = value
            //TODO add repeatMode function
        }
    actual val shuffleModeEnabled: Boolean get() = TODO("Not yet implemented")
    actual fun repeatByGroup(startIndex:Int,endIndex : Int) : Unit = TODO("Not yet implemented")
    /**
     * Equalizer
     * **/
    @ExperimentalMultiplatform
    actual var isEqualizerEnabled: Boolean = false
    @ExperimentalMultiplatform
    actual var isSmartEqualizerEnabled: Boolean = false

    //TODO update equalizer when variable Hz changed
    @ExperimentalMultiplatform
    actual var currentEqualizerValues: EqualizerValues = Equalizer.Default
        set(value){
            if(!isEqualizerEnabled) throw IllegalArgumentException("Can't set equalizer preset if isEqualizerEnabled = false")

            field = value

            val equalizer = ExoplayerEqualizer(0, mediaPlayer.audioSessionId)
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
    private var playerListener : Player.Listener? = null
    @ExperimentalMultiplatform
    actual var mediaEventListener : MediaEventListener? = null
}