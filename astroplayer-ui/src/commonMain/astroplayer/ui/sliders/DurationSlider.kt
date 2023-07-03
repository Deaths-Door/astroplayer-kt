package com.deathsdoor.astroplayer.ui.sliders

import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.deathsdoor.astroplayer.state.AstroPlayerState

@Composable
fun DurationSlider(
    modifier: Modifier = Modifier,
    state : AstroPlayerState,
    colors: SliderColors = SliderDefaults.colors()
){
    var newPosition by remember { mutableStateOf(0L) }
    Slider(
        modifier = modifier,
        value = state.currentMediaItemPosition.toFloat(),
        colors = colors,
        valueRange = 0f..(state.currentMediaItemMetadata?.trackLength?.toFloat() ?: 0f),
        onValueChange = {
            newPosition =  it.toLong()
        },
        onValueChangeFinished = {
            state.astroPlayer.seekTo(newPosition)
        },
    )
}