package com.deathsdoor.astroplayer.common

import com.deathsdoor.astroplayer.AstroPlayer

val AstroPlayer.isMuted : Boolean get() = volume == 0f

fun AstroPlayer.mute(){
    previousUnMutedVolume = volume
    volume = 0f
}
fun AstroPlayer.unMute(){
    volume = previousUnMutedVolume
}
