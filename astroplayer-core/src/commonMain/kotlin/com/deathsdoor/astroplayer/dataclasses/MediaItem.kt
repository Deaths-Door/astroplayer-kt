package com.deathsdoor.astroplayer.dataclasses
data class MediaItem(
    val mediaID : String,
    val mediaSource : String,
    val sourceType : MediaSource,
    val metadata : MediaMetadata
){
    enum class MediaSource{
        File,
        Url,
        Unknown
    }
}