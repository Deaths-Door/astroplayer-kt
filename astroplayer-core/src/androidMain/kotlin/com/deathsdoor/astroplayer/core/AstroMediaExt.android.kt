package com.deathsdoor.astroplayer.core

import android.os.Bundle
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.eygraber.uri.toAndroidUri
import com.eygraber.uri.toUri


/**
 * Converts an `AstroMediaItem` object to a platform-specific `MediaItem` object suitable for the underlying media player.
 *
 * This function takes an optional `metadata` argument (defaulting to the `metadata` property of the current
 * `AstroMediaItem` after conversion using `asNativeMediaMetadata()` if available). It internally uses the
 * `asNativeMediaItemBuilder` function to create a builder, populates it with the media ID, source URI (converted
 * to an Android URI using `toAndroidUri()`), and optionally the media metadata (if provided), and then builds
 * the final `MediaItem` object.
 *
 * @param metadata An optional `MediaMetadata` object representing the media metadata in a format compatible with the underlying media player (defaults to the converted metadata from the current `AstroMediaItem`).
 * @return A `MediaItem` object suitable for the underlying media player.
 */
fun AstroMediaItem.asNativeMediaItem(metadata: MediaMetadata? = this.metadata?.asNativeMediaMetadata()): MediaItem =
    asNativeMediaItemBuilder(metadata).build()

/** Creates a builder object for a platform-specific `MediaItem` compatible with the underlying media player, pre-populated with data from the current `AstroMediaItem`.
 *
 * This function takes an optional `metadata` argument (defaulting to the `metadata` property of the current
 * `AstroMediaItem` after conversion using `asNativeMediaMetadata()` if available). It creates a new `MediaItem.Builder`
 * object and applies the following configuration:
   - If `metadata` is provided, it sets the media metadata using `setMediaMetadata(metadata)`.
   - It sets the media ID using `setMediaId(mediaId)`.
   - It sets the media source URI by converting the `source` property of the `AstroMediaItem` to an Android URI
     using `toAndroidUri()`.
 *
 * The resulting builder object allows further customization of the `MediaItem` before building the final
 * object using `build()`.
 *
 * @param metadata An optional `MediaMetadata` object representing the media metadata in a format compatible with the underlying media player (defaults to the converted metadata from the current `AstroMediaItem`).
 * @return A `MediaItem.Builder` object pre-populated with data from the current `AstroMediaItem`.
 */
fun AstroMediaItem.asNativeMediaItemBuilder(metadata: MediaMetadata? = this.metadata?.asNativeMediaMetadata()): MediaItem.Builder {
    return MediaItem.Builder().apply {
        if (metadata != null) {
            setMediaMetadata(metadata)
        }

        setMediaId(mediaId)
        setUri(source.toAndroidUri())
    }
}

/**
 * Converts an `AstroMediaMetadata` object to a platform-specific `MediaMetadata` object suitable for the underlying media player.
 *
 * This function utilizes the `asNativeMediaMetadataBuilder` function to create a builder, populates it with the
 * necessary metadata fields from the current `AstroMediaMetadata` object (likely using appropriate conversion methods),
 * and then builds the final `MediaMetadata` object compatible with the underlying media player.
 *
 * @return A `MediaMetadata` object suitable for the underlying media player.
 */
fun AstroMediaMetadata.asNativeMediaMetadata() : MediaMetadata = asNativeMediaMetadataBuilder().build()
/**
 * Creates a builder object for a platform-specific `MediaMetadata` compatible with the underlying media player, pre-populated with data from the current `AstroMediaItem.metadata`.
 *
 * This function creates a new `MediaMetadata.Builder` object and likely uses conversion methods to populate
 * the builder with the corresponding metadata fields from the current `AstroMediaMetadata` object. The specific
 * conversion logic is likely not documented here, but it presumably translates the data from `AstroMediaMetadata`
 * to a format compatible with the underlying media player's `MediaMetadata` class.
 *
 * The resulting builder object allows further customization of the `MediaMetadata` before building the final
 * object using `build()`.
 *
 * @return A `MediaMetadata.Builder` object pre-populated with data from the current `AstroMediaMetadata`.
 */
