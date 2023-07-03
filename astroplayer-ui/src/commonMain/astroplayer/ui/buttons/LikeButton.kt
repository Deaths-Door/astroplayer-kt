package com.deathsdoor.astroplayer.ui.buttons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.deathsdoor.astroplayer.state.AstroPlayerState

@Composable
fun LikeButton(
    modifier: Modifier = Modifier,
    state : AstroPlayerState,
    likedImageVector: ImageVector = Icons.Filled.Favorite,
    disLikedImageVector : ImageVector = Icons.Outlined.FavoriteBorder,
    likedTint : Color = LocalContentColor.current,
    disLikedTint : Color = LocalContentColor.current,
    onLikeStatusChanged : (Boolean) -> Unit
) =
    IconToggleButton(
        modifier = modifier,
        checked = state.currentMediaItemMetadata?.isLiked ?: false,
        onCheckedChange = onLikeStatusChanged,
        content = {
            if (state.currentMediaItemMetadata?.isLiked == true) {
                Icon(
                    imageVector = likedImageVector,
                    contentDescription = "Current Song is Liked",
                    tint = likedTint,
                 //   modifier = Modifier.fillMaxSize()
                )
            } else {
                Icon(
                    imageVector = disLikedImageVector,
                    contentDescription = "Current Song is disliked",
                    tint = disLikedTint,
               //     modifier = Modifier.fillMaxSize()
                )
            }
        }
    )