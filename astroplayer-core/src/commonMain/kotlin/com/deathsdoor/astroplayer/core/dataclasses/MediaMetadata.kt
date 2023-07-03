package com.deathsdoor.astroplayer.core.dataclasses

import com.deathsdoor.uri.URI
import kotlinx.serialization.Serializable
import kotlin.properties.ObservableProperty
@Serializable
data class MediaMetadata (
    var trackLength : Long,
    var title: String,
    var artwork : URI? = null,
    var artist: List<String>? = null,
    var albumTitle: String? = null,
    var albumArtist: List<String>? = null,
    var albumArtwork : String? = null,
    var subtitle: String? = null,
    var description: String? = null,
    var trackNumber: Int? = null,
    var totalTrackCount: Int? = null,
    var recordingYear: Int? = null,
    var recordingMonth: Int? = null,
    var recordingDay: Int? = null,
    var releaseYear: Int? = null,
    var releaseMonth: Int? = null,
    var releaseDay: Int? = null,
    var writer: String? = null,
    var composer: String? = null,
    var conductor: String? = null,
    var discNumber: Int? = null,
    var totalDiscCount: Int? = null,
    var genre: String? = null,
    var compilation: String? = null,
    var station: String? = null,
    var mood : String? = null,
    var sprache : String? = null,
    var lyrics : Lyric? = null,
    var isLiked : Boolean = false
)