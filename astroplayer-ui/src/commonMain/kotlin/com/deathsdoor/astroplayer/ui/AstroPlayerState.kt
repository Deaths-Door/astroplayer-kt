package com.deathsdoor.astroplayer.ui

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.deathsdoor.astroplayer.core.AstroMediaItem
import com.deathsdoor.astroplayer.core.AstroPlayer
import com.deathsdoor.astroplayer.core.RepeatMode
import com.deathsdoor.astroplayer.core.addListener
import com.deathsdoor.astroplayer.core.equalizer.EqualizerValues
import com.deathsdoor.astroplayer.core.hasNextMediaItem
import com.deathsdoor.astroplayer.core.hasPreviousMediaItem
import com.deathsdoor.astroplayer.core.listeners.AstroListener

/**
 * A class that represents the mutable state of an [AstroPlayer] instance for use with Jetpack Compose.
 *
 * This class wraps an existing [AstroPlayer] object and provides a way to access and modify its properties
 * as mutable state for Jetpack Compose composables. It listens to changes in the underlying `AstroPlayer`
 * and updates the corresponding state properties accordingly.
 *
 * **Note:** This class is a wrapper and doesn't modify the original [AstroPlayer] object directly.
 * It provides a convenient way to manage state within composables while keeping the player logic separate.
 */
@Stable
class AstroPlayerState(val astroPlayer: AstroPlayer) {
    init {
        astroPlayer.addListener(object : AstroListener {
            override fun onPlaybackStarted() {
                this@AstroPlayerState.isPaused = false
                this@AstroPlayerState.isPlaying = true
            }

            override fun onPlaybackPaused() {
                this@AstroPlayerState.isPlaying = false
                this@AstroPlayerState.isPaused = true
            }

            override fun onCurrentMediaItemChanged(mediaItem: AstroMediaItem?) {
                this@AstroPlayerState.currentMediaItem = mediaItem
                this@AstroPlayerState.currentMediaItemIndex = astroPlayer.currentMediaItemIndex

                this@AstroPlayerState.hasNextMediaItem = astroPlayer.hasNextMediaItem
                this@AstroPlayerState.hasPreviousMediaItem = astroPlayer.hasPreviousMediaItem
            }

            override fun onPlaybackSpeedChanged(speed: Float) {
                this@AstroPlayerState.playBackSpeed = speed
            }

            override fun onRepeatModeChanged(repeatMode: RepeatMode) {
                this@AstroPlayerState.repeatMode = repeatMode
            }

            override fun onShuffleModeChanged(shuffleModeEnabled: Boolean) {
                this@AstroPlayerState.shuffleModeEnabled = shuffleModeEnabled
            }

            override fun onVolumeChanged(volume: Float) {
                this@AstroPlayerState.volume = volume
            }

            override fun onMuteStateChanged(isMuted: Boolean) {
                this@AstroPlayerState.isMuted = isMuted
            }

            override fun onEqualizerEnabledChanged(isEqualizerEnabled: Boolean) {
                this@AstroPlayerState.isEqualizerEnabled = isEqualizerEnabled
            }

            override fun onSmartEqualizerEnabledChanged(isSmartEqualizerEnabled: Boolean) {
                this@AstroPlayerState.isSmartEqualizerEnabled = isSmartEqualizerEnabled
            }

            override fun onCurrentEqualizerValuesChanged(currentValues: EqualizerValues?) {
                this@AstroPlayerState.currentEqualizerValues = currentValues
            }
        })
    }

    var isPlaying by mutableStateOf(astroPlayer.isPlaying)
        internal set

    var isPaused by mutableStateOf(astroPlayer.isPaused)
        internal set

    var playBackSpeed by mutableStateOf(astroPlayer.playBackSpeed)
        internal set

    var isMuted by mutableStateOf(astroPlayer.isMuted)
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

    var hasPreviousMediaItem by mutableStateOf(astroPlayer.hasPreviousMediaItem)
        internal set

    var hasNextMediaItem by mutableStateOf(astroPlayer.hasNextMediaItem)
        internal set
}