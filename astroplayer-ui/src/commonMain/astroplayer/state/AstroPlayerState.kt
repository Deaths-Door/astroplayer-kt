package com.deathsdoor.astroplayer.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.deathsdoor.astroplayer.core.AstroPlayer
import com.deathsdoor.astroplayer.core.common.currentMediaItem
import com.deathsdoor.astroplayer.core.common.currentMediaItemMetadata
import com.deathsdoor.astroplayer.core.common.hasNextMediaItem
import com.deathsdoor.astroplayer.core.common.hasPreviousMediaItem

class AstroPlayerState(val astroPlayer: AstroPlayer) {
    var isPlaying by mutableStateOf(astroPlayer.isPlaying)
        internal set

    var isPaused by mutableStateOf(astroPlayer.isPaused)
        internal set

    var playBackSpeed by mutableStateOf(astroPlayer.playBackSpeed)
        internal set

    var volume by mutableStateOf(astroPlayer.volume)
        internal set

    var isEqualizerEnabled by mutableStateOf(astroPlayer.isEqualizerEnabled)
        internal set

    var isSmartEqualizerEnabled by mutableStateOf(astroPlayer.isSmartEqualizerEnabled)
        internal set

    var currentEqualizerValues by mutableStateOf(astroPlayer.currentEqualizerValues)
        internal set

    var repeatMode by mutableStateOf(astroPlayer.repeatMode)
        internal set

    var shuffleModeEnabled by mutableStateOf(astroPlayer.shuffleModeEnabled)
        internal set

    var currentMediaItem by mutableStateOf(astroPlayer.currentMediaItem)
        internal set

    var currentMediaItemIndex by mutableStateOf(astroPlayer.currentMediaItemIndex)
        internal set

    var currentMediaItemPosition by mutableStateOf(astroPlayer.currentMediaItemPosition)
        internal set

    var currentMediaItemMetadata by mutableStateOf(astroPlayer.currentMediaItemMetadata)
        internal set

    var hasNextMediaItem by mutableStateOf(astroPlayer.hasNextMediaItem)
        internal set

    var hasPreviousMediaItem by mutableStateOf(astroPlayer.hasPreviousMediaItem)
        internal set
}