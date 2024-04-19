package com.deathsdoor.astroplayer.ui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.deathsdoor.astroplayer.core.seekToNextMediaItem
import com.deathsdoor.astroplayer.core.seekToPreviousMediaItem
import com.deathsdoor.astroplayer.ui.icons.Pause
import com.deathsdoor.astroplayer.ui.icons.SkipNext
import com.deathsdoor.astroplayer.ui.icons.SkipPrevious

/**
 * A composable function that renders a Play button with customizable appearance and behavior.
 *
 * This function is a wrapper around the standard `IconButton` composable with additional properties
 * specifically designed for a play button.
 *
 * @param modifier: Modifier to apply to the button (optional)
 * @param onClick: Function to be called when the button is clicked (required)
 * @param imageVector: The vector asset to be used as the button icon (default: Icons.Filled.PlayArrow)
 * @param contentDescription: A content description for accessibility (default: "Current Song is Playing")
 * @param enabled: Boolean indicating whether the button is clickable (default: true)
 * @param colors: Colors to be applied to the button (default: IconButtonDefaults.iconButtonColors())
 * @param interactionSource: A [MutableInteractionSource] object to provide information about user interactions (default: a new instance)
 *
 * This function uses `IconButton` internally to create a clickable button with the specified icon and content description.
 * Clicking the button triggers the `onClick` function.
 */
@NonRestartableComposable
@Composable
fun PlayButton(
    modifier : Modifier = Modifier,
    onClick : () -> Unit,
    imageVector : ImageVector =  Icons.Filled.PlayArrow,
    contentDescription : String= "Current Song is Playing",
    enabled: Boolean = true,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) = IconButton(
    modifier = modifier,
    enabled = enabled,
    colors = colors,
    interactionSource = interactionSource,
    onClick = onClick,
    content  = {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
)

/**
 * A composable function that renders a Pause button with customizable appearance and behavior.
 *
 * This function is similar to `PlayButton` but displays a pause icon.
 *
 * @param modifier: Modifier to apply to the button (optional)
 * @param onClick: Function to be called when the button is clicked (required)
 * @param imageVector: The vector asset to be used as the button icon (default: Icons.Outlined.Pause)
 * @param contentDescription: A content description for accessibility (default: "Current Song is Paused")
 * @param enabled: Boolean indicating whether the button is clickable (default: true)
 * @param colors: Colors to be applied to the button (default: IconButtonDefaults.iconButtonColors())
 * @param interactionSource: A [MutableInteractionSource] object to provide information about user interactions (default: a new instance)
 */

@NonRestartableComposable
@Composable
fun PauseButton(
    modifier : Modifier = Modifier,
    onClick : () -> Unit,
    imageVector : ImageVector =  Icons.Outlined.Pause,
    contentDescription : String= "Current Song is Paused",
    enabled: Boolean = true,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) = IconButton(
    modifier = modifier,
    enabled = enabled,
    colors = colors,
    interactionSource = interactionSource,
    onClick = onClick,
    content  = {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
)

/**
 * A composable function that renders a button to seek to the previous media item in a player.
 *
 * This function is a wrapper around the standard `IconButton` composable with logic to handle enabling/disabling
 * based on the availability of a previous media item in the `AstroPlayerState`.
 *
 * @param astroPlayerState: An `AstroPlayerState` object that provides access to player state and information.
 * @param modifier: Modifier to apply to the button (optional)
 * @param onClick: Function to be called when the button is clicked (default: calls seekToPreviousMediaItem on astroPlayer)
 * @param imageVector: The vector asset to be used as the button icon (default: Icons.Outlined.SkipPrevious)
 * @param contentDescription: A content description for accessibility (default: "Seek to previous MediaItem")
 * @param enabled: Boolean indicating whether the button is clickable (default: true)
 * @param colors: Colors to be applied to the button (default: IconButtonDefaults.iconButtonColors())
 * @param interactionSource: A [MutableInteractionSource] object to provide information about user interactions (default: a new instance)
 *
 * This function checks if there's a previous media item using `astroPlayerState.hasPreviousMediaItem` before enabling the button.
 * Clicking the button triggers the provided `onClick` function (default: seeks to the previous media item).
 */

@Composable
fun PreviousMediaItemButton(
    astroPlayerState: AstroPlayerState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { astroPlayerState.astroPlayer.seekToPreviousMediaItem() },
    imageVector: ImageVector = Icons.Outlined.SkipPrevious,
    contentDescription: String = "Seek to previous MediaItem",
    enabled: Boolean = true,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) = IconButton(
    modifier = modifier,
    enabled = if(astroPlayerState.hasPreviousMediaItem) enabled else false,
    colors = colors,
    interactionSource = interactionSource,
    onClick = onClick,
    content  = {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
)

/**
 * A composable function that renders a button to seek to the next media item in a player.
 *
 * This function is a wrapper around the standard `IconButton` composable with logic to handle enabling/disabling
 * based on the availability of a next media item in the `AstroPlayerState`.
 *
 * @param astroPlayerState: An `AstroPlayerState` object that provides access to player state and information.
 * @param modifier: Modifier to apply to the button (optional)
 * @param onClick: Function to be called when the button is clicked (default: calls seekToNextMediaItem on astroPlayer)
 * @param imageVector: The vector asset to be used as the button icon (default: Icons.Outlined.SkipNext)
 * @param contentDescription: A content description for accessibility (default: "Seek to next MediaItem")
 * @param enabled: Boolean indicating whether the button is clickable (default: true)
 * @param colors: Colors to be applied to the button (default: IconButtonDefaults.iconButtonColors())
 * @param interactionSource: A [MutableInteractionSource] object to provide information about user interactions (default: a new instance)
 *
 * This function checks if there's a next media item using `astroPlayerState.hasNextMediaItem` before enabling the button.
 * Clicking the button triggers the provided `onClick` function (default: seeks to the next media item).
 */

@Composable
fun NextMediaItemButton(
    astroPlayerState: AstroPlayerState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { astroPlayerState.astroPlayer.seekToNextMediaItem() },
    imageVector: ImageVector = Icons.Outlined.SkipNext,
    contentDescription: String = "Seek to next MediaItem",
    enabled: Boolean = true,
    colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) = IconButton(
    modifier = modifier,
    enabled = if(astroPlayerState.hasNextMediaItem) enabled else false,
    colors = colors,
    interactionSource = interactionSource,
    onClick = onClick,
    content  = {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
)