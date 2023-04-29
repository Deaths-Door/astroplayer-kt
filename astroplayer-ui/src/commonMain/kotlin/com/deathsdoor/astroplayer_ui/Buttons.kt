package com.deathsdoor.astroplayer_ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.deathsdoor.astroplayer_core.AstroPlayer
import com.deathsdoor.astroplayer_core.platfromCommonFunctions.currentMediaItem
import com.deathsdoor.astroplayer_core.platfromCommonFunctions.hasNextMediaItem
import com.deathsdoor.astroplayer_core.platfromCommonFunctions.hasPreviousMediaItem

//TODO add custom image vectors
@Composable
fun AstroPlayer.LikeButton(
    modifier: Modifier = Modifier,
    likedImageVector: ImageVector = Icons.Filled.Favorite,
    disLikedImageVector : ImageVector = Icons.Outlined.FavoriteBorder,
    onLike:() -> Unit,
    onDislike:() -> Unit
) {
    IconToggleButton(
        modifier = modifier,
        checked = currentMediaItem.metadata.isLiked,
        onCheckedChange = {
            currentMediaItem.metadata.isLiked = it
            if (currentMediaItem.metadata.isLiked) onLike()
            else onDislike()
        }
    ){
        if (currentMediaItem.metadata.isLiked) {
            Icon(
                imageVector = likedImageVector,
                contentDescription = "Current Song is Liked",
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Icon(
                imageVector = disLikedImageVector,
                contentDescription = "Current Song is disliked",
                tint = MaterialTheme.colorScheme.onError,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun AstroPlayer.PlayPauseButton(
    modifier: Modifier = Modifier,
    playImageVector: ImageVector = Icons.Filled.PlayArrow,
    pausedImageVector: ImageVector = Icons.Filled.KeyboardArrowUp
) {
    IconButton(
        modifier = modifier,
        onClick = {
            if(isPlaying) play() else pause()
        }
    ) {
        Icon(
            imageVector = if (isPlaying) pausedImageVector else playImageVector,
            contentDescription = if (isPlaying) "Current Song is Pause" else "Current Song is Play"
        )
    }
}

@Composable
fun AstroPlayer.ToNextMediaItemButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.Filled.KeyboardArrowUp,
    enabledColor : Color = MaterialTheme.colorScheme.secondary,
    disabledColor : Color = MaterialTheme.colorScheme .onSurface.copy(alpha = 0.4f)
){
    IconButton(
        modifier = modifier,
        onClick = {},
        enabled = hasNextMediaItem
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "Seek Forward Button",
            tint = if (hasNextMediaItem) enabledColor else disabledColor
        )
    }
}

@Composable
fun AstroPlayer.ToPreviousMediaItemButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.Filled.KeyboardArrowUp,
    enabledColor : Color = MaterialTheme.colorScheme.secondary,
    disabledColor : Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
) {
    IconButton(
        modifier = modifier,
        onClick = {},
        enabled = hasPreviousMediaItem
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "Seek Backwards Button",
            tint = if (hasPreviousMediaItem) enabledColor else disabledColor
        )
    }
}

@Composable
fun AstroPlayer.TrackArtwork(modifier: Modifier = Modifier) {
    //TODO load image from metadata
}