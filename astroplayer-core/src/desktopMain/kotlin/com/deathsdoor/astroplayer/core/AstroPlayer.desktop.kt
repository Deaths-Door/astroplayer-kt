@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.deathsdoor.astroplayer.core

import com.deathsdoor.astroplayer.core.equalizer.EqualizerPresets
import com.deathsdoor.astroplayer.core.equalizer.EqualizerValues
import com.deathsdoor.astroplayer.core.listeners.AstroListener
import com.eygraber.uri.Uri
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery
import uk.co.caprica.vlcj.media.MediaRef
import uk.co.caprica.vlcj.media.Meta
import uk.co.caprica.vlcj.player.base.Equalizer
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter
import uk.co.caprica.vlcj.player.component.EmbeddedMediaListPlayerComponent
import java.lang.Exception
import java.util.Collections

actual typealias NativeMediaPLayer = EmbeddedMediaListPlayerComponent

@Suppress("UNUSED")
actual open class AstroPlayer actual constructor(private val nativeMediaPlayer: NativeMediaPLayer) {
    init {
        NativeDiscovery().discover()
    }
    private val _nativeMediaPlayer get() = nativeMediaPlayer.mediaPlayer()
    actual open fun prepare() = Unit
    actual open fun release() = nativeMediaPlayer.release()

    actual open fun play() = _nativeMediaPlayer.controls().play()

    actual open fun pause() = _nativeMediaPlayer.controls().pause()

    actual open val isPlaying: Boolean get() = _nativeMediaPlayer.status().isPlaying
    actual open val isPaused: Boolean get() = !_nativeMediaPlayer.status().isPlaying && _nativeMediaPlayer.media().info().audioTracks().size >= 1
    actual open var playBackSpeed: Float
        get() = _nativeMediaPlayer.status().rate()
        set(value) {
            _nativeMediaPlayer.controls().setRate(value)
            forEachListener { it.onPlaybackSpeedChanged(value) }
        }

    private val rawVolume = _nativeMediaPlayer.audio().volume()
    actual open val volume: Float get() = rawVolume.toFloat()

    actual open fun increaseVolume() {
        _nativeMediaPlayer.audio().setVolume(rawVolume + 10)
    }

    actual open fun decreaseVolume() {
        _nativeMediaPlayer.audio().setVolume(rawVolume - 10)
    }

    actual open fun increaseVolumeBy(offset : Float) {
        _nativeMediaPlayer.audio().setVolume(rawVolume + offset.toInt())
    }

    actual open fun decreaseVolumeBy(offset : Float) {
        _nativeMediaPlayer.audio().setVolume(rawVolume - offset.toInt())
    }
    actual open val isMuted : Boolean get() = _nativeMediaPlayer.audio().isMute

    actual open fun mute() {
        _nativeMediaPlayer.audio().isMute = true
    }

    actual open fun unMute() {
        _nativeMediaPlayer.audio().isMute = false
    }

    actual open val seekBackIncrement : Long = 5000
    actual open val seekForwardIncrement : Long = 5000

    actual open fun seekTo(milliseconds: Long) {
        val old = currentPosition
        _nativeMediaPlayer.controls().setTime(milliseconds)

        if(old < currentPosition) forEachListener { it.onSeekForward() }
        else forEachListener { it.onSeekBackward() }
    }

    actual open val currentPosition : Long get() = _nativeMediaPlayer.status().time()
    actual open val contentDuration : Long get() = _nativeMediaPlayer.status().length()

    private val astroMediaItems = Collections.synchronizedList(mutableListOf<AstroMediaItem>())

    private val nativePlaylistManager get() = nativeMediaPlayer.mediaListPlayer().list()

    actual open val currentMediaItem: AstroMediaItem? get() = astroMediaItems.getOrNull(currentMediaItemIndex)
    actual open val mediaItemCount: Int get() = astroMediaItems.size

    actual open fun clearMediaItems() {
        nativePlaylistManager.media().clear()
    }

    actual open fun addMediaItem(item: AstroMediaItem) {
        astroMediaItems.add(item)

        val mediaItem = nativeMediaPlayer.mediaPlayer().media().newMedia().apply {
            meta().apply metadata@ {
                val astroMetadata = item.metadata ?: return@metadata

                set(Meta.TITLE,astroMetadata.title)
                set(Meta.ARTIST, astroMetadata.artist)
                set(Meta.ALBUM, astroMetadata.albumTitle)
                set(Meta.ALBUM_ARTIST, astroMetadata.albumArtist)
                set(Meta.GENRE, astroMetadata.genre)
                set(Meta.TRACK_NUMBER, astroMetadata.trackNumber.toString())
                set(Meta.DESCRIPTION, astroMetadata.description)
                set(Meta.DATE, astroMetadata.releaseDay.toString())
                set(Meta.URL, item.source.toString())
                set(Meta.ARTWORK_URL, astroMetadata.artworkUri.toString())
                set(Meta.TRACK_TOTAL, astroMetadata.totalTrackCount.toString())
                set(Meta.DISC_NUMBER, astroMetadata.discNumber.toString())
                set(Meta.DISC_TOTAL, astroMetadata.totalDiscCount.toString())
            }
        }

        nativePlaylistManager.media().add(mediaItem.newMediaRef())
    }

    actual open fun addMediaItem(
        index: Int,
        item: AstroMediaItem,
    ) {
        astroMediaItems.add(index,item)
        nativePlaylistManager.media().insert(index,item.source.toString())
    }

    actual open fun addMediaItems(items: List<AstroMediaItem>) {
        for(item in items) {
            addMediaItem(item)
        }
    }

    actual open fun addMediaItems(index: Int, items: List<AstroMediaItem>) {
        for(item in items) {
            addMediaItem(index,item)
        }
    }

    actual open fun removeMediaItem(index: Int) {
        astroMediaItems.removeAt(index)
        nativePlaylistManager.media().remove(index)
    }

    actual open fun removeMediaItems(fromIndex: Int, toIndex: Int) {
        // No removeRange function availiable
        // Refer to this [this](https://youtrack.jetbrains.com/issue/KT-13951/MutableList-is-missing-a-method-to-remove-multiple-items-by-position-or-range)
        astroMediaItems.subList(fromIndex,toIndex).clear()

        val media = nativePlaylistManager.media()

        for(index in fromIndex..toIndex) {
            media.remove(index)
        }
    }

    actual open fun replaceMediaItem(
        index: Int,
        mediaItem: AstroMediaItem,
    ) {
        val media = nativePlaylistManager.media()
        media.remove(index)
        addMediaItem(index,mediaItem)
    }

    actual open fun replaceMediaItems(
        fromIndex: Int,
        toIndex: Int,
        mediaItems: List<AstroMediaItem>,
    ) {
        removeMediaItems(fromIndex,toIndex)
        addMediaItems(fromIndex,mediaItems)
    }

    actual open fun moveMediaItem(currentIndex: Int, newIndex: Int) {
        val mediaItem = astroMediaItems.removeAt(currentIndex)
        nativePlaylistManager.media().remove(currentIndex)
        addMediaItem(newIndex,mediaItem)
    }

    actual open fun moveMediaItems(
        fromIndex: Int,
        toIndex: Int,
        newIndex: Int,
    ) {
        val mediaItems = astroMediaItems.slice(fromIndex..toIndex)

        removeMediaItems(fromIndex, toIndex)

        addMediaItems(newIndex,mediaItems)
    }


    actual open fun seekToMediaItem(index: Int) {
        val media = _nativeMediaPlayer.media()
        nativeMediaPlayer.mediaPlayer().audio().setTrack(media.info().audioTracks()[index].id())
    }

    actual open fun allMediaItems(): List<AstroMediaItem> = astroMediaItems
    actual open fun <T> mapMediaItems(transform: (AstroMediaItem) -> T): List<T> = astroMediaItems.map(transform)


    actual open var repeatMode: RepeatMode = RepeatMode.Off
        set(value) {
            field = value
            nativeMediaPlayer.mediaListPlayer().controls().setMode(field.toPlaybackMode())
            forEachListener { it.onRepeatModeChanged(field) }
        }

    actual open var shuffleModeEnabled: Boolean = false
        set(value) {
            field = value
            // TODO: Same issue as onCurrentMediaItemChanged

            forEachListener { it.onShuffleModeChanged(field) }
        }

    // Singular Listener to increase efficiency and make my life easier
    private val onMediaChangedListener : MediaPlayerEventAdapter =  object : MediaPlayerEventAdapter() {
        override fun mediaChanged(mediaPlayer: MediaPlayer, media: MediaRef) {
            super.mediaChanged(mediaPlayer, media)

            // TODO : CHECK IF THIS WILL WORK -> Same issue as onCurrentMediaItemChanged
            // Find the current media item index
            // Basically since [_nativeMediaPlayer] repeats the current media item
            // Hence compare its source to our collection
            val mrl = _nativeMediaPlayer.media().info().mrl()
            val source = Uri.parse(mrl)

            _cachedCurrentMediaItemIndex = astroMediaItems.indexOfFirst { it.source == source }

            _smartEqualizerUpdater?.invoke(mediaPlayer,media)
        }
    }

    init {
        _nativeMediaPlayer.events().addMediaPlayerEventListener(onMediaChangedListener)
    }

    private var _cachedCurrentMediaItemIndex : Int = -1
    actual open val currentMediaItemIndex: Int get() = _cachedCurrentMediaItemIndex

    private val equalizerApi = nativeMediaPlayer.mediaPlayerFactory().equalizer()
    private val _equalizer : Equalizer? get() = _nativeMediaPlayer.audio().equalizer()

    actual var isEqualizerEnabled: Boolean = _equalizer != null
        get() = _equalizer != null
        set(value) {
            field = value
            if(!field) _nativeMediaPlayer.audio().setEqualizer(null)

            forEachListener { it.onEqualizerEnabledChanged(field) }
        }

    actual var currentEqualizerValues: EqualizerValues? = null
        set(value) {
            field = value

            if(isEqualizerEnabled && field != null) {
                val equalizer = equalizerApi.newEqualizer(field!!.identifier)

                val amps = field!!.map { band -> (band * 1000).toInt().toFloat() }
                equalizer.setAmps(amps.toFloatArray())

                _nativeMediaPlayer.audio().setEqualizer(equalizer)
            }

            forEachListener { it.onCurrentEqualizerValuesChanged(field) }

        }

    actual var smartEqualizerPicker: ((id: String) -> EqualizerValues)? = null

    private var _smartEqualizerUpdater : ((mediaPlayer: MediaPlayer, media: MediaRef) -> Unit)? = null
    actual var isSmartEqualizerEnabled: Boolean = isEqualizerEnabled
        set(value) {
            field = value

            when(field) {
                true -> {
                    isEqualizerEnabled = true

                    _smartEqualizerUpdater = { _ , _ ->
                        val identifier = currentMediaItem?.metadata?.equalizerIdentifier

                        currentEqualizerValues = when(identifier) {
                            null -> EqualizerPresets.Default
                            else -> EqualizerPresets.AllPresets[identifier] ?: smartEqualizerPicker?.invoke(identifier)
                        }
                    }
                }
                false -> _smartEqualizerUpdater = null
            }

            forEachListener { it.onSmartEqualizerEnabledChanged(field) }

        }

    private var astroNativeListener : MediaPlayerEventAdapter? = null
    internal actual val listenersStore: MutableMap<Int, AstroListener> = mutableMapOf()

    internal actual fun registerNativeListenerForAstro() {
        nativeMediaPlayer.mediaPlayer().events().addMediaPlayerEventListener(object : MediaPlayerEventAdapter(){
            override fun buffering(mediaPlayer: MediaPlayer?, newCache: Float) {
                super.buffering(mediaPlayer, newCache)
                forEachListener { it.onMediaItemBuffering() }
            }

            override fun error(mediaPlayer: MediaPlayer?) {
                super.error(mediaPlayer)
                // TODO : Get error?
                forEachListener { it.onPlaybackError(Exception("Can't get error for desktop")) }
            }

            // TODO: onCurrentMediaItemChanged -> Can't detect on Mediaitem changed
            override fun forward(mediaPlayer: MediaPlayer?) {
                super.forward(mediaPlayer)
                forEachListener { it.onSeekToNextMediaItem() }
            }

            override fun backward(mediaPlayer: MediaPlayer?) {
                super.backward(mediaPlayer)
                forEachListener { it.onSeekToPreviousMediaItem() }
            }

            override fun playing(mediaPlayer: MediaPlayer?) {
                super.playing(mediaPlayer)
                forEachListener { it.onPlaybackStarted() }
            }

            override fun paused(mediaPlayer: MediaPlayer?) {
                super.paused(mediaPlayer)
                forEachListener { it.onPlaybackPaused() }
            }

            override fun volumeChanged(mediaPlayer: MediaPlayer?, volume: Float) {
                super.volumeChanged(mediaPlayer, volume)
                forEachListener { it.onVolumeChanged(volume) }
            }

            override fun muted(mediaPlayer: MediaPlayer?, muted: Boolean) {
                super.muted(mediaPlayer, muted)
                forEachListener { it.onMuteStateChanged(muted) }
            }
        })
    }

    internal actual fun deregisterNativeListenerForAstro() {
        nativeMediaPlayer.mediaPlayer().events().removeMediaPlayerEventListener(astroNativeListener)
    }

    actual companion object
}