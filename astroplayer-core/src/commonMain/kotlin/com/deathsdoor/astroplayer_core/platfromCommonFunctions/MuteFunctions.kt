package com.deathsdoor.astroplayer_core.platfromCommonFunctions

import com.deathsdoor.astroplayer_core.AstroPlayer


fun AstroPlayer.mute(){
    previousUnMutedVolume = volume
    volume = 0f
}
fun AstroPlayer.unMute(){
    volume = previousUnMutedVolume
}
val AstroPlayer.isMuted : Boolean get() = volume == 0f
