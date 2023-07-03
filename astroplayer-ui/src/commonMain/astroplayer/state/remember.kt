package com.deathsdoor.astroplayer.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import com.deathsdoor.astroplayer.core.AstroPlayer
import com.deathsdoor.astroplayer.core.common.currentMediaItem
import com.deathsdoor.astroplayer.core.common.currentMediaItemMetadata
import com.deathsdoor.astroplayer.core.common.hasNextMediaItem
import com.deathsdoor.astroplayer.core.common.hasPreviousMediaItem


@Composable
expect fun rememberAstroPlayerState() : AstroPlayerState

@Composable
fun rememberAstroPlayerState(astroPlayer: AstroPlayer) : AstroPlayerState {
    return remember { AstroPlayerState(astroPlayer) }.apply {
        produceState(initialValue = astroPlayer.currentMediaItem, key1 = astroPlayer.currentMediaItem){
            currentMediaItem = astroPlayer.currentMediaItem
        }

        produceState(initialValue = astroPlayer.currentMediaItemMetadata, key1 = astroPlayer.currentMediaItemMetadata){
            currentMediaItemMetadata = astroPlayer.currentMediaItemMetadata
        }
        /*produceState(initialValue = astroPlayer.isPlaying, key1 = astroPlayer.isPlaying){
            isPlaying = astroPlayer.isPlaying
        }*/

     /*
        LaunchedEffect(astroPlayer.isPlaying) {
            isPlaying = astroPlayer.isPlaying
        }

        LaunchedEffect(astroPlayer.isPaused) {
            isPaused = astroPlayer.isPaused
        }

        LaunchedEffect(astroPlayer.playBackSpeed) {
            playBackSpeed = astroPlayer.playBackSpeed
        }

        LaunchedEffect(astroPlayer.volume) {
            volume = astroPlayer.volume
        }

        LaunchedEffect(astroPlayer.isEqualizerEnabled) {
            isEqualizerEnabled = astroPlayer.isEqualizerEnabled
        }

        LaunchedEffect(astroPlayer.isSmartEqualizerEnabled) {
            isSmartEqualizerEnabled = astroPlayer.isSmartEqualizerEnabled
        }

        LaunchedEffect(astroPlayer.currentEqualizerValues) {
            currentEqualizerValues = astroPlayer.currentEqualizerValues
        }

        LaunchedEffect(astroPlayer.repeatMode) {
            repeatMode = astroPlayer.repeatMode
        }

        LaunchedEffect(astroPlayer.shuffleModeEnabled) {
            shuffleModeEnabled = astroPlayer.shuffleModeEnabled
        }

        LaunchedEffect(astroPlayer.currentMediaItem) {
            currentMediaItem = astroPlayer.currentMediaItem
        }

        LaunchedEffect(astroPlayer.currentMediaItemIndex) {
            currentMediaItemIndex = astroPlayer.currentMediaItemIndex
        }

        LaunchedEffect(astroPlayer.currentMediaItemPosition) {
            currentMediaItemPosition = astroPlayer.currentMediaItemPosition
        }

        LaunchedEffect(astroPlayer.currentMediaItemMetadata) {
            currentMediaItemMetadata = astroPlayer.currentMediaItemMetadata
        }

        LaunchedEffect(astroPlayer.hasNextMediaItem) {
            hasNextMediaItem = astroPlayer.hasNextMediaItem
        }

        LaunchedEffect(astroPlayer.hasPreviousMediaItem) {
            hasPreviousMediaItem = astroPlayer.hasPreviousMediaItem
        }*/
    }
}