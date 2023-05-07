package com.deathsdoor.astroplayer.equalizer

/*
*  Add in future
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
interface EqualizerValues {
    val hz60 : Float
    val hz230 : Float
    val hz910 : Float
    val hz3600 : Float
    val hz14000 : Float
    companion object {
        fun createEqualizerValues(
            hz60 : Float = Equalizer.Default.hz60, hz230 : Float = Equalizer.Default.hz230,
            hz910 : Float = Equalizer.Default.hz910, hz3600 : Float = Equalizer.Default.hz3600,
            hz14000 : Float = Equalizer.Default.hz14000
        ): EqualizerValues = object : EqualizerValues {
            override val hz60: Float = hz60
            override val hz230: Float = hz230
            override val hz910: Float = hz910
            override val hz3600: Float = hz3600
            override val hz14000: Float = hz14000
        }
    }
}

