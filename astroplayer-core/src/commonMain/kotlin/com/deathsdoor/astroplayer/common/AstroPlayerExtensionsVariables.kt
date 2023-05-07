package com.deathsdoor.astroplayer.common

import com.deathsdoor.astroplayer.AstroPlayer
import com.deathsdoor.astroplayer.dataclasses.MediaItem
import com.deathsdoor.astroplayer.dataclasses.MediaMetadata

val AstroPlayer.mediaItemsLength: Int get() = mediaItems.size
val AstroPlayer.currentMediaItem : MediaItem get() = mediaItems[currentMediaItemIndex]
val AstroPlayer.currentMediaItemMetadata : MediaMetadata get() = currentMediaItem.metadata
val AstroPlayer.currentMediaItemTrackLength: Long get() = currentMediaItemMetadata.trackLength

val AstroPlayer.hasNextMediaItem : Boolean get() = currentMediaItemIndex < mediaItemsLength - 1
val AstroPlayer.hasPreviousMediaItem : Boolean get() = currentMediaItemIndex > 0