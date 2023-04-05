package com.deathsdoor.astroplayer_core

actual class AstroPlayer private actual constructor() {
    actual val play: Unit = Unit
    actual val pause: Unit = Unit
    actual val stop: Unit = Unit
    actual val isPlaying: Boolean = true
}