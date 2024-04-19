package com.deathsdoor.astroplayer.core

import com.eygraber.uri.Uri

/**
 * A data class representing metadata associated with a media item in the AstroPlayer playlist.
 * This data class provides optional fields for various metadata categories. Not all fields might be available
 * for all media types, and the specific information available will depend on the media source and the player implementation.
 *
 * @property title The title of the media item (e.g., song title, movie title).
 * @property artist The artist or performer associated with the media item (e.g., singer, band).
 * @property albumTitle The title of the album the media item belongs to.
 * @property albumArtist The artist or performer associated with the album.
 * @property displayTitle An alternative title for display purposes.
 * @property subtitle A subtitle or secondary title for the media item.
 * @property description A textual description of the media item.
 * @property artworkUri The URI of artwork associated with the media item (e.g., album cover).
 * @property trackNumber The track number within the album or playlist (1-based).
 * @property totalTrackCount The total number of tracks in the album or playlist.
 * @property recordingYear The year the media was recorded.
 * @property recordingMonth The month the media was recorded (1-based).
 * @property recordingDay The day of the month the media was recorded.
 * @property releaseYear The year the media was released.
 * @property releaseMonth The month the media was released (1-based).
 * @property releaseDay The day of the month the media was released.
 * @property writer The writer(s) of the media content (e.g., songwriter).
 * @property composer The composer(s) of the media content (e.g., music composer).
 * @property conductor The conductor associated with the media content (e.g., orchestra conductor).
 * @property discNumber The disc number within a multi-disc set (1-based).
 * @property totalDiscCount The total number of discs in a multi-disc set.
 * @property genre The genre of the media content.
 * @property compilation Whether the media is part of a compilation album.
 * @property station The radio station associated with the media (if applicable).
 * @property equalizerIdentifier An identifier for a specific equalizer preset to be applied to this media item
 * (implementation-specific). This is used with `AstroPlayer.isSmartEqualizerEnabled` for smart equalizer functionality.
 * @property extras A map containing additional metadata key-value pairs that are not covered by the other properties.
 */
data class AstroMediaMetadata(
    val title: String? = null,
    val artist: String? = null,
    val albumTitle: String? = null,
    val albumArtist: String? = null,
    val displayTitle: String? = null,
    val subtitle: String? = null,
    val description: String? = null,
    val artworkUri: Uri? = null,
    val trackNumber: Int? = null,
    val totalTrackCount: Int? = null,
    val recordingYear: Int? = null,
    val recordingMonth: Int? = null,
    val recordingDay: Int? = null,
    val releaseYear: Int? = null,
    val releaseMonth: Int? = null,
    val releaseDay: Int? = null,
    val writer: String? = null,
    val composer: String? = null,
    val conductor: String? = null,
    val discNumber: Int? = null,
    val totalDiscCount: Int? = null,
    val genre: String? = null,
    val compilation: String? = null,
    val station: String? = null,
    val equalizerIdentifier : String? = null,
    val extras: Map<String,Any>? = null
)