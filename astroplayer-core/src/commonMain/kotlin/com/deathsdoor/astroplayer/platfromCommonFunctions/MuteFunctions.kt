package com.deathsdoor.astroplayer.platfromCommonFunctions

import com.deathsdoor.astroplayer.AstroPlayer


fun AstroPlayer.mute(){
    previousUnMutedVolume = volume
    volume = 0f
}
fun AstroPlayer.unMute(){
    volume = previousUnMutedVolume
}
val AstroPlayer.isMuted : Boolean get() = volume == 0f
