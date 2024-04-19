@file:Suppress("UNUSED")

package com.deathsdoor.astroplayer.core

import com.deathsdoor.astroplayer.core.listeners.AstroListener

/**
 * Seeks the playback position forward by the specified number of milliseconds.
 *
 * This method is equivalent to calling `seekTo(currentPosition + milliseconds)`.
 *
 * @param milliseconds The number of milliseconds to seek forward.
 */
fun AstroPlayer.seekForwardBy(milliseconds: Long) = seekTo(currentPosition + milliseconds)

/**
 * Seeks the playback position backward by the specified number of milliseconds.
 *
 * This method is equivalent to calling `seekTo(currentPosition - milliseconds)`.
 *
 * @param milliseconds The number of milliseconds to seek backward.
 */
fun AstroPlayer.seekBackwardBy(milliseconds: Long) = seekTo(currentPosition - milliseconds)

/**
 * Seeks the playback position forward by the default seek forward increment.
 *
 * This method utilizes the player's configured `seekForwardIncrement` value to move the playback position forward.
 */
fun AstroPlayer.seekForwardByIncrement() = seekTo(currentPosition + seekForwardIncrement)

/**
 * Seeks the playback position backward by the default seek backward increment.
 *
 * This method utilizes the player's configured `seekBackIncrement` value to move the playback position backward.
 */
fun AstroPlayer.seekBackwardByIncrement() = seekTo(currentPosition - seekBackIncrement)

/**
 * Seeks the playback position to the beginning of the current media item.
 *
 * This method is equivalent to calling `seekTo(0)`.
 */
fun AstroPlayer.seekToStartOfMediaItem() {
    seekTo(0)
}

/**
 * Seeks the playback position to the end of the current media item.
 *
 * This method is equivalent to calling `seekTo(contentDuration)`.
 */
fun AstroPlayer.seekToEndOfMediaItem() {
    seekTo(contentDuration)
}

/**
 * Seeks to a specific media item and then sets the playback position within that item.
 *
 * This method first calls `seekToMediaItem(index)` to switch to the media item at the provided index.
 * Then, it calls `seekTo(milliseconds)` to set the playback position within that media item to the specified time in milliseconds.
 *
 * @param index The index of the media item to seek to.
 * @param milliseconds The target position within the media item in milliseconds.
 */
fun AstroPlayer.seekToMediaItemThenAt(index: Int, milliseconds: Long) {
    seekToMediaItem(index)
    seekTo(milliseconds)
}

/**
 * Seeks to the next media item in the playlist, if available.
 *
 * This method checks if there's a next media item using `hasNextMediaItem` and seeks to it using
 * `seekToMediaItem(currentMediaItemIndex + 1)` if there is one.
 */
fun AstroPlayer.seekToNextMediaItem() {
    if (hasNextMediaItem) {
        seekToMediaItem(currentMediaItemIndex + 1)
    }
}

/**
 * Seeks to the previous media item in the playlist, if available.
 *
 * This method checks if there's a previous media item using `hasPreviousMediaItem` and seeks to it using
 * `seekToMediaItem(currentMediaItemIndex - 1)` if there is one.
 */
fun AstroPlayer.seekToPreviousMediaItem() {
    if (hasPreviousMediaItem) {
        seekToMediaItem(currentMediaItemIndex - 1)
    }
}

/**
 * The metadata associated with the currently playing media item, or null if no item is playing or the item has no metadata.
 *
 * This property provides a read-only access to the `metadata` property of the currently playing media item
 * (accessible through `currentMediaItem`). It returns null if there's no media item currently playing or
 * if the media item doesn't have any associated metadata.
 */
val AstroPlayer.currentMediaMetadata get() = currentMediaItem?.metadata

/**
 * Indicates whether there's a next media item available in the playlist after the current one.
 *
 * This property checks if the current media item index (`currentMediaItemIndex`) is less than the total
 * number of media items in the playlist (`mediaItemCount`) minus 1 (to account for zero-based indexing).
 * It returns true if there's at least one more item after the current one, and false otherwise.
 */
