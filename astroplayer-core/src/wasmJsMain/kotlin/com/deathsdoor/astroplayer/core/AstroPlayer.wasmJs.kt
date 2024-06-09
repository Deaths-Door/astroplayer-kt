@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.deathsdoor.astroplayer.core

import com.deathsdoor.astroplayer.core.equalizer.EqualizerValues
import com.deathsdoor.astroplayer.core.listeners.AstroListener


actual typealias NativeMediaPLayer = Howl

/**
 * The main class for controlling media playback.
 *
 * AstroPlayer provides a set of methods for playing, pausing, seeking, and managing media items in a playlist.
 *
 * @constructor Creates a new AstroPlayer instance.
 *
 * @param nativeMediaPlayer An implementation of the underlying native media player that AstroPlayer delegates
 * playback functionality to. The specific type of [NativeMediaPLayer] will vary depending on the platform
 * and the [AstroPlayer] implementation.
 */
@Suppress("UNUSED")
actual open class AstroPlayer actual constructor(private var nativeMediaPlayer: NativeMediaPLayer): List<AstroMediaItem> {
    /** Prepares the player for playback. This might involve buffering the media stream.**/
    actual open fun prepare() = Unit

    /**
     * Releases the player resources. This should be called when the player is no longer needed.
     */
    actual open fun release() = nativeMediaPlayer.release()

    /**
     * Starts playback of the current media item.
     */
    actual open fun play() {
        nativeMediaPlayer.play()
    }

    /**
     * Pauses playback of the current media item.
     */
    actual open fun pause() = nativeMediaPlayer.pause()
    /**
     * Indicates whether the player is currently playing media.
     */
    actual open val isPlaying: Boolean get() = nativeMediaPlayer.isAnyAudioPlaying()

    /**
     * Indicates whether the player is currently paused.
     */
    actual open val isPaused: Boolean get() {
        val state = nativeMediaPlayer.state().toString()
        if(state == "unloaded" || state == "loading") return true
        return !isPlaying
    }

    /**
     * Gets or sets the playback speed. Values greater than 1.0 indicate faster playback,
     * while values less than 1.0 indicate slower playback.
     */
    actual open var playBackSpeed: Float
        get() = nativeMediaPlayer.rate.toDouble().toFloat()
        set(value) {
            nativeMediaPlayer.rate = value.toDouble().toJsNumber()
        }

    /**
     * The current repeat mode of the player.
     */
    actual open var repeatMode: RepeatMode = RepeatMode.Off
        set(value) {
            field = value

            // Set loop true in case this was disabled
            if(repeatMode == RepeatMode.One) nativeMediaPlayer.loop = true
        }

    init {
        fun seekToNextOrRandomMediaItem() = if(shuffleModeEnabled)
                (0 until astroMediaItems.size)
                    .randomOrNull()
                    ?.let { seekToMediaItem(it) }
        else seekToNextMediaItem()

        when(repeatMode) {
            // Enabling looping
            RepeatMode.One -> nativeMediaPlayer.loop = true

            // TODO: Remove these listeners when repeatMode changes
            // Just Seek to Next MediaItem
            RepeatMode.Off -> nativeMediaPlayer.on("end") {
                seekToNextOrRandomMediaItem()
            }

            RepeatMode.All -> nativeMediaPlayer.on("end") {
                // Just go to the next one
                if(hasNextMediaItem) seekToNextOrRandomMediaItem()

                // Go to the first one
                else seekToMediaItem(0)
            }
        }
    }

    /**
     * Indicates whether shuffle mode is enabled.
     */
    actual open var shuffleModeEnabled: Boolean = false

    /**
     * Gets the current volume level of the player. The value ranges from 0.0 (silent) to 1.0 (maximum volume).
     */
    actual open val volume: Float
        get() = nativeMediaPlayer.volume

    /**
     * Increases the player volume.
     */
    actual open fun increaseVolume() {
        // Increase by 10%%
        increaseVolumeBy(nativeMediaPlayer.volume * 1.1f)
    }

    /**
     * Decreases the player volume.
     */
    actual open fun decreaseVolume() {
        // Decrease by 10%%
        decreaseVolumeBy(nativeMediaPlayer.volume * 0.9f)
    }

    /**
     * Increases the player volume by a specified offset.
     *
     * @param offset The amount to increase the volume by.
     */
    actual open fun increaseVolumeBy(offset: Float) {
        nativeMediaPlayer.volume += nativeMediaPlayer.volume + offset
    }

    /**
     * Decreases the player volume by a specified offset.
     *
     * @param offset The amount to decrease the volume by.
     */
    actual open fun decreaseVolumeBy(offset: Float) {
        nativeMediaPlayer.volume += nativeMediaPlayer.volume - offset
    }

    /**
     * Indicates whether the player is muted.
     */
    actual open val isMuted: Boolean get() = nativeMediaPlayer.mute

    /**
     * Mutes the player.
     */
    actual open fun mute() {
        nativeMediaPlayer.mute = true
    }

    /**
     * Unmutes the player.
     */
    actual open fun unMute() {
        nativeMediaPlayer.mute = false
    }


    /**
     * The default seek backward increment in milliseconds.
     */
    actual open val seekBackIncrement: Long = 5000

    /**
     * The default seek forward increment in milliseconds.
     */
    actual open val seekForwardIncrement: Long = 5000

    /**
     * Seeks to a specific position in the current media item in milliseconds.
     *
     * @param milliseconds The target position in milliseconds.
     */
    actual open fun seekTo(milliseconds: Long) {
        nativeMediaPlayer.seek = (milliseconds / 1000L).toDouble().toJsNumber()
    }

    /**
     * Selects a media item from the playlist by its index.
     *
     * @param index The index of the media item to play.
     *
     *
     * This function takes an integer `index` as input, which represents the position of the desired media item
     * within the `astroMediaItems` playlist. It performs the following steps:
     *
     * 1. **Safety Check:** The function first verifies if the provided `index` is within the valid range of the playlist.
     * If the index is greater than the size of the playlist minus one (indicating an out-of-bounds index), the function exits
     * without making any changes.
     * 2. **Update Index:** If the index is valid, the `currentMediaItemIndex` property is updated to point to the selected
     * media item. This essentially sets the current position within the playlist.
     * 3. **Generate Howl Properties:** Similar to the `nextMediaItem` function, a helper function named
     * `createHowlProperties` is used. This function takes the source URL of the selected media item (`astroMediaItems[index].source`)
     * and creates a new `HowlProperties` object. It also copies the current playback settings (volume, html5, loop, etc.)
     * from the `nativeMediaPlayer` object to ensure consistent playback behavior.
     * 4. **Create New Howl Instance:** A new `Howl` instance is created using the generated `HowlProperties`. This effectively
     * replaces the existing player with the selected media item, starting playback from the beginning.
     *
     * By following these steps, the `seekToMediaItem` function allows you to navigate the playlist and play any media item
     * based on its index.
     */
    actual open fun seekToMediaItem(index: Int) {
        // Just for safety
        if(index > astroMediaItems.size - 1) return

        // Only update index if we have a next mediaitem
        nativeCurrentMediaItemIndex = index

        // Create new howl for the next media item , now the player is set for the current media item
        val mediaItem = astroMediaItems[nativeCurrentMediaItemIndex]
        val source = mediaItem.first.source.toString()
        val properties = nativeMediaPlayer.createHowlProperties(source)
        nativeMediaPlayer = Howl(properties)

        // Associate the new mediaplayer with this item
        astroMediaItems[nativeCurrentMediaItemIndex] = mediaItem.first to nativeMediaPlayer
    }

    /**
     * Gets the current playback position in milliseconds.
     */
    actual open val currentPosition: Long get() = (nativeMediaPlayer.seek.toDouble() * 1000.0).toLong()

    /**
     * Gets the duration of the current media item in milliseconds.
     */
    actual open val contentDuration: Long get() = nativeMediaPlayer.duration().toDouble().toLong()

    /**
     * Gets the currently playing media item, or null if no media item is playing.
     */
    actual open val currentMediaItem: AstroMediaItem?
        get() = astroMediaItems[currentMediaItemIndex].first

    /**
     * Gets the index of the currently playing media item in the playlist, or -1 if no media item is playing.
     */
    actual open val currentMediaItemIndex: Int get() = nativeCurrentMediaItemIndex

    /**
     * Gets the total number of media items in the playlist.
     */
    actual open val mediaItemCount: Int get() = astroMediaItems.size
    /**
     * Removes all media items from the playlist.
     */
    actual open fun clearMediaItems() {
        // Destroy all players then clear the list
        astroMediaItems.forEach {
            it.second?.unload()
        }

        astroMediaItems.clear()
    }

    /**
     * Adds a media item to the playlist.
     *
     * @param item The media item to add.
     */
    actual open fun addMediaItem(item: AstroMediaItem) {
        astroMediaItems.add(item to null)
    }

    /**
     * Inserts a media item at a specific index in the playlist.
     *
     * @param index The index at which to insert the media item.
     * @param item The media item to add.
     */
    actual open fun addMediaItem(
        index: Int,
        item: AstroMediaItem,
    ) {
        astroMediaItems.add(index,item to null)
    }

    /**
     * Adds a list of media items to the end of the playlist.
     *
     * @param items The list of media items to add.
     */
    actual open fun addMediaItems(items: List<AstroMediaItem>) {
        astroMediaItems.addAll(items.map { it to null })
    }

    /**
     * Inserts a list of media items at a specific index in the playlist.
     *
     * @param index The index at which to insert the media items.
     * @param items The list of media items to add.
     */
    actual open fun addMediaItems(
        index: Int,
        items: List<AstroMediaItem>,
    ) {
        astroMediaItems.addAll(index,items.map { it to null })
    }

    /**
     * Moves a media item from one index to another in the playlist.
     *
     * @param currentIndex The current index of the media item to move.
     * @param newIndex The new index for the media item.
     */
    actual open fun moveMediaItem(currentIndex: Int, newIndex: Int) {
        val item = astroMediaItems.removeAt(currentIndex)
        astroMediaItems.add(newIndex,item)
    }

    /**
     * Moves a range of media items from one index to another in the playlist.
     *
     * @param fromIndex The starting index of the media items to move.
     * @param toIndex The ending index of the media items to move (exclusive).
     * @param newIndex The new index at which to insert the moved media items.
     */
    actual open fun moveMediaItems(
        fromIndex: Int,
        toIndex: Int,
        newIndex: Int,
    ) {
        // No need to move as it is same
        if (fromIndex == newIndex) {
            return
        }

        // No Concurrency Modify Error
        val sublist = astroMediaItems.subList(fromIndex, toIndex).toList()

        astroMediaItems.removeAll(sublist)
        astroMediaItems.addAll(newIndex,sublist)
    }

    /**
     * Removes a media item from the playlist by its index.
     *
     * @param index The index of the media item to remove.
     */
    actual open fun removeMediaItem(index: Int) {
        astroMediaItems.removeAt(index).second?.unload()
    }

    /**
     * Removes a range of media items from the playlist.
     *
     * @param fromIndex The starting index of the media items to remove (inclusive).
     * @param toIndex The ending index of the media items to remove (exclusive).
     */
    actual open fun removeMediaItems(fromIndex: Int, toIndex: Int) {
        // No removeRange for KMP
        val items = astroMediaItems.subList(fromIndex, toIndex)
        items.forEach { it.second?.unload() }
        items.clear()
    }

    /**
     * Replaces a media item at a specific index in the playlist.
     *
     * @param index The index of the media item to replace.
     * @param mediaItem The new media item to insert.
     */
    actual open fun replaceMediaItem(
        index: Int,
        mediaItem: AstroMediaItem,
    ) {
        removeMediaItem(index)
        addMediaItem(index,mediaItem)
    }

    /**
     * Replaces a range of media items in the playlist with a new list of media items.
     *
     * @param fromIndex The starting index of the media items to replace (inclusive).
     * @param toIndex The ending index of the media items to replace (exclusive).
     * @param mediaItems The list of new media items to insert.
     */
    actual open fun replaceMediaItems(
        fromIndex: Int,
        toIndex: Int,
        mediaItems: List<AstroMediaItem>,
    ) {
        removeMediaItems(fromIndex, toIndex)
        addMediaItems(fromIndex,mediaItems)
    }

    private var nativeCurrentMediaItemIndex = -1

    /** Using [MutableList] instead of [MutableMap] for operations like `*MediaItems(at index)` **/
    private val astroMediaItems : MutableList<Pair<AstroMediaItem,Howl?>> = mutableListOf()

    /**
     * Returns a list containing all media items in the playlist.
     *
     * @return A list of [AstroMediaItem] objects.
     */
    actual open fun allMediaItems(): List<AstroMediaItem> = astroMediaItems.map { it.first }

    /**
     * Applies a transformation function to each media item in the playlist and returns a list of the transformed results.
     *
     * @param transform The transformation function to apply to each media item.
     * @return A list containing the results of applying the transformation function to each media item.
     */
    actual open fun <T> mapMediaItems(transform: (AstroMediaItem) -> T): List<T> = astroMediaItems.map { transform(it.first) }

    // TODO : Work on equalizers for wasm target
    /**
     * Gets or sets whether the equalizer is enabled.
     */
    actual var isEqualizerEnabled: Boolean = false
        set(value) {
            field = value
            forEachListener { it.onEqualizerEnabledChanged(field) }
        }

    /**
     * An optional callback function that allows you to provide custom equalizer values for specific equalizer preset IDs.
     * This property is used for smart equalizers, where the player can dynamically adjust the sound based on the selected preset.
     *
     * If you want to use custom equalizer values, you need to set this property to a function that takes an equalizer preset ID (as a string)
     * and returns an [EqualizerValues] object containing the desired settings for that preset.
     *
     * If this property is null, the player will use its default equalizer settings for each preset.
     */
    actual var smartEqualizerPicker: ((id: String) -> EqualizerValues)? = null

    /**
     * Gets or sets whether smart equalizer is enabled. When enabled, the player will attempt to apply an equalizer
     * configuration based on the current media item's equalizer ID and the following logic:
     *
     * 1. **No Equalizer ID:** If the current media item does not have an equalizer ID set, no equalizer will be applied.
     * 2. **Default Presets:** If an equalizer ID is provided, the player will first search for a matching preset
     *    among built-in equalizer options (implementation-specific). If a match is found, the corresponding
     *    equalizer settings will be applied.
     * 3. **Custom Presets:** If no default preset is found and `smartEqualizerPicker` is not null, the player
     *    will call this callback function with the media item's equalizer ID, and those custom values will be applied.
     * 4. **No Equalizer Applied:** If none of the above conditions are met, no equalizer will be applied to the playback.
     */
    actual var isSmartEqualizerEnabled: Boolean = isEqualizerEnabled
        set(value) {
            field = value
            forEachListener { it.onSmartEqualizerEnabledChanged(field) }
        }

    /**
     * Gets the current equalizer values, or null if the equalizer is not enabled or not supported.
     */
    actual var currentEqualizerValues: EqualizerValues? = null
        set(value) {
            field = value
            forEachListener { it.onCurrentEqualizerValuesChanged(field) }
        }

    internal actual val listenersStore: MutableMap<Int, AstroListener> = mutableMapOf()
    internal actual fun registerNativeListenerForAstro() {
        // TODO : Register listeners
    }

    internal actual fun deregisterNativeListenerForAstro() {
        // TODO : Deregister listeners
    }


    actual companion object{}

    // -- List + Mutable List
    override val size: Int get()= astroMediaItems.size

    override fun contains(element: AstroMediaItem): Boolean = astroMediaItems.any { it.first == element }
    override fun containsAll(elements: Collection<AstroMediaItem>): Boolean = elements.all { contains(it) }
    override fun get(index: Int): AstroMediaItem = astroMediaItems[index].first
    override fun indexOf(element: AstroMediaItem): Int = astroMediaItems.indexOfFirst { it.first == element }
    override fun isEmpty(): Boolean = astroMediaItems.isEmpty()
    override fun iterator(): Iterator<AstroMediaItem> = astroMediaItems.map { it.first }.iterator()
    override fun lastIndexOf(element: AstroMediaItem): Int = astroMediaItems.indexOfLast { it.first == element }
    override fun listIterator(): ListIterator<AstroMediaItem> = astroMediaItems.map { it.first }.listIterator()
    override fun listIterator(index: Int): ListIterator<AstroMediaItem> = astroMediaItems.map { it.first }.listIterator(index)
    override fun subList(fromIndex: Int, toIndex: Int): List<AstroMediaItem> = astroMediaItems.map { it.first }.subList(fromIndex, toIndex)
    override fun toString(): String = astroMediaItems.toString()
}