package com.deathsdoor.astroplayer.core.equalizer

/**
 * An object that provides a collection of predefined equalizer values for various music genres.
 */
object EqualizerPresets {

    /**
     * The default equalizer preset with balanced settings.
     */
    val Default = EqualizerValues("default", 0f, 0f, 0f, 0f, 0f)

    /**
     * A preset optimized for Hip Hop music, emphasizing bass and low-mids.
     */
    val HipHop = EqualizerValues("hiphop", 2.1f, 0.8f, 3.7f, 1.2f, 1.9f)

    /**
     * A preset optimized for Rock music, emphasizing guitars and drums.
     */
    val Rock = EqualizerValues("rock", 1.8f, 2.5f, 1.7f, 0.9f, 1.3f)

    /**
     * A preset optimized for Dance music, emphasizing bass and high-mids.
     */
    val Dance = EqualizerValues("dance", 0.7f, 2.2f, 2.8f, 2.4f, 0.8f)

    /**
     * A preset optimized for Classical music, emphasizing clarity and detail.
     */
    val Classical = EqualizerValues("classical", 0.32f, 0.65f, 1.2f, 1.4f, 1.8f)

    /**
     * A preset optimized for Pop music, emphasizing vocals and instruments.
     */
    val Pop = EqualizerValues("pop", 1.7f, 0.9f, 1.8f, 1.1f, 1.2f)

    /**
     * A preset optimized for Jazz music, emphasizing midrange and warmth.
     */
    val Jazz = EqualizerValues("jazz", 0.4f, -0.94f, 1.6f, 1.9f, 0.7f) // Note: Includes a negative gain value

    /**
     * A preset optimized for R&B music, emphasizing vocals and bass.
     */
    val RNB = EqualizerValues("rnb", 1.3f, 1.1f, 0.9f, 1.7f, 1.6f)

    /**
     * A preset optimized for Electronic music, emphasizing synthesizers and effects.
     */
    val Electronic = EqualizerValues("electronic", 0.9f, 1.0f, 0.8f, 2.3f, 2.1f)

    /**
     * A preset optimized for Country music, emphasizing vocals and midrange instruments.
     */
    val Country = EqualizerValues("country", 0.6f, 0.4f, 1.1f, 1.8f, 1.4f)

    /**
     * A preset optimized for Reggae music, emphasizing bass and low-mids with a relaxed feel.
     */
    val Reggae = EqualizerValues("reggae", 1.9f, 0.7f, 0.8f, 1.0f, 2.0f)

    /**
     * A preset optimized for Blues music, emphasizing vocals and guitars with a warm tone.
     */
    val Blues = EqualizerValues("blues", 0.2f, 0.3f, 1.5f, 2.0f, 1.1f)

    /**
     * A preset optimized for Metal music, emphasizing guitars and drums with high intensity.
     */
    val Metal = EqualizerValues("metal", 2.0f, 0.8f, 0.7f, 0.5f, 2.2f)

    /**
     * A preset optimized for Folk music, emphasizing vocals and acoustic instruments.
     */
    val Folk = EqualizerValues("folk", 0.45f, 0.3f, 0.2f, 1.9f, 2.1f)

    /**
     * A map containing all available equalizer presets, with their corresponding IDs as keys.
     */
    val AllPresets = listOf(
        EqualizerPresets.Default,
        EqualizerPresets.HipHop,
        EqualizerPresets.Rock,
        EqualizerPresets.Dance,
        EqualizerPresets.Classical,
        EqualizerPresets.Pop,
        EqualizerPresets.Jazz,
        EqualizerPresets.RNB,
        EqualizerPresets.Electronic,
        EqualizerPresets.Country,
        EqualizerPresets.Reggae,
        EqualizerPresets.Blues,
        EqualizerPresets.Metal,
        EqualizerPresets.Folk
    ).associateBy { it.identifier }
}