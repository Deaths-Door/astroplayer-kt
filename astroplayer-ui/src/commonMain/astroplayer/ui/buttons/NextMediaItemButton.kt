package com.deathsdoor.astroplayer.ui.buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.deathsdoor.astroplayer.core.common.hasNextMediaItem
import com.deathsdoor.astroplayer.state.AstroPlayerState

@Composable
fun NextMediaItemButton(
    modifier: Modifier = Modifier,
    state : AstroPlayerState,
    imageVector: ImageVector = Icons.Filled.KeyboardArrowUp,
    enabledColor : Color = MaterialTheme.colorScheme.secondary,
    disabledColor : Color = MaterialTheme.colorScheme .onSurface.copy(alpha = 0.4f)
){
    IconButton(
        modifier = modifier,
        onClick = { state.astroPlayer.seekToNextMediaItem() },
        enabled = state.hasNextMediaItem
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "Seek Forward Button",
            tint = if (state.hasNextMediaItem) enabledColor else disabledColor
        )
    }
}