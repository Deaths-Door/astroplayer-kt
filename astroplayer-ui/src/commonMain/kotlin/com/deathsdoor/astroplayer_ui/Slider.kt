package com.deathsdoor.astroplayer_ui

import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.deathsdoor.astroplayer_core.AstroPlayer
import com.deathsdoor.astroplayer_core.platfromCommonFunctions.currentMediaItemTrackLength

@Composable
fun AstroPlayer.TrackDurationSlider(
    modifier: Modifier = Modifier,
    colors: SliderColors = SliderDefaults.colors(
               thumbColor = Color.White,
               activeTrackColor = Color.White,
               inactiveTrackColor = Color.White.copy(alpha = 0.5f)
           )
){
    Slider(
        modifier = modifier,
        value = currentMediaItemPosition.toFloat(),
        onValueChange = { seekTo(it.toLong()) },
        valueRange = 0f..currentMediaItemTrackLength.toFloat(),
        colors = colors,
    )
}
