package com.deathsdoor.astroplayer_core.platfromCommonFunctions

import com.deathsdoor.astroplayer_core.AstroPlayer
import com.deathsdoor.astroplayer_core.dataclasses.MediaItem
import com.deathsdoor.astroplayer_core.dataclasses.MediaMetadata

val AstroPlayer.totalMediaItems: Int get() = mediaItems.size
val AstroPlayer.currentMediaItem : MediaItem get() = mediaItems[currentMediaItemIndex]
val AstroPlayer.currentMediaItemMetadata : MediaMetadata get() = currentMediaItem.metadata
val AstroPlayer.currentMediaItemTrackLength: Long get() = currentMediaItemMetadata.trackLength