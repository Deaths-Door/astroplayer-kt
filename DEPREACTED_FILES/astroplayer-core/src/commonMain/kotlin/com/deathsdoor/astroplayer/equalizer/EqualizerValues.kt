package com.deathsdoor.astroplayer.equalizer

/*
* TODO add
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
@ExperimentalMultiplatform
interface EqualizerValues {
    var hz60 : Float
    var hz230 : Float
    var hz910 : Float
    var hz3600 : Float
    var hz14000 : Float
    companion object {
        fun createEqualizerValues(
            hz60 : Float = Equalizer.Default.hz60, hz230 : Float = Equalizer.Default.hz230,
            hz910 : Float = Equalizer.Default.hz910, hz3600 : Float = Equalizer.Default.hz3600,
            hz14000 : Float = Equalizer.Default.hz14000
        ): EqualizerValues = object : EqualizerValues {
            override var hz60: Float = hz60
            override var hz230: Float = hz230
            override var hz910: Float = hz910
            override var hz3600: Float = hz3600
            override var hz14000: Float = hz14000
        }
    }
}

