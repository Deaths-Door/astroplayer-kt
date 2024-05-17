package com.deathsdoor.astroplayer.ui.equalizer

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.Constraints
import com.deathsdoor.astroplayer.ui.states.EqualizerGraphState


/**
 * A composable that represents a single slider for an equalizer band.
 *
 * This slider is designed to be rotated vertically and provides callbacks for both value changes
 * and thumb position changes.
 *
 * @param state An [EqualizerGraphState] object that holds the data for the equalizer.
 * @param bandLevel The index of the equalizer band that this slider represents. This value must be
 *        present in the `frequencies` list of the provided `state`.
 * @param modifier Additional modifier to apply to the slider.
 * @param enabled Whether the slider is enabled or disabled.
 * @param colors The color scheme to use for the slider.
 * @param onValueChangeFinished A callback that will be called when the user finishes changing
 *                              the slider value (currently not implemented).
 * @param interactionSource The interaction source for the slider.
 * @param thumb A composable that represents the thumb of the slider. By default, it uses
 *             [SliderDefaults.Thumb].
 * @param track A composable that represents the track of the slider. By default, it uses
 *             [SliderDefaults.Track].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EqualizerGraphSlider(
    state : EqualizerGraphState,
    bandLevel : Int,
    modifier : Modifier = Modifier,
    enabled : Boolean = true,
    colors: SliderColors = SliderDefaults.colors(),
    onValueChangeFinished: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    thumb: @Composable (SliderState) -> Unit = {
        SliderDefaults.Thumb(
            interactionSource = interactionSource,
            colors = colors,
            enabled = enabled
        )
    },
    track: @Composable (SliderState) -> Unit = { sliderState ->
        SliderDefaults.Track(
            colors = colors,
            enabled = enabled,
            sliderState = sliderState
        )
    },
) = EqualizerGraphSlider(
    value = state._frequencies[bandLevel]!!,
    onValueChange = {
        state._frequencies[bandLevel] = it
    },
    onThumbPositionChange = { coordinates ->
        // TODO : check if this works correctly
        val index = state._frequencies.toMap().keys.indexOfFirst { key -> key == bandLevel }
        state.sliderThumbPositions.add(index,coordinates)
    },
    modifier = modifier,
    enabled = enabled,
    colors = colors,
    onValueChangeFinished = onValueChangeFinished,
    thumb = thumb,
    track = track,
)

/**
 * A composable that represents a single slider for an equalizer band.
 *
 * This slider is designed to be rotated vertically and provides callbacks for both value changes
 * and thumb position changes.
 *
 * @param modifier Additional modifier to apply to the slider.
 * @param value The current value of the slider (typically in dB).
 * @param onValueChange A callback that will be invoked when the slider value changes.
 * @param onThumbPositionChange A callback that will be invoked when the thumb position changes.
 * @param enabled Whether the slider is enabled or disabled.
 * @param colors The color scheme to use for the slider.
 * @param onValueChangeFinished A callback that will be called when the user finishes changing
 *                              the slider value (currently not implemented).
 * @param interactionSource The interaction source for the slider.
 * @param thumb A composable that represents the thumb of the slider. By default, it uses
 *             [SliderDefaults.Thumb].
 * @param track A composable that represents the track of the slider. By default, it uses
 *             [SliderDefaults.Track].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EqualizerGraphSlider(
    modifier : Modifier = Modifier,
    value : Float,
    onValueChange : (Float) -> Unit,
    onThumbPositionChange : (Offset) -> Unit,
    enabled : Boolean = true,
    colors: SliderColors = SliderDefaults.colors(),
    onValueChangeFinished: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    thumb: @Composable (SliderState) -> Unit = {
        SliderDefaults.Thumb(
            interactionSource = interactionSource,
            colors = colors,
            enabled = enabled
        )
    },
    track: @Composable (SliderState) -> Unit = { sliderState ->
        SliderDefaults.Track(
            colors = colors,
            enabled = enabled,
            sliderState = sliderState
        )
    },
) {
    Slider(
        modifier = Modifier.rotateVertically() then modifier,
        value = value,
        onValueChange = onValueChange,
        onValueChangeFinished = onValueChangeFinished,
        enabled = enabled,
        colors = colors,
        interactionSource = interactionSource,
        track = track,
        thumb = {
            Box(
                modifier = Modifier.onPlaced {
                    onThumbPositionChange(it.positionInWindow())
                },
                content = { thumb(it) }
            )
        }
    )
}

// https://stackoverflow.com/a/71129399/20243803
private fun Modifier.rotateVertically() = graphicsLayer {
    rotationZ = 270f
    transformOrigin = TransformOrigin(0f, 0f)
}.layout { measurable, constraints ->
    val placeable = measurable.measure(
        Constraints(
            minWidth = constraints.minHeight,
            maxWidth = constraints.maxHeight,
            minHeight = constraints.minWidth,
            maxHeight = constraints.maxWidth,
        )
    )
    layout(placeable.height, placeable.width) {
        placeable.place(-placeable.width, 0)
    }
}