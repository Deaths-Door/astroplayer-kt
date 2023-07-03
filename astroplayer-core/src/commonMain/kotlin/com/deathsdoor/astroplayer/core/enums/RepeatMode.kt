package com.deathsdoor.astroplayer.core.enums
sealed class RepeatMode {
    object Auf : RepeatMode()
    data class Current(val mediaItemIndex : Int) : RepeatMode()
    object Alle : RepeatMode()
    data class Group(val from : Int,val to : Int) : RepeatMode()
}