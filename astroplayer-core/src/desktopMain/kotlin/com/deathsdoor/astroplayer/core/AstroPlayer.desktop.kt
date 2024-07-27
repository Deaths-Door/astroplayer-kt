@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.deathsdoor.astroplayer.core

import co.touchlab.stately.collections.ConcurrentMutableList
import com.deathsdoor.astroplayer.core.equalizer.EqualizerPresets
import com.deathsdoor.astroplayer.core.equalizer.EqualizerValues
import com.deathsdoor.astroplayer.core.listeners.AstroListener
import com.eygraber.uri.Uri
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery
import uk.co.caprica.vlcj.media.MediaRef
import uk.co.caprica.vlcj.media.Meta
import uk.co.caprica.vlcj.medialist.MediaApi
import uk.co.caprica.vlcj.player.base.Equalizer
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent
import java.util.Collections
import kotlin.random.Random

actual typealias NativeMediaPLayer = AudioPlayerComponent

@Suppress("UNUSED")
actual open class AstroPlayer actual constructor(private val nativeMediaPlayer: NativeMediaPLayer) {
    private val nativeMediaPlayerDelegate get() = nativeMediaPlayer.mediaPlayer()

    actual open fun prepare() {
        // To load native library
        NativeDiscovery().discover()
    }

    actual open fun release() {
        nativeMediaPlayerDelegate.events().removeMediaPlayerEventListener(onMediaChangedListener)
        deregisterNativeListenerForAstro()
        nativeMediaPlayer.release()
    }

    actual open fun play() {
        seekToMediaItem(currentMediaItemIndex)
        nativeMediaPlayerDelegate.controls().play()
    }
    
    actual open fun pause() = nativeMediaPlayerDelegate.controls().pause()

    actual open val isPlaying: Boolean get() = nativeMediaPlayerDelegate.status().isPlaying
    actual open val isPaused: Boolean get() = !nativeMediaPlayerDelegate.status().isPlaying
    actual open var playBackSpeed: Float
        get() = nativeMediaPlayerDelegate.status().rate()
        set(value) {
            nativeMediaPlayerDelegate.controls().setRate(value)
            observeWennNativeListenerRegistered { it.onPlaybackSpeedChanged(value) }
        }

    private var nativeVolume
        get() = nativeMediaPlayerDelegate.audio().volume()
        set(value) {
            nativeMediaPlayerDelegate.audio().setVolume(value)
        }

    private fun Float.asAdjustedVolume(): Int = (this * VOLUME_UPPER_LIMIT).toInt()

    actual open val volume: Float get() = nativeVolume.toFloat() / VOLUME_UPPER_LIMIT
    actual open fun increaseVolume() {
        nativeVolume += DEFAULT_VOLUME_CHANGE_OFFSET
    }
    actual open fun decreaseVolume() {
        nativeVolume = DEFAULT_VOLUME_CHANGE_OFFSET
    }
    actual open fun increaseVolumeBy(offset : Float) {
        nativeVolume += offset.asAdjustedVolume()
    }
    actual open fun decreaseVolumeBy(offset : Float) {
        nativeVolume = offset.asAdjustedVolume()
    }

    actual open val isMuted : Boolean get() = nativeMediaPlayerDelegate.audio().isMute

    actual open fun mute() {
        nativeMediaPlayerDelegate.audio().isMute = true
    }

    actual open fun unMute() {
        nativeMediaPlayerDelegate.audio().isMute = false
    }

    actual open val seekBackIncrement : Long = 5000
    actual open val seekForwardIncrement : Long = 5000

    actual open fun seekTo(milliseconds: Long) {
        val old = currentPosition
        nativeMediaPlayerDelegate.controls().setTime(milliseconds)

        if(old < currentPosition) observeWennNativeListenerRegistered { it.onSeekForward() }
        else observeWennNativeListenerRegistered { it.onSeekBackward() }
    }

    actual open val currentPosition : Long get() = nativeMediaPlayerDelegate.status().time()
    actual open val contentDuration : Long get() = nativeMediaPlayerDelegate.status().length()

    private val astroMediaItems = ConcurrentMutableList<AstroMediaItem>()
    private var _cachedCurrentMediaItemIndex : Int = -1
        get() {
            if(field == -1 && astroMediaItems.isNotEmpty()) {
                _cachedCurrentMediaItemIndex = 0
            }

            return field
        }
        set(value) {
            field = value
            observeWennNativeListenerRegistered { it.onCurrentMediaItemChanged(currentMediaItem) }
        }

    actual open val currentMediaItemIndex: Int get() = _cachedCurrentMediaItemIndex
    actual open val currentMediaItem: AstroMediaItem? get() = astroMediaItems.getOrNull(currentMediaItemIndex)
    actual open val mediaItemCount: Int get() = astroMediaItems.size

    private fun prepareMediaForPlayback(source : Uri?) {
        if(source != null) {
            observeWennNativeListenerRegistered { it.onCurrentMediaItemChanged(currentMediaItem) }
        }

        nativeMediaPlayerDelegate.media().prepare(source?.toString())
    }

    private val onMediaChangedListener : MediaPlayerEventAdapter = object : MediaPlayerEventAdapter() {
        private fun shuffleSeekToNextMediaItem() {
            println("shuffleSeekToNextMediaItem")
            println("shuffleModeRandom = $shuffleModeRandom")
            println("-".repeat(5))
            // If shuffle mode is enabled (indicated by a non-null `shuffleModeRandom`),
            // select a random media item to play next.
            when(shuffleModeRandom) {
                null -> seekToNextMediaItem()
                else -> {
                    val nextIndex = shuffleModeRandom!!.nextInt(until = mediaItemCount)
                    seekToMediaItem(nextIndex)
                }
            }
        }

        override fun finished(mediaPlayer: MediaPlayer?) {
            super.finished(mediaPlayer)

            println("on mediaitem finished")

            // Treating media player completion as a media change event due to unreliable `mediaChanged`
            when(repeatMode) {
                RepeatMode.One -> seekToStartOfMediaItem()
                RepeatMode.Off -> {
                    println("hasNextMediaItem = $hasNextMediaItem")
                    if(hasNextMediaItem) {
                        shuffleSeekToNextMediaItem()

                        // Updated equalizer values
                        _smartEqualizerUpdater?.invoke()
                    }
                }
                RepeatMode.All -> {
                    when (hasNextMediaItem) {
                        // If there's a next media item, seek to it using shuffle logic.
                        true -> shuffleSeekToNextMediaItem()
                        // If there's no next media item, go back to the first media item.
                        // This is the expected behavior for RepeatMode.All.
                        false -> seekToMediaItem(0)
                    }

                    // Updated equalizer values
                    _smartEqualizerUpdater?.invoke()
                }
            }
        }
    }

    init {
        nativeMediaPlayerDelegate.events().addMediaPlayerEventListener(onMediaChangedListener)
    }

    actual open fun seekToMediaItem(index: Int) {
        _cachedCurrentMediaItemIndex = index

        println("$currentMediaItem")

        val preparedMrl = nativeMediaPlayerDelegate.media()
            .info()
            ?.mrl()
            ?.let { Uri.parse(it) }

        val source = currentMediaItem!!.source

        if(preparedMrl == null || preparedMrl != source)  {
            println("preparing current mediaitem")
            prepareMediaForPlayback(source)
        }

       // nativeMediaPlayerDelegate.controls().play()
    }

    actual open fun clearMediaItems() {
        astroMediaItems.clear()
        prepareMediaForPlayback(null)
    }

    actual open fun addMediaItem(item: AstroMediaItem) {
        astroMediaItems.add(item)
    }

    actual open fun addMediaItems(items: List<AstroMediaItem>) {
        astroMediaItems.addAll(items)
    }

    // TODO: implement this in
    private fun handleCurrentMediaItemOnListModification(modifiedIndex : Int,update : (index : Int) -> Unit = { seekToMediaItem(it) }) {
        // If the index of the media item to be removed is the same as the currently playing media item
        // and there is at least one media item in the queue,
        // seek to the next media item in the queue.
        if (modifiedIndex == currentMediaItemIndex && mediaItemCount > 0) update(modifiedIndex)
    }


    // TODO: implement this in
    private fun handleCurrentMediaItemOnListRangeModification(range: IntRange,update : (range : IntRange) -> Unit = {}/*= { seekToMediaItem(range.first) }*/) {
        // If any items being removed include the current media item and there are more items,
        // automatically transition to the first item after the removed range.
        if (currentMediaItemIndex in range && mediaItemCount > 0) update(range)
    }

    private fun indexOfCurrentMediaItemInListModification(range : IntRange) : Int {
        return currentMediaItemIndex - range.first
    }

    actual open fun addMediaItem(index: Int, item: AstroMediaItem) {
        astroMediaItems.add(index,item)

        handleCurrentMediaItemOnListModification(index) {
            // If the current media item index is where we add the item,
            // increment the cached current media item index as it's now the next item.
            _cachedCurrentMediaItemIndex += 1
        }
    }

    actual open fun addMediaItems(index: Int, items: List<AstroMediaItem>) {
        val range = index..(index+items.size)
        val indexInRange = indexOfCurrentMediaItemInListModification(range)

        astroMediaItems.addAll(index,items)

        handleCurrentMediaItemOnListRangeModification(range)
    }

    private fun removeMediaItemAt(index : Int): AstroMediaItem {
        val mediaItem = astroMediaItems.removeAt(index)
        handleCurrentMediaItemOnListModification(index)
        return mediaItem
    }

    actual open fun removeMediaItem(index: Int) {
        removeMediaItemAt(index)
    }

    actual open fun removeMediaItems(fromIndex: Int, toIndex: Int) {
        val range = fromIndex..toIndex

        // No removeRange function available
        // Refer to this [this](https://youtrack.jetbrains.com/issue/KT-13951/MutableList-is-missing-a-method-to-remove-multiple-items-by-position-or-range)
        astroMediaItems.subList(range.first,range.last).clear()

        handleCurrentMediaItemOnListRangeModification(range)
    }

    actual open fun replaceMediaItem(
        index: Int,
        mediaItem: AstroMediaItem,
    ) {
        removeMediaItem(index)
        addMediaItem(index,mediaItem)
        handleCurrentMediaItemOnListModification(index)
    }

    actual open fun replaceMediaItems(
        fromIndex: Int,
        toIndex: Int,
        mediaItems: List<AstroMediaItem>,
    ) {
        removeMediaItems(fromIndex,toIndex)
        addMediaItems(fromIndex,mediaItems)
        handleCurrentMediaItemOnListRangeModification(fromIndex..toIndex)
    }

    actual open fun moveMediaItem(currentIndex: Int, newIndex: Int) {
        val mediaItem = removeMediaItemAt(currentIndex)
        addMediaItem(newIndex,mediaItem)

        handleCurrentMediaItemOnListModification(currentIndex) {
            seekToMediaItem(newIndex)
        }
    }

    actual open fun moveMediaItems(
        fromIndex: Int,
        toIndex: Int,
        newIndex: Int,
    ) {
        val range = fromIndex..toIndex
        val mediaItems = astroMediaItems.slice(range)
        val indexInRange = indexOfCurrentMediaItemInListModification(range)

        removeMediaItems(fromIndex, toIndex)
        addMediaItems(newIndex,mediaItems)

        handleCurrentMediaItemOnListRangeModification(range) {
            seekToMediaItem(newIndex + indexInRange)
        }
    }

    actual open fun allMediaItems(): List<AstroMediaItem> = astroMediaItems
    actual open fun <T> mapMediaItems(transform: (AstroMediaItem) -> T): List<T> = astroMediaItems.map(transform)

    actual open var repeatMode: RepeatMode = RepeatMode.Off
        set(value) {
            field = value
            observeWennNativeListenerRegistered { it.onRepeatModeChanged(field) }
        }

    // Shuffle mode logic is based on the `ShuffleOrder` class from ExoPlayer (https://cs.android.com/android/platform/superproject/main/+/main:external/exoplayer/tree_15dc86382f17a24a3e881e52e31a810c1ea44b49/library/core/src/main/java/com/google/android/exoplayer2/source/ShuffleOrder.java;bpv=1;bpt=1;l=41?q=ShuffleOrder&ss=android%2Fplatform%2Fsuperproject%2Fmain:external%2Fexoplayer%2Ftree_15dc86382f17a24a3e881e52e31a810c1ea44b49%2Flibrary%2F&gsn=DefaultShuffleOrder&gs=KYTHE%3A%2F%2Fkythe%3A%2F%2Fandroid.googlesource.com%2Fplatform%2Fsuperproject%2Fmain%2F%2Fmain%3Flang%3Djava%3Fpath%3Dcom.google.android.exoplayer2.source.ShuffleOrder.DefaultShuffleOrder%23e08927dfbf23c1f63562740b92713ae489b8d5f924a5836a21a87e7271f8d323).
    // Uses same shuffle 'algorithm' as the one used in the Android ExoPlayer.
    // This implementation utilizes a `java.util.Random` object for shuffle logic consistency across platforms.
    private var shuffleModeRandom : Random? = null
    actual open var shuffleModeEnabled: Boolean = false
        get() = shuffleModeRandom != null
        set(value) {
            field = value

            // Same as java.util.Random() -- https://developer.classpath.org/doc/java/util/Random-source.html
            if(field) shuffleModeRandom = Random(System.currentTimeMillis())

            observeWennNativeListenerRegistered { it.onShuffleModeChanged(field) }
        }

    private val equalizerApi get() = nativeMediaPlayer.mediaPlayerFactory().equalizer()
    private val nativeEqualizer : Equalizer? get() = nativeMediaPlayerDelegate.audio().equalizer()

    actual var isEqualizerEnabled: Boolean = nativeEqualizer != null
        get() = nativeEqualizer != null
        set(value) {
            field = value

            if(!field) nativeMediaPlayerDelegate.audio().setEqualizer(null)

            observeWennNativeListenerRegistered { it.onEqualizerEnabledChanged(field) }
        }

    actual var currentEqualizerValues: EqualizerValues? = null
        set(value) {
            field = value

            if(isEqualizerEnabled && field != null) {
                val equalizer = equalizerApi.newEqualizer(field!!.identifier)

                val amps = field!!.map { band -> (band * 1000).toInt().toFloat() }
                equalizer.setAmps(amps.toFloatArray())

                nativeMediaPlayerDelegate.audio().setEqualizer(equalizer)
            }

            observeWennNativeListenerRegistered { it.onCurrentEqualizerValuesChanged(field) }
        }

    actual var smartEqualizerPicker: ((id: String) -> EqualizerValues)? = null

    private var _smartEqualizerUpdater : (() -> Unit)? = null
    actual var isSmartEqualizerEnabled: Boolean = isEqualizerEnabled
        set(value) {
            field = value

            when(field) {
                true -> {
                    isEqualizerEnabled = true

                    _smartEqualizerUpdater = {
                        val identifier = currentMediaItem?.metadata?.equalizerIdentifier

                        currentEqualizerValues = when(identifier) {
                            null -> EqualizerPresets.Default
                            else -> EqualizerPresets.AllPresets[identifier] ?: smartEqualizerPicker?.invoke(identifier)
                        }
                    }
                }
                false -> _smartEqualizerUpdater = null
            }

            observeWennNativeListenerRegistered { it.onSmartEqualizerEnabledChanged(field) }
        }

    private var astroNativeListener : MediaPlayerEventAdapter? = null

    @Suppress("SpellCheckingInspection")
    private fun observeWennNativeListenerRegistered(action: (AstroListener) -> Unit) {
        astroNativeListener?.let { forEachListener(action) }
    }

    // synchronized
    internal actual val listenersStore: MutableMap<Int, AstroListener> = mutableMapOf()
    internal actual fun registerNativeListenerForAstro() {
        astroNativeListener = object : MediaPlayerEventAdapter(){
            override fun buffering(mediaPlayer: MediaPlayer?, newCache: Float) {
                super.buffering(mediaPlayer, newCache)
                forEachListener { it.onMediaItemBuffering() }
            }

            override fun error(mediaPlayer: MediaPlayer?) {
                super.error(mediaPlayer)
                // TODO: Try to get the actual error from mediaPlayer
                val exception = Exception("Playback error occurred on desktop. Unable to retrieve detailed error information.")

                forEachListener { it.onPlaybackError(exception) }
            }

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
                forEachListener { it.onVolumeChanged(this@AstroPlayer.volume) }
            }

            override fun muted(mediaPlayer: MediaPlayer?, muted: Boolean) {
                super.muted(mediaPlayer, muted)
                forEachListener { it.onMuteStateChanged(muted) }
            }
        }

        nativeMediaPlayer.mediaPlayer().events().addMediaPlayerEventListener(astroNativeListener)
    }

    internal actual fun deregisterNativeListenerForAstro() {
        nativeMediaPlayer.mediaPlayer().events().removeMediaPlayerEventListener(astroNativeListener)
    }

    actual companion object {
        private const val VOLUME_UPPER_LIMIT = 100
        private const val DEFAULT_VOLUME_CHANGE_OFFSET : Int = 10
    }
}