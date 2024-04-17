package com.deathsdoor.astroplayer.core

external interface HowlerProperties {
    @JsName("src")
    var sources: JsArray<JsString>

    var volume: Float
    var html5: Boolean
    var loop: Boolean

    // string or boolean
    var preload: JsAny

    var autoplay: Boolean
    var mute: Boolean
    var rate: JsNumber

    fun on()
}