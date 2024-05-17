package com.deathsdoor.astroplayer.core.equalizer

/**
 * An object that provides a collection of predefined equalizer values for various music genres.
 */
@Suppress("MemberVisibilityCanBePrivate")
object EqualizerPresets {
    /**
     * The default equalizer preset with balanced settings.
     */
    val Default = EqualizerValues("default", mapOf(20 to 0.5f,60 to 0.5f,125 to 0.5f,170 to 0.5f,250 to 0.5f,500 to 0.5f,1000 to 0.5f,2000 to 0.5f,3600 to 0.5f,8000 to 0.5f,16000 to 0.5f))

    /**
     * A preset optimized for HipHop music
     */
    val HipHop = EqualizerValues(identifier="hiphop",frequencies=mapOf(20 to 0.8f,60 to 0.8f,125 to 0.6f,170 to 0.5f,250 to 0.6f,500 to 0.7f,1000 to 0.8f,2000 to 1.0f,3600 to 0.8f,8000 to 0.7f,16000 to 0.6f))


    /**
     * A preset optimized for Rock music
     */
    val Rock = EqualizerValues(identifier="rock",frequencies=mapOf(20 to 0.7f,60 to 0.7f,125 to 0.8f,170 to 1.0f,250 to 0.8f,500 to 0.6f,1000 to 0.5f,2000 to 0.6f,3600 to 0.7f,8000 to 0.8f,16000 to 0.7f))


    /**
     * A preset optimized for Dance music
     */
    val Dance = EqualizerValues(identifier="dance",frequencies=mapOf(20 to 0.6f,60 to 0.6f,125 to 0.7f,170 to 0.8f,250 to 1.0f,500 to 0.8f,1000 to 0.7f,2000 to 0.8f,3600 to 0.6f,8000 to 0.6f,16000 to 0.5f))


    /**
     * A preset optimized for Classical music
     */
    val Classical = EqualizerValues(identifier="classical",frequencies=mapOf(20 to 0.5f,60 to 0.6f,125 to 0.7f,170 to 0.8f,250 to 1.0f,500 to 0.8f,1000 to 0.7f,2000 to 0.6f,3600 to 0.5f,8000 to 0.5f,16000 to 0.5f))


    /**
     * A preset optimized for Pop music
     */
    val Pop = EqualizerValues(identifier="pop",frequencies=mapOf(20 to 0.6f,60 to 0.7f,125 to 0.8f,170 to 0.9f,250 to 1.0f,500 to 0.8f,1000 to 0.7f,2000 to 0.6f,3600 to 0.6f,8000 to 0.7f,16000 to 0.6f))


    /**
     * A preset optimized for Jazz music
     */
    val Jazz = EqualizerValues(identifier="jazz",frequencies=mapOf(20 to 0.6f,60 to 0.7f,125 to 0.8f,170 to 0.8f,250 to 0.9f,500 to 1.0f,1000 to 0.8f,2000 to 0.7f,3600 to 0.6f,8000 to 0.5f,16000 to 0.5f))


    /**
     * A preset optimized for RNB music
     */
    val RNB = EqualizerValues(identifier="rnb",frequencies=mapOf(20 to 0.7f,60 to 0.8f,125 to 0.8f,170 to 0.8f,250 to 0.8f,500 to 1.0f,1000 to 0.8f,2000 to 0.7f,3600 to 0.6f,8000 to 0.6f,16000 to 0.5f))


    /**
     * A preset optimized for Electronic music
     */
    val Electronic = EqualizerValues(identifier="electronic",frequencies=mapOf(20 to 0.6f,60 to 0.6f,125 to 0.6f,170 to 0.7f,250 to 0.8f,500 to 1.0f,1000 to 0.8f,2000 to 0.7f,3600 to 0.8f,8000 to 0.8f,16000 to 0.7f))


    /**
     * A preset optimized for Country music
     */
    val Country = EqualizerValues(identifier="country",frequencies=mapOf(20 to 0.7f,60 to 0.8f,125 to 0.9f,170 to 1.0f,250 to 0.8f,500 to 0.6f,1000 to 0.5f,2000 to 0.6f,3600 to 0.7f,8000 to 0.8f,16000 to 0.7f))


    /**
     * A preset optimized for Reggae music
     */
    val Reggae = EqualizerValues(identifier="reggae",frequencies=mapOf(20 to 0.7f,60 to 0.8f,125 to 0.9f,170 to 1.0f,250 to 0.7f,500 to 0.5f,1000 to 0.5f,2000 to 0.6f,3600 to 0.7f,8000 to 0.8f,16000 to 0.7f))

    /**
     * A preset optimized for Blues music, emphasizing vocals and guitars with a warm tone.
     */
    val Blues = EqualizerValues("blues", frequencies = mapOf(20 to 0.2f, 60 to 0.3f, 125 to 1.5f, 170 to 2.0f, 250 to 1.1f, 500 to 1.0f, 1000 to 1.0f, 2000 to 1.0f, 3600 to 1.0f, 8000 to 1.0f, 16000 to 1.0f))

    /**
     * A preset optimized for Metal music, emphasizing guitars and drums with high intensity.
     */
    val Metal = EqualizerValues("metal", frequencies = mapOf(20 to 2.0f, 60 to 0.8f, 125 to 0.7f, 170 to 0.5f, 250 to 2.2f, 500 to 1.0f, 1000 to 1.0f, 2000 to 1.0f, 3600 to 1.0f, 8000 to 1.0f, 16000 to 1.0f))

    /**
     * A preset optimized for Folk music, emphasizing vocals and acoustic instruments.
     */
    val Folk = EqualizerValues("folk", frequencies = mapOf(20 to 0.45f, 60 to 0.3f, 125 to 0.2f, 170 to 1.9f, 250 to 2.1f, 500 to 1.0f, 1000 to 1.0f, 2000 to 1.0f, 3600 to 1.0f, 8000 to 1.0f, 16000 to 1.0f))

    /**
     * A map containing all available equalizer presets, with their corresponding IDs as keys.
     */
    val AllPresets = listOf(
        Default,
        HipHop,
        Rock,
        Dance,
        Classical,
        Pop,
        Jazz,
        RNB,
        Electronic,
        Country,
        Reggae,
        Blues,
        Metal,
        Folk
    ).associateBy { it.identifier }
}