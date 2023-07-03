package com.deathsdoor.astroplayer.core.common

import com.deathsdoor.astroplayer.core.AstroPlayer
import com.deathsdoor.astroplayer.core.dataclasses.MediaItem
import com.deathsdoor.astroplayer.core.dataclasses.MediaMetadata
val AstroPlayer.mediaItemsLength: Int get() = mediaItems.size
val AstroPlayer.currentMediaItem : MediaItem? get() = if(mediaItemsLength == 0) null else mediaItems[currentMediaItemIndex]
val AstroPlayer.currentMediaItemMetadata : MediaMetadata? get() = currentMediaItem?.metadata
val AstroPlayer.currentMediaItemTrackLength: Long get() = currentMediaItemMetadata?.trackLength ?: 0

val AstroPlayer.hasNextMediaItem : Boolean get() = currentMediaItemIndex < mediaItemsLength - 1
val AstroPlayer.hasPreviousMediaItem : Boolean get() = currentMediaItemIndex > 0