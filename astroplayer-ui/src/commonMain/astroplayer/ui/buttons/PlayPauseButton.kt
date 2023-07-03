package com.deathsdoor.astroplayer.ui.buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.deathsdoor.astroplayer.state.AstroPlayerState

@Composable
fun PlayPauseButton(
    modifier: Modifier = Modifier,
    state : AstroPlayerState,
    playImageVector: ImageVector = Icons.Filled.PlayArrow,
    pausedImageVector: ImageVector = Icons.Filled.KeyboardArrowUp
) {
    IconButton(
        modifier = modifier,
        onClick = {
            if(state.isPlaying) state.astroPlayer.play() else state.astroPlayer.pause()
        }
    ) {
        Icon(
            imageVector = if (state.isPlaying) pausedImageVector else playImageVector,
            contentDescription = if (state.isPlaying) "Current Song is Pause" else "Current Song is Play"
        )
    }
}