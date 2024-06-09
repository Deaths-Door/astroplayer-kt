@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.deathsdoor.astroplayer.core

import android.content.ComponentName
import android.content.Context
import android.media.audiofx.Equalizer
import androidx.annotation.FloatRange
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.MEDIA_ITEM_TRANSITION_REASON_REPEAT
import androidx.media3.common.Player.STATE_BUFFERING
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaController
import androidx.media3.session.MediaSessionService
import androidx.media3.session.SessionToken
import com.deathsdoor.astroplayer.core.equalizer.EqualizerPresets
import com.deathsdoor.astroplayer.core.equalizer.EqualizerValues
import com.deathsdoor.astroplayer.core.listeners.AstroListener
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.reflect.KClass

actual class NativeMediaPLayer(val player: Player)

@Suppress("UNUSED")
actual open class AstroPlayer actual constructor(private val nativeMediaPlayer: NativeMediaPLayer) : List<AstroMediaItem> {
    /**
     * Prepares the media player for playback.
     *
     * This method delegates the call to the underlying `nativeMediaPlayer.player.prepare()`. Refer to the
     * documentation of that method for specific details on its behavior and requirements.
     */
    actual open fun prepare() = nativeMediaPlayer.player.prepare()

    /**
     * Releases the resources associated with the media player.
     *
     * This method delegates the call to the underlying `nativeMediaPlayer.player.release()`. Refer to the
     * documentation of that method for details on resource management and proper cleanup.
     */
    actual open fun release() = nativeMediaPlayer.player.release()

    /**
     * Starts playback of the media.
     *
     * This method delegates the call to the underlying `nativeMediaPlayer.player.play()`. Refer to the
     * documentation of that method for details on playback behavior and potential errors.
     */
    actual open fun play() = nativeMediaPlayer.player.play()

    /**
     * Pauses playback of the media.
     *
     * This method delegates the call to the underlying `nativeMediaPlayer.player.pause()`. Refer to the
     * documentation of that method for details on pausing behavior and resuming playback.
     */
    actual open fun pause() = nativeMediaPlayer.player.pause()

    /**
     * Indicates whether the media is currently playing.
     *
     * This property delegates the check to the underlying `nativeMediaPlayer.player.isPlaying`. Refer to the
     * documentation of that property for details on how it determines the playback state.
     */
    actual open val isPlaying: Boolean get() = nativeMediaPlayer.player.isPlaying

    /**
     * Indicates whether the media is currently paused.
     *
     * This property is a combination of checks on the underlying `NativeMediaPlayer` state. It considers
     * both `playWhenReady` and `isPlaying` flags to determine if playback is truly paused.
     */
    actual open val isPaused: Boolean get() = !nativeMediaPlayer.player.playWhenReady && !nativeMediaPlayer.player.isPlaying

    /**
     * Sets the playback speed of the media.
     *
     * This property delegates the setting to the underlying `nativeMediaPlayer.player.setPlaybackSpeed(value)`.
     * Refer to the documentation of that method for details on supported speed values and behavior.
     */
    actual open var playBackSpeed: Float = 1f
        set(value){
            field = value
            nativeMediaPlayer.player.setPlaybackSpeed(field)
        }

    /**
     * Gets or sets the repeat mode for playback.
     *
     * This property delegates both getting and setting to the underlying `NativeMediaPlayer`. It uses
     * conversion methods to translate between `AstroPlayer`'s `RepeatMode` enum and ExoPlayer's
     * `Player.RepeatMode` enum. Refer to the documentation of both enums for details on available
     * repeat behavior options.
     */
    actual open var repeatMode: RepeatMode
        get() = nativeMediaPlayer.player.repeatMode.toRepeatMode()
        set(value) {
            nativeMediaPlayer.player.repeatMode = value.toPlayerRepeatMode()
        }

    /**
     * Gets or sets whether shuffle mode is enabled for playback.
     *
     * This property delegates both getting and setting to the underlying `NativeMediaPlayer`. It directly
     * accesses the `shuffleModeEnabled` property of the `NativeMediaPlayer`. Refer to the documentation
     * of that property for details on shuffle mode behavior.
     */
    actual open var shuffleModeEnabled : Boolean
        get() = nativeMediaPlayer.player.shuffleModeEnabled
        set(value) {
            nativeMediaPlayer.player.shuffleModeEnabled = value
        }

    /**
     * Gets the current volume level of the media player.
     *
     * This property delegates the retrieval to the underlying `nativeMediaPlayer.player.volume`. Refer to the
     * documentation of that property for details on how the volume level is represented.
     */
    actual open val volume: Float get() = nativeMediaPlayer.player.volume

    /**
     * Increases the device volume for media playback. (Deprecated)
     *
     * This method delegates the call to the underlying `nativeMediaPlayer.player.increaseDeviceVolume()`. This method
     * is deprecated as it directly controls device volume, which might not be ideal for all use cases.
     * Consider using alternative approaches for volume control within your application.
     */
    @Suppress("DEPRECATION")
    actual open fun increaseVolume() = nativeMediaPlayer.player.increaseDeviceVolume()

    /**
     * Decreases the device volume for media playback. (Deprecated)
     *
     * This method delegates the call to the underlying `nativeMediaPlayer.player.decreaseDeviceVolume()`. This method
     * is deprecated as it directly controls device volume, which might not be ideal for all use cases.
     * Consider using alternative approaches for volume control within your application.
     */
    @Suppress("DEPRECATION")
    actual open fun decreaseVolume() = nativeMediaPlayer.player.decreaseDeviceVolume()

    /**
     * Increases the media player's volume by a specified offset.
     *
     * This method adjusts the internal volume level of the `NativeMediaPlayer` by adding the provided `offset`
     * (between 0.0 and 1.0). Refer to the documentation of `nativeMediaPlayer.player.volume` for details on how the
     * volume level is represented.
     *
     * @param offset The amount by which to increase the volume (between 0.0 and 1.0).
     */
    actual open fun increaseVolumeBy(@FloatRange(from = 0.0, to = 1.0) offset: Float) {
        nativeMediaPlayer.player.volume += offset
    }

    /**
     * Decreases the media player's volume by a specified offset.
     *
     * This method adjusts the internal volume level of the `NativeMediaPlayer` by subtracting the provided `offset`
     * (between 0.0 and 1.0). Refer to the documentation of `nativeMediaPlayer.player.volume` for details on how the
     * volume level is represented.
     *
     * @param offset The amount by which to decrease the volume (between 0.0 and 1.0).
     */
    actual open fun decreaseVolumeBy(@FloatRange(from = 0.0, to = 1.0) offset: Float) {
        nativeMediaPlayer.player.volume -= offset
    }

    /**
     * Indicates whether the device's audio output is muted.
     *
     * This property delegates the check to the underlying `nativeMediaPlayer.player.isDeviceMuted`. It reflects the
     * overall device mute state, not just the media player's specific volume.
     */
    actual open val isMuted: Boolean get() = nativeMediaPlayer.player.isDeviceMuted

    /**
     * Mutes the device's audio output for media playback. (Deprecated)
     *
     * This method delegates the call to the underlying `nativeMediaPlayer.player.setDeviceMuted(true)`. This method
     * is deprecated as it directly controls device audio output, which might not be ideal for all use cases.
     * Consider using alternative approaches for audio muting within your application.
     */
    @Suppress("DEPRECATION")
    actual open fun mute() = nativeMediaPlayer.player.setDeviceMuted(true)

    /**
     * Unmutes the device's audio output for media playback. (Deprecated)
     *
     * This method delegates the call to the underlying `nativeMediaPlayer.player.setDeviceMuted(false)`. This method
     * is deprecated as it directly controls device audio output, which might not be ideal for all use cases.
     * Consider using alternative approaches for audio muting within your application.
     */
    @Suppress("DEPRECATION")
    actual open fun unMute() = nativeMediaPlayer.player.setDeviceMuted(false)

    /**
     * Gets the default seek backward increment value in milliseconds.
     *
     * This property delegates the retrieval to the underlying `nativeMediaPlayer.player.seekBackIncrement`. This value
     * determines the amount by which the playback position is moved backward when using `seekBackwardBy` or
     * similar methods.
     */
    actual open val seekBackIncrement: Long get() = nativeMediaPlayer.player.seekBackIncrement

    /**
     * Gets the default seek forward increment value in milliseconds.
     *
     * This property delegates the retrieval to the underlying `nativeMediaPlayer.player.seekForwardIncrement`. This value
     * determines the amount by which the playback position is moved forward when using `seekForwardBy` or
     * similar methods.
     */
    actual open val seekForwardIncrement: Long get() = nativeMediaPlayer.player.seekForwardIncrement

    /**
     * Seeks the playback position to a specific time in milliseconds.
     *
     * This method delegates the call to the underlying `nativeMediaPlayer.player.seekTo(milliseconds)`. Refer to the
     * documentation of that method for details on supported seek behavior and potential limitations.
     *
     * @param milliseconds The target position within the media item in milliseconds.
     */
    actual open fun seekTo(milliseconds: Long) = nativeMediaPlayer.player.seekTo(milliseconds)

    /**
     * Seeks the playback position to the beginning of the media item at a specific index in the playlist.
     *
     * This method delegates the call to `nativeMediaPlayer.player.seekTo(index, 0)`. It seeks to the specified
     * `index` in the playlist and then sets the position within that media item to 0 milliseconds.
     */
    actual open fun seekToMediaItem(index : Int) = nativeMediaPlayer.player.seekTo(index,0)

    /**
     * Gets the current playback position within the media item in milliseconds.
     *
     * This property delegates the retrieval to the underlying `nativeMediaPlayer.player.currentPosition`. It reflects the
     * current point in time where playback is happening.
     */
    actual open val currentPosition: Long get() = nativeMediaPlayer.player.currentPosition

    /**
     * Gets the total duration of the currently playing media item in milliseconds.
     *
     * This property delegates the retrieval to the underlying `nativeMediaPlayer.player.contentDuration`. It reflects
     * the overall length of the media content.
     */
    actual open val contentDuration: Long get() = nativeMediaPlayer.player.contentDuration

    /**
     * (Internal Visible) A custom deserialization function used to convert data from the underlying [androidx.media3.common.MediaMetadata]
     * to appropriate types for [AstroMediaMetadata] objects.
     *
     * This function takes a key-value pair (`key: String`, `value: String`) as input,
     * where the `value` is initially treated as a String. However, `mapExtras` is responsible for potentially
     * deserializing the `value` into a more specific type suitable for storage in the extra fields of [AstroMediaMetadata.extras].
     *
     * The exact logic of deserialization and the supported target types will depend on the implementation of
     * `mapExtras`. It's likely that `mapExtras` uses the key to determine the appropriate deserialization strategy
     * for the corresponding value.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    var mapExtras: (key: String, value: String) -> Any = { _, value -> value }

    /**
     * Gets the currently playing media item as an `AstroMediaItem` object, or null if no item is playing.
     */
    actual open val currentMediaItem: AstroMediaItem? get() = currentNativeMediaItem?.asAstroMediaItem(mapExtras)

    /**
     *  Gets the currently playing native media item ([MediaItem]) in the underlying [NativeMediaPLayer] representation.
     */
    @Suppress("MemberVisibilityCanBePrivate")
    val currentNativeMediaItem: MediaItem? get() = nativeMediaPlayer.player.currentMediaItem

    /**
     * Gets the index of the currently playing media item within the playlist.
     *
     * This property delegates the retrieval to the underlying `nativeMediaPlayer.player.currentMediaItemIndex`. It reflects
     * the zero-based index of the media item that is currently playing in the playlist.
     */
    actual open val currentMediaItemIndex: Int get() = nativeMediaPlayer.player.currentMediaItemIndex
    /**
     * Gets the total number of media items in the playlist.
     *
     * This property delegates the retrieval to the underlying `nativeMediaPlayer.player.mediaItemCount`. It reflects the
     * total number of media items currently loaded into the playlist.
     */
    actual open val mediaItemCount: Int get() = nativeMediaPlayer.player.mediaItemCount

    /**
     * Clears all media items from the playlist.
     *
     * This method delegates the call to the underlying `nativeMediaPlayer.player.clearMediaItems()`. It removes all
     * media items that were previously added to the playlist.
     */
    actual open fun clearMediaItems() = nativeMediaPlayer.player.clearMediaItems()

    /**
     * Adds a single media item to the playlist.
     *
     * This method takes an `AstroMediaItem` object as input. It internally converts the `AstroMediaItem`
     * to a format compatible with the underlying `NativeMediaPlayer` using the `asNativeMediaItem()` function
     * of the `AstroMediaItem` class. The converted item is then added to the playlist.
     *
     * @param item The `AstroMediaItem` object representing the media content to be added.
     */
    actual open fun addMediaItem(item: AstroMediaItem) = nativeMediaPlayer.player.addMediaItem(item.asNativeMediaItem())

    /**
     * Adds a single media item to the playlist at a specific index.
     *
     * This method takes an `AstroMediaItem` object and an `index` as input. It internally converts the
     * `AstroMediaItem` to a format compatible with the underlying `NativeMediaPlayer` using the `asNativeMediaItem()`
     * function of the `AstroMediaItem` class. The converted item is then inserted into the playlist at the
     * specified `index`.
     *
     * @param index The zero-based index at which to insert the media item.
     * @param item The `AstroMediaItem` object representing the media content to be added.
     */
    actual open fun addMediaItem(
        index: Int,
        item: AstroMediaItem,
    ) = nativeMediaPlayer.player.addMediaItem(index,item.asNativeMediaItem())

    /**
     * Adds a list of media items to the playlist.
     *
     * This method takes a list of `AstroMediaItem` objects as input. It iterates through the list, internally
     * converts each `AstroMediaItem` to a format compatible with the underlying `NativeMediaPlayer` using the
     * `asNativeMediaItem()` function, and then adds the converted items to the playlist in the order they appear
     * in the list.
     *
     * @param items A list of `AstroMediaItem` objects representing the media content to be added.
     */
    actual open fun addMediaItems(items: List<AstroMediaItem>) = nativeMediaPlayer.player.addMediaItems(items.map { it.asNativeMediaItem() })

    /**
     * Adds a list of media items to the playlist at a specific index.
     *
     * This method takes a list of `AstroMediaItem` objects and an `index` as input. It iterates through the list,
     * internally converts each `AstroMediaItem` to a format compatible with the underlying `NativeMediaPlayer` using
     * the `asNativeMediaItem()` function, and then inserts the converted items into the playlist starting at the
     * specified `index`.
     *
     * @param index The zero-based index at which to insert the first item in the list.
     * @param items A list of `AstroMediaItem` objects representing the media content to be added.
     */
    actual open fun addMediaItems(
        index: Int,
        items: List<AstroMediaItem>,
    ) = nativeMediaPlayer.player.addMediaItems(index,items.map { it.asNativeMediaItem() })

    /**
     * Moves a single media item within the playlist to a new position.
     *
     * This method takes two indices (`currentIndex` and `newIndex`) as input. It reorders the playlist by
     * removing the media item at `currentIndex` and then inserting it at the `newIndex`.
     *
     * @param currentIndex The zero-based index of the media item to be moved.
     * @param newIndex The zero-based index at which to insert the moved media item.
     */
    actual open fun moveMediaItem(currentIndex: Int, newIndex: Int) = nativeMediaPlayer.player.moveMediaItem(currentIndex, newIndex)
    /**
     * Moves a range of media items within the playlist to a new position.
     *
     * This method takes three indices (`fromIndex`, `toIndex`, and `newIndex`) as input. It reorders the playlist
     * by removing a range of media items starting at `fromIndex` and ending at `toIndex` (inclusive). These items
     * are then inserted at the `newIndex`.
     *
     * @param fromIndex The zero-based index of the first item in the range to be moved.
     * @param toIndex The zero-based index of the last item in the range to be moved (inclusive).
     * @param newIndex The zero-based index at which to insert the moved range of media items.
     */
    actual open fun moveMediaItems(
        fromIndex: Int,
        toIndex: Int,
        newIndex: Int,
    )  = nativeMediaPlayer.player.moveMediaItems(fromIndex,toIndex,newIndex)

    /**
     * Removes a single media item from the playlist at a specific index.
     *
     * This method takes an `index` as input. It removes the media item at the specified zero-based index from
     * the playlist.
     *
     * @param index The zero-based index of the media item to be removed.
     */
    actual open fun removeMediaItem(index: Int)  = nativeMediaPlayer.player.removeMediaItem(index)

    /**
     * Removes a range of media items from the playlist.
     *
     * This method takes two indices (`fromIndex` and `toIndex`) as input. It removes a range of media items
     * starting at `fromIndex` and ending at `toIndex` (inclusive) from the playlist.
     *
     * @param fromIndex The zero-based index of the first item in the range to be removed.
     * @param toIndex The zero-based index of the last item in the range to be removed (inclusive).
     */
    actual open fun removeMediaItems(fromIndex: Int, toIndex: Int)  = nativeMediaPlayer.player.removeMediaItems(fromIndex, toIndex)

    /** Replaces a single media item in the playlist at a specific index with a new item.
     *
     * This method takes an `index` and an `AstroMediaItem` object as input. It replaces the media item at the
     * specified `index` with the provided `AstroMediaItem`. Internally, the `asNativeMediaItem()` function of the
     * `AstroMediaItem` class is used to convert the new item to a format compatible with the underlying
     * `NativeMediaPlayer`.
     *
     * @param index The zero-based index of the media item to be replaced.
     * @param mediaItem The `AstroMediaItem` object representing the new media content.
     */
    actual open fun replaceMediaItem(
        index: Int,
        mediaItem: AstroMediaItem,
    )  = nativeMediaPlayer.player.replaceMediaItem(index, mediaItem.asNativeMediaItem())

    /**
     * Replaces a range of media items in the playlist with a new list of items.
     *
     * This method takes three arguments: `fromIndex`, `toIndex`, and `mediaItems`. It replaces a range of media
     * items starting at `fromIndex` and ending at `toIndex` (inclusive) with the provided list of `AstroMediaItem`
     * objects. Internally, each item in the `mediaItems` list is converted to a format compatible with the
     * underlying `NativeMediaPlayer` using the `asNativeMediaItem()` function of the `AstroMediaItem` class.
     *
     * @param fromIndex The zero-based index of the first item in the range to be replaced.
     * @param toIndex The zero-based index of the last item in the range to be replaced (inclusive).
     * @param mediaItems A list of `AstroMediaItem` objects representing the new media content.
     */
    actual open fun replaceMediaItems(
        fromIndex: Int,
        toIndex: Int,
        mediaItems: List<AstroMediaItem>,
    ) = nativeMediaPlayer.player.replaceMediaItems(fromIndex,toIndex,mediaItems.map { it.asNativeMediaItem() })

    private inline val Player.mediaItemsRange get() = 0 until mediaItemCount

    /**
     * Gets a list of all media items currently in the playlist.
     *
     * This method leverages the `mapMediaItems` function with an identity transformation (`{ it }`) to
     * retrieve a list containing all `AstroMediaItem` objects in the playlist. It essentially calls
     * `mapMediaItems` and directly returns the resulting list without any further modifications.
     *
     * @return A list of `AstroMediaItem` objects representing the media items in the playlist.
     */
    actual open fun allMediaItems(): List<AstroMediaItem> = mapMediaItems { it }
    /**
     * Applies a transformation function to each media item in the playlist and returns a list of the transformed results.
     *
     * This method takes a lambda function `transform` as input. The `transform` function takes an `AstroMediaItem`
     * object as input and returns a value of type `T`. The method iterates through all media items in the
     * playlist using `nativeMediaPlayer.player.mediaItemsRange`. For each index in the range:
    - It retrieves the media item at that index using `nativeMediaPlayer.player.getMediaItemAt(index)`.
    - It converts the retrieved media item to an `AstroMediaItem` object using `asAstroMediaItem(mapExtras)`.
    - If the conversion is successful (i.e., the item is not null), the `transform` function is applied
    to the converted `AstroMediaItem` and the transformed value is added to the result list.
    - If the conversion fails (i.e., the item is null), it's skipped.
     *
     * The final result is a list containing the transformed values for each successfully converted media item in
     * the playlist. The type of the elements in the resulting list depends on the return type of the provided
     * `transform` function (`T`).
     *
     * @param transform A lambda function that takes an `AstroMediaItem` object as input and returns a value of type `T`.
     * @return A list containing the transformed results for each media item in the playlist, or an empty list if no items exist.
     */
    actual open fun <T> mapMediaItems(transform: (AstroMediaItem) -> T) : List<T> = nativeMediaPlayer.player.mediaItemsRange.mapNotNull { index ->
        mapNativeMediaItemIndex(index)?.let { item -> transform(item) }
    }

    /**
    * Applies a transformation function to each media item in the playlist using the underlying native format and returns a list of the transformed results.
    *
    * This method offers an alternative approach to `mapMediaItems` for scenarios where you might not need
    * or want to convert media items to `AstroMediaItem` objects. It takes a lambda function `transform` as input.
    * The `transform` function takes a `MediaItem` object (the underlying native representation) as input and
    * returns a value of type `T`
    *
    * The final result is a list containing the transformed values for each media item in the playlist based on
    * the underlying native format. The type of the elements in the resulting list depends on the return type of the
    * provided `transform` function (`T`).
    *
    * @param transform A lambda function that takes a `MediaItem` object (native representation) as input and returns a value of type `T`.
    * @return A list containing the transformed results for each media item in the playlist using the native format, or an empty list if no items exist.
    */
    fun<T> mapNativeMediaItems(transform: (MediaItem) -> T) = nativeMediaPlayer.player.mediaItemsRange.map {
        transform(nativeMediaPlayer.player.getMediaItemAt(it))
    }

    private val _equalizerLock = Any()
    private var _equalizer : Equalizer? = null
    private var _equalizerListener : Player.Listener? = null

    /**Refer to documentation in for **commonMain** source set **/
    actual var isEqualizerEnabled: Boolean = false
        get() = _equalizer != null
        set(value) {
            field = value

            synchronized(_equalizerLock) {
                when {
                    field -> _equalizerListener = object : Player.Listener {
                        @UnstableApi
                        override fun onAudioSessionIdChanged(audioSessionId: Int) {
                            super.onAudioSessionIdChanged(audioSessionId)
                            synchronized(_equalizerLock) {
                                _equalizer?.release()
                                _equalizer = Equalizer(0, audioSessionId)
                            }
                        }
                    }
                    else -> {
                        isSmartEqualizerEnabled = false

                        nativeMediaPlayer.player.removeListener(_equalizerListener!!)
                        _equalizerListener = null

                        _equalizer!!.release()
                        _equalizer = null
                    }
                }
            }

            forEachListener { it.onEqualizerEnabledChanged(field) }

        }

    /**Refer to documentation in for **commonMain** source set **/
    actual var currentEqualizerValues: EqualizerValues? = null
        set(value) {
            field = value

            if(isEqualizerEnabled && field != null) {
                synchronized(_equalizerLock) {
                    field?.forEachIndexed { index, (_,value) ->
                        val bandLevel = (value * 1000).toInt().toShort()
                        _equalizer!!.setBandLevel(index.toShort(), bandLevel)
                    }
                }
            }

            forEachListener { it.onCurrentEqualizerValuesChanged(field) }
        }

    private var _equalizerSmartListener : Player.Listener? = null
    /**Refer to documentation in for **commonMain** source set **/
    actual var smartEqualizerPicker : ((id: String) -> EqualizerValues)? = null
    /**Refer to documentation in for **commonMain** source set **/
    actual var isSmartEqualizerEnabled: Boolean = isEqualizerEnabled
        set(value) {
            field = value

            when(field) {
                true -> {
                    isEqualizerEnabled = true

                    synchronized(_equalizerLock) {
                        _equalizerSmartListener = object : Player.Listener {
                            override fun onMediaItemTransition(mediaItem: MediaItem?,@Player.MediaItemTransitionReason reason: Int) {
                                super.onMediaItemTransition(mediaItem, reason)

                                // Only repeat is the case where we don't have to update the equalizerValues
                                if(reason != MEDIA_ITEM_TRANSITION_REASON_REPEAT) return

                                val identifier = mediaItem?.mediaMetadata?.extras?.getString(EQUALIZER_KEY)

                                currentEqualizerValues = when(identifier) {
                                    null -> EqualizerPresets.Default
                                    else -> EqualizerPresets.AllPresets[identifier] ?: smartEqualizerPicker?.invoke(identifier)
                                }
                            }
                        }

                        nativeMediaPlayer.player.addListener(_equalizerListener!!)
                    }
                }

                false -> synchronized(_equalizerLock) {
                    nativeMediaPlayer.player.removeListener(_equalizerSmartListener!!)
                    _equalizerSmartListener = null
                }
            }

            forEachListener { it.onSmartEqualizerEnabledChanged(field) }
        }

    private var astroNativeListener : Player.Listener? = null
    internal actual val listenersStore: MutableMap<Int, AstroListener> = mutableMapOf()

    internal actual fun registerNativeListenerForAstro() {
        astroNativeListener = object : Player.Listener {
            override fun onEvents(player: Player, events: Player.Events) {
                super.onEvents(player, events)

                val closures = mutableSetOf<(AstroListener) -> Unit>()

                if(events.contains(Player.EVENT_PLAYER_ERROR)) {
                    closures.add {
                        it.onPlaybackError(this@AstroPlayer.nativeMediaPlayer.player.playerError!!)
                    }
                }

                if(events.contains(Player.EVENT_MEDIA_ITEM_TRANSITION)) {
                    closures.add { it.onCurrentMediaItemChanged(currentMediaItem) }
                }

                if(events.contains(Player.EVENT_IS_PLAYING_CHANGED)) {
                    if(isPaused) closures.add { it.onPlaybackStarted() }
                    else closures.add { it.onPlaybackPaused() }
                }

                if(events.containsAny(Player.EVENT_DEVICE_VOLUME_CHANGED,Player.EVENT_VOLUME_CHANGED)) {
                    closures.add {
                        it.onVolumeChanged(volume)
                        it.onMuteStateChanged(isMuted)
                    }
                }

                if(events.contains(Player.EVENT_PLAYBACK_STATE_CHANGED) && nativeMediaPlayer.player.playbackState == STATE_BUFFERING) {
                    closures.add { it.onMediaItemBuffering() }
                }

                if(events.contains(Player.EVENT_SHUFFLE_MODE_ENABLED_CHANGED)) {
                    closures.add { it.onShuffleModeChanged(shuffleModeEnabled = shuffleModeEnabled) }
                }

                if(events.contains(Player.EVENT_REPEAT_MODE_CHANGED)) {
                    closures.add { it.onRepeatModeChanged(repeatMode = repeatMode) }
                }

                if(events.contains(Player.EVENT_PLAYBACK_PARAMETERS_CHANGED)) {
                    closures.add { it.onPlaybackSpeedChanged(playBackSpeed) }
                }

                forEachListener { astro ->
                    for(item in closures) {
                        item(astro)
                    }
                }
            }

            // Can not be down in onEvents due to lack of information
            override fun onPositionDiscontinuity(
                oldPosition: Player.PositionInfo,
                newPosition: Player.PositionInfo,
                @Player.DiscontinuityReason reason: Int,
            ) {
                super.onPositionDiscontinuity(oldPosition, newPosition, reason)

                val closures = mutableSetOf<(AstroListener) -> Unit>()

                when(reason) {
                    Player.DISCONTINUITY_REASON_SEEK_ADJUSTMENT ->  when(oldPosition.contentPositionMs < newPosition.contentPositionMs) {
                        true -> closures.add { it.onSeekForward() }
                        false -> closures.add { it.onSeekBackward() }
                    }

                    Player.DISCONTINUITY_REASON_SKIP -> when(oldPosition.mediaItemIndex < newPosition.mediaItemIndex) {
                        true -> closures.add { it.onSeekToNextMediaItem() }
                        false -> closures.add { it.onSeekToPreviousMediaItem() }
                    }

                    else -> {}
                }

                forEachListener { astro ->
                    for(item in closures) {
                        item(astro)
                    }
                }
            }
        }

        nativeMediaPlayer.player.addListener(astroNativeListener!!)
    }

    internal actual fun deregisterNativeListenerForAstro() {
        nativeMediaPlayer.player.removeListener(astroNativeListener!!)
        astroNativeListener = null
    }

    actual companion object {
        /**
         * Creates a `MediaController` instance for an Android `MediaSessionService`.
         *
         * The function creates a `SessionToken` object based on the provided context and `sessionService` class.
         * It then uses the `MediaController.Builder(context, sessionToken)` constructor to create a builder
         * for the `MediaController`. If the `customize` lambda is provided, it's used to customize the builder
         * before building the final `MediaController` object. Finally, the function builds the `MediaController`
         * asynchronously using `buildAsync()` and adds a listener to prepare the controller after it's built.
         *
         * @param context The Android application [Context].
         * @param sessionService The class representing the [MediaSessionService].
         * @param customize An optional lambda function to customize the [MediaController.Builder] (default: empty lambda).
         * @return A [ListenableFuture] object representing the asynchronous creation of the [MediaController].
         */
        fun createFrom(context: Context,sessionService : KClass<MediaSessionService>,customize: MediaController.Builder.() -> MediaController.Builder = { this }): ListenableFuture<MediaController> {
            val sessionToken = SessionToken(context, ComponentName(context, sessionService::class.java))
            val controllerFuture = MediaController.Builder(context, sessionToken).customize().buildAsync()
            controllerFuture.addListener({ controllerFuture.get().prepare() }, MoreExecutors.directExecutor())
            return controllerFuture
        }
    }
    
    private fun mapNativeMediaItemIndex(index : Int) = nativeMediaPlayer.player.getMediaItemAt(index).asAstroMediaItem(mapExtras)

    // -- List + Mutable List
    override val size: Int get() = mediaItemCount
    override fun isEmpty(): Boolean = mediaItemCount == 0

    override fun contains(element: AstroMediaItem): Boolean = nativeMediaPlayer.player.mediaItemsRange.any {
        mapNativeMediaItemIndex(it) == element
    }

    override fun containsAll(elements: Collection<AstroMediaItem>): Boolean = allMediaItems().containsAll(elements)
    override fun get(index: Int): AstroMediaItem = mapNativeMediaItemIndex(index) ?: error("this should be unreachable mostly")
    override fun indexOf(element: AstroMediaItem): Int =  nativeMediaPlayer.player.mediaItemsRange.indexOfFirst { index ->
        mapNativeMediaItemIndex(index) == element
    }

    override fun lastIndexOf(element: AstroMediaItem): Int = nativeMediaPlayer.player.mediaItemsRange.indexOfLast { index ->
        mapNativeMediaItemIndex(index) == element
    }

    override fun iterator(): Iterator<AstroMediaItem> = allMediaItems().iterator()
    override fun listIterator(): ListIterator<AstroMediaItem> = allMediaItems().listIterator()
    override fun listIterator(index: Int): ListIterator<AstroMediaItem> = allMediaItems().listIterator(index)
    override fun subList(fromIndex: Int, toIndex: Int): List<AstroMediaItem> = allMediaItems().subList(fromIndex, toIndex)
}