fun AstroMediaMetadata.asNativeMediaMetadataBuilder() : MediaMetadata.Builder {
    return MediaMetadata.Builder().apply {
        setTitle(title)
        setArtist(artist)
        setAlbumTitle(albumTitle)
        setAlbumArtist(albumArtist)
        setDisplayTitle(displayTitle)
        setSubtitle(subtitle)
        setTrackNumber(trackNumber)
        setTotalTrackCount(totalTrackCount)
        setRecordingYear(recordingYear)
        setRecordingMonth(recordingMonth)
        setRecordingDay(recordingDay)
        setReleaseYear(releaseYear)
        setReleaseMonth(releaseMonth)
        setReleaseDay(releaseDay)
        setWriter(writer)
        setComposer(composer)
        setConductor(conductor)
        setDiscNumber(discNumber)
        setTotalDiscCount(totalDiscCount)
        setGenre(genre)
        setCompilation(compilation)
        setStation(station)

        val bundle = Bundle()

        equalizerIdentifier?.let {
            bundle.putString(EQUALIZER_KEY,it)
        }

        extras?.let {
            for ((key, value) in it) {
                bundle.putString(key, value.toString())
            }
        }

        setExtras(bundle)
    }
}
internal const val EQUALIZER_KEY = "equalizer"

/**
 * Attempts to convert a platform-specific `MediaItem` object to an `AstroMediaItem` object.
 *
 * This function takes an optional `mapExtras` lambda function (defaulting to null). The `mapExtras` function
 * is used to process any  extra data associated with the `MediaItem` object. It takes
 * a key-value pair (both strings) as input and should return a value in a format suitable for the `AstroMediaItem`
 * object. The conversion process likely involves:

 * The function returns a nullable `AstroMediaItem` object. If the conversion fails (e.g., due to missing
 * information or incompatible data), it returns null.
 *
 * @param mapExtras An optional lambda function to process platform-specific extra data from the `MediaItem` (default: null).
 * @return An `AstroMediaItem` object representing the converted media item, or null if conversion fails.
 */
fun MediaItem.asAstroMediaItem(mapExtras : (key : String,value : String) -> Any): AstroMediaItem? {
    return requestMetadata.mediaUri?.toUri()?.let {
        AstroMediaItem(
            mediaId = mediaId,
            source = it,
            metadata =  mediaMetadata.asAstroMediaMetadata(mapExtras)
        )
    }
}

/**
 * Converts a platform-specific `MediaMetadata` object to an `AstroMediaMetadata` object.
 *
 * This function takes an optional `mapExtras` lambda function (defaulting to null). The `mapExtras` function
 * is likely used to process any platform-specific extra data associated with the `MediaMetadata` object. It takes
 * a key-value pair (both strings) as input and should return a value in a format suitable for the `AstroMediaMetadata`
 * object. The conversion process likely involves:

 * @param mapExtras An optional lambda function to process platform-specific extra data from the `MediaMetadata` (default: null).
 * @return An `AstroMediaMetadata` object representing the converted metadata.
 */
fun MediaMetadata.asAstroMediaMetadata(mapExtras : (key : String,value : String) -> Any): AstroMediaMetadata {
    val extrasMap = mutableMapOf<String,Any>()
    var equalizerIdentifier: String? = null

    extras?.let { extras ->
        extras.keySet().removeIf { value ->
            if(value == EQUALIZER_KEY) {
                equalizerIdentifier = value
                true
            } else false
        }

        extras.keySet().forEach { key ->
            val value = extras.getString(key)!!
            extrasMap[key]  = mapExtras(key,value)
        }
    }

    return AstroMediaMetadata(
        title = title?.toString(),
        artist = artist?.toString(),
        albumTitle = albumTitle?.toString(),
        albumArtist = albumArtist?.toString(),
        displayTitle = displayTitle?.toString(),
        subtitle = subtitle?.toString(),
        description = description?.toString(),
        trackNumber = trackNumber,
        totalTrackCount = totalTrackCount,
        recordingYear = recordingYear,
        recordingMonth = recordingMonth,
        recordingDay = recordingDay,
        releaseYear = releaseYear,
        releaseMonth = releaseMonth,
        releaseDay = releaseDay,
        writer = writer?.toString(),
        composer = composer?.toString(),
        conductor = conductor?.toString(),
        discNumber = discNumber,
        totalDiscCount = totalDiscCount,
        genre = genre?.toString(),
        compilation = compilation?.toString(),
        station = station?.toString(),
        equalizerIdentifier = equalizerIdentifier,
        extras = extrasMap
    )
}
