package com.deathsdoor.astroplayer.core

import androidx.media3.common.Player
import androidx.media3.common.Player.REPEAT_MODE_ALL
import androidx.media3.common.Player.REPEAT_MODE_OFF
import androidx.media3.common.Player.REPEAT_MODE_ONE

internal fun RepeatMode.toPlayerRepeatMode() : @Player.RepeatMode Int = when(this) {
    RepeatMode.Off -> REPEAT_MODE_OFF
    RepeatMode.One -> REPEAT_MODE_ONE
    RepeatMode.All -> REPEAT_MODE_ALL
}

internal fun (@Player.RepeatMode Int).toRepeatMode() = when(this) {
    REPEAT_MODE_OFF -> RepeatMode.Off
    REPEAT_MODE_ONE -> RepeatMode.One
    REPEAT_MODE_ALL -> RepeatMode.All
    else -> error("Unreachable")
}