package com.deathsdoor.astroplayer.ui.sliders

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.deathsdoor.astroplayer.internal.formatToTime
import com.deathsdoor.astroplayer.state.AstroPlayerState

@Composable
fun TrackLength(
    modifier: Modifier = Modifier,
    state : AstroPlayerState,
    color: Color = MaterialTheme.colorScheme.inversePrimary,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
){
    Text(
        text = state.currentMediaItemMetadata?.trackLength?.formatToTime ?: "0:00",
        modifier = modifier,
        maxLines = 1,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily
    )
}