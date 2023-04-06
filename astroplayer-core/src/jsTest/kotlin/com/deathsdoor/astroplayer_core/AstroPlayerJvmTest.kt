package com.deathsdoor.astroplayer_core

import com.deathsdoor.astroplayer_core.dataclasses.MediaItem
import com.deathsdoor.astroplayer_core.dataclasses.MediaMetadata
import com.deathsdoor.astroplayer_core.platfromCommonFunctions.addMediaItem
import kotlin.test.Test


class AstroPlayerJvmTest{
    private val astroPlayer = AstroPlayer.create()
    @Test
    fun test1(){
        astroPlayer.addMediaItem(MediaItem("","C:\\Users\\Aarav Aditya Shah\\Desktop\\Applaus.wav",MediaItem.MediaSource.File, MediaMetadata(1)))
        while(true){}
    }
}