package com.deathsdoor.astroplayer.ui.text

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.deathsdoor.astroplayer.state.AstroPlayerState

@Composable
fun TrackArtists(
    modifier: Modifier = Modifier,
    state : AstroPlayerState,
    color: Color = MaterialTheme.colorScheme.inversePrimary,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    style: TextStyle = LocalTextStyle.current
){
    Text(
        text = state.currentMediaItem?.metadata?.artist?.joinToString(separator = ",") { it } ?: "Not Given Artists",
        modifier = modifier,
        maxLines = 1,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        style = style
    )
}