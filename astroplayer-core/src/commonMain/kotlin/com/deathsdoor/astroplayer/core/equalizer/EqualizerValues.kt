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
 * @property hz60 The gain adjustment for the low-frequency band (around 60 Hz). This typically affects
 * the bass response. Positive values boost the bass, while negative values attenuate it.
 * @property hz230 The gain adjustment for the mid-low frequency band (around 230 Hz). This can affect
 * the body of instruments like guitars and cellos.
 * @property hz910 The gain adjustment for the mid-frequency band (around 910 Hz). This can affect
 * the clarity of vocals and lead instruments.
 * @property hz3600 The gain adjustment for the high-mid frequency band (around 3600 Hz). This can affect
 * the brightness and sibilance of instruments.
 * @property hz14000 The gain adjustment for the high-frequency band (around 14000 Hz). This typically affects
 * the detail and crispness of the sound.
 */
data class EqualizerValues(
    val identifier : String,
    val hz60 : Float,
    val hz230 : Float,
    val hz910 : Float,
    val hz3600 : Float,
    val hz14000 : Float
) : Iterable<Float> {
    override fun iterator(): Iterator<Float> = listOf(hz60,hz230,hz910,hz3600,hz14000).iterator()
}