package com.deathsdoor.astroplayer.dataclasses

import com.deathsdoor.astroplayer.enums.MediaSource

data class MediaItem(
    val mediaID : String,
    val mediaSource : String,
    val sourceType : MediaSource = MediaSource.Network,
    val metadata : MediaMetadata
)