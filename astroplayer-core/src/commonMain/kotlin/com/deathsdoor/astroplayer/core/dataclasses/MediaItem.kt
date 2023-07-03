package com.deathsdoor.astroplayer.core.dataclasses

import com.deathsdoor.uri.URI
import com.deathsdoor.uuid.UUID
import kotlinx.serialization.Serializable

@Serializable
data class MediaItem(
    val id : UUID,
    val media : URI,
    val metadata : MediaMetadata
)  /*TODO do this to update source : AutoCloseable {
    override fun close() {
    }
}*/