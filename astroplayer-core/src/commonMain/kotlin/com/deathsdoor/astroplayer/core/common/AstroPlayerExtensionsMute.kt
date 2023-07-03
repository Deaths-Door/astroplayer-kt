package com.deathsdoor.astroplayer.core.common

import com.deathsdoor.astroplayer.core.AstroPlayer

val AstroPlayer.isMuted : Boolean get() = volume == 0f

fun AstroPlayer.mute(){
    previousUnMutedVolume = volume
    volume = 0f
}
fun AstroPlayer.unMute(){
    volume = previousUnMutedVolume
}
