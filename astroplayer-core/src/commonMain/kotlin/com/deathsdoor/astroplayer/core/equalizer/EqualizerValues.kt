package com.deathsdoor.astroplayer.core.equalizer

/*
*  TODO :Add in future
*
*  - Bass Boost
*  - Virtualizer
*  - Reverb Effects
*       - Strenght
*       - Size
*       - Damping
*       - Density
*       - Diffusion
*       - Decay Time
*  - Room Size
* */

/**
 * A data class representing a set of equalizer values for different frequency bands.
 *
 * This data class is used to define custom equalizer configurations or to represent the settings
 * of predefined equalizer presets (see [EqualizerPresets]).
 *
 * @property identifier A unique identifier for this equalizer configuration. This ID might be used
 * by the AstroPlayer to match it with media items that have a specific equalizer preset setting.
 * @property frequencies A map that defines the gain levels for different audio frequencies.
 *     **Key:** The key represents the frequency in **Hertz (Hz)**.
 *     **Value:** The value represents the gain level for that frequency. It should be a float value
 *       between **0.0 (silent)** and **1.0 (maximum gain)**.
 */
data class EqualizerValues(
    val identifier : String,
    val frequencies: Map<Int, Float>
) : Iterable<Map.Entry<Int,Float>> {
    constructor(
        identifier : String,
        hz60 : Float,
        hz230 : Float,
        hz910 : Float,
        hz3600 : Float,
        hz14000 : Float
    ) : this(
        identifier,
        mapOf(
            60 to hz60,
            230 to hz230,
            910 to hz910,
            3600 to hz3600,
            14000 to hz14000
        )
    )

    override fun iterator(): Iterator<Map.Entry<Int,Float>> = frequencies.iterator()
}