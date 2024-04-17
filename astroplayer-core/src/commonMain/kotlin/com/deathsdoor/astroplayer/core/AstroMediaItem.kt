package com.deathsdoor.astroplayer.core

import com.eygraber.uri.Uri

/**
 * A data class representing a single media item in the AstroPlayer playlist.
 *
 * @property mediaId A unique identifier for the media item. The format and meaning of this ID are
 * implementation-specific. This ID can be used to reference the media item within the AstroPlayer API.
 * @property source The URI (Uniform Resource Identifier) of the media resource to play. This can be:
 *   - A local file path (e.g., `/storage/music/my_song.mp3`)
 *   - A remote URL (e.g., "https://www.example.com/audio.ogg")
 *   - Any other URI supported by [Uri]
 * @property metadata Optional metadata associated with the media item. This [AstroMediaMetadata]
 * provides additional information such as title, artist, album, genre, and more.
 */
data class AstroMediaItem(
    val mediaId : String,
    val source : Uri,
    val metadata : AstroMediaMetadata?,
)