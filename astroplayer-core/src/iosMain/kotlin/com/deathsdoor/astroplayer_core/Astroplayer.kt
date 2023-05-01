package com.deathsdoor.astroplayer_core

import platform.AVFoundation.*
import platform.Foundation.*
import kotlinx.cinterop.*

import com.deathsdoor.astroplayer_core.dataclasses.MediaItem
import com.deathsdoor.astroplayer_core.enums.RepeatMode
import com.deathsdoor.astroplayer_core.equalizer.Equalizer
import com.deathsdoor.astroplayer_core.equalizer.EqualizerValues

actual class AstroPlayer private actual constructor() {
    constructor(avplayer: AVPlayer) : this() {
        this.mediaPlayer = avplayer
    }
    
    private var mediaplayer = AVPlayer.playerWithURL(NSURL())
    
    internal actual val mediaItems : MutableList<MediaItem> = mutableListOf()

    /**
     * Playback
     * */
    actual fun play() = mediaPlayer.play()
    actual fun pause() = mediaPlayer.pause()
    actual fun stop() = mediaPlayer.stop()
    
 
}