val AstroPlayer.hasNextMediaItem : Boolean get() = currentMediaItemIndex < mediaItemCount - 1

/**
 * Indicates whether there's a previous media item available in the playlist before the current one.
 *
 * This property checks if the current media item index (`currentMediaItemIndex`) is greater than 0.
 * It returns true if there's at least one item before the current one, and false otherwise (including when
 * the current index is 0, meaning it's the first item).
 */
val AstroPlayer.hasPreviousMediaItem : Boolean get() = currentMediaItemIndex > 0


/**
 * Clears the current playlist and then adds a single media item.
 *
 * This method is equivalent to calling `clearMediaItems()` followed by `addMediaItem(item)`.
 *
 * @param item The media item to add to the playlist.
 */
fun AstroPlayer.clearThenAddMediaItem(item: AstroMediaItem) {
    clearMediaItems()
    addMediaItem(item)
}

/**
 * Clears the current playlist and then adds a single media item at a specific index.
 *
 * This method is equivalent to calling `clearMediaItems()` followed by `addMediaItem(index, item)`.
 *
 * @param index The index at which to insert the media item in the playlist.
 * @param item The media item to add.
 */
fun AstroPlayer.clearThenAddMediaItem(index: Int, item: AstroMediaItem) {
    clearMediaItems()
    addMediaItem(index, item)
}

/**
 * Clears the current playlist and then adds a collection of media items.
 *
 * This method is equivalent to calling `clearMediaItems()` followed by `addMediaItems(item)`.
 *
 * @param item A list of media items to add to the playlist.
 */
fun AstroPlayer.clearThenAddMediaItems(item: List<AstroMediaItem>) {
    clearMediaItems()
    addMediaItems(item)
}

/**
 * Clears the current playlist and then adds a collection of media items at a specific index.
 *
 * This method is equivalent to calling `clearMediaItems()` followed by `addMediaItems(index, item)`.
 *
 * @param index The index at which to insert the media items in the playlist.
 * @param item A list of media items to add.
 */
fun AstroPlayer.clearThenAddMediaItems(index: Int, item: List<AstroMediaItem>) {
    clearMediaItems()
    addMediaItems(index, item)
}

/**
 * Registers a listener to receive notifications about playback and media state changes in AstroPlayer.
 *
 * This method adds the provided `AstroPlayerListener` to the internal list of listeners maintained by AstroPlayer.
 * When events related to playback, media state, or settings changes occur, all registered listeners will be notified
 * with the corresponding callback methods.
 *
 * The method returns an integer that can be used as an identifier for the registered listener. This ID might
 * be useful for future calls to `removeListener` if you want to unregister the listener using a specific identifier.
 *
 * @param listener The `AstroPlayerListener` object that will be registered to receive notifications.
 * @return An integer representing the ID of the registered listener (implementation-specific behavior).
 */
fun AstroPlayer.addListener(listener : AstroListener) : Int {
    // Register Listener
    if(listenersStore.isEmpty()) registerNativeListenerForAstro()

    val id = listener.hashCode()
    listenersStore[id] = listener
    return id
}

/**
 * Unregisters a previously registered listener from receiving notifications from AstroPlayer.
 *
 * This method removes the listener identified by the provided `id` from the internal list of listeners maintained by AstroPlayer.
 * The `id` should be the same value that was returned by the corresponding call to `addListener` for the specific listener you want to unregister.
 *
 * If the provided `id` does not match any registered listener, the method silently returns without any effect.
 *
 * @param id The integer ID of the listener to be unregistered, as returned by a previous call to `addListener`.
 */
fun AstroPlayer.removeListener(id : Int) {
    listenersStore.remove(id)

    // Deregister Listener
    if(listenersStore.isEmpty()) deregisterNativeListenerForAstro()
}