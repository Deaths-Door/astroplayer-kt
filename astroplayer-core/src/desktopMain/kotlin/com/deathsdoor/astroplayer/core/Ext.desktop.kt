package com.deathsdoor.astroplayer.core

import com.deathsdoor.astroplayer.core.RepeatMode.*
import uk.co.caprica.vlcj.player.list.PlaybackMode

internal fun RepeatMode.toPlaybackMode() : PlaybackMode {
    return when(this) {
        Off -> PlaybackMode.DEFAULT
        All -> PlaybackMode.LOOP
        One -> PlaybackMode.LOOP
    }
}