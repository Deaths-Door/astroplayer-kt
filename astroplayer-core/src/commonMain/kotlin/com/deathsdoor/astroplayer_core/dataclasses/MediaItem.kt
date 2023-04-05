package com.deathsdoor.astroplayer_core.dataclasses

data class MediaItem(
    val mediaID : String,
    val mediaSource : String,
    val sourceType : MediaSource,
    val metadata : MediaMetadata
){
    //TODO add more sources
    enum class MediaSource{
        File,
        Url,
        Unknown
    }
}

