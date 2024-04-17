package com.deathsdoor.astroplayer.core

@JsModule("howler")
external class Howl constructor(properties: HowlerProperties) : HowlerProperties {
    @JsName("unload")
    fun release()
    fun play() : JsNumber
    fun pause()
    @JsName("stop")
    fun stopAndReset()

    override var sources: JsArray<JsString>
    override var volume: Float
    override var html5: Boolean
    override var loop: Boolean
    override var preload: JsAny
    override var autoplay: Boolean
    override var mute: Boolean
    override var rate: JsNumber

    var seek : JsNumber
    @JsName("playing")
    fun isAnyAudioPlaying() : Boolean
    fun duration() : JsNumber

    // unloaded / loading / loaded
    fun state() : JsString

    fun unload()

    fun on(event: String, function: (id: JsString) -> Unit)
}