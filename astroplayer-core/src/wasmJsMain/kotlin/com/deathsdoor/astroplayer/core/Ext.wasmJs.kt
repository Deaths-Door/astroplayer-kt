package com.deathsdoor.astroplayer.core

private fun createHowlProperties(
    source: String,
    volume: Float,
    html5: Boolean,
    loop: Boolean,
    preload: JsAny,
    autoplay: Boolean,
    mute: Boolean,
    rate: JsNumber
): HowlerProperties = js("""{ src : ["source"] ,volume : volume,  html : html5,loop : loop, preload : preload, autoplay : autoplay, mute : mute , rate : rate  }""")

internal fun NativeMediaPLayer.createHowlProperties(source: String): HowlerProperties {
    return createHowlProperties(
        source,
        volume,
        html5,
        loop,
        preload,
        autoplay,
        mute,
        rate
    )
}