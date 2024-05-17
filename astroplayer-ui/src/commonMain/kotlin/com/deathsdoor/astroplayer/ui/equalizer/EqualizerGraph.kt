package com.deathsdoor.astroplayer.ui.equalizer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.deathsdoor.astroplayer.core.equalizer.EqualizerValues
import com.deathsdoor.astroplayer.ui.states.EqualizerGraphState

/**
 * A composable that represents the entire equalizer graph with multiple sliders.
 *
 *
 * @param state An [EqualizerGraphState] object that holds the data for the equalizer.
 * @param slider A composable function that takes two arguments:
 *        * `bandLevel`: The index of the equalizer band (key in the `frequencies` map of the state).
 *        * `frequency`: The current frequency value (dB) for the band.
 *        This function is expected to render a single slider for the specified band.
 *        **It's recommended to use the `EqualizerGraphSlider` function for this purpose.**
 * @param modifier Additional modifier to apply to the entire equalizer graph.
 * @param enabled Whether the equalizer is enabled or disabled. This affects the color of the sliders.
 * @param colors The color scheme to use for the sliders.
 *
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EqualizerGraph(
    state : EqualizerGraphState,
    slider : @Composable (bandLevel : Int,frequency : Float) -> Unit = { bandLevel, _ ->
         EqualizerGraphSlider(
             state = state,
             bandLevel = bandLevel,
         )
    },
    modifier : Modifier = Modifier,
    enabled : Boolean = true,
    colors: SliderColors = SliderDefaults.colors(),
) {
    val color = if(enabled) colors.thumbColor else colors.disabledThumbColor

    Row(
        modifier = modifier.drawConnectedGraph(
            state = state,
            color = color
        ),
        content = {
            state._frequencies.forEach {
                slider(it.key,it.value)
            }
        }
    )
}

/**
* Creates and remembers an instance of [EqualizerGraphState] based on the provided [EqualizerValues].
 *
 * */
@Composable
fun rememberEqualizerGraphState(values : EqualizerValues) = remember(values) {
    EqualizerGraphState(values)
}

/*
// inspiration from https://medium.com/@kheldiente/how-to-recreate-spotifys-equalizer-for-android-4c31b2ecd973
@Deprecated("do not use this")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EqualizerGraph(
    // unchanged by function
    equalizerValues: EqualizerValues,
    modifier : Modifier = Modifier,
    enabled : Boolean = true,
    colors: SliderColors = SliderDefaults.colors(),
) {
    val frequencies = remember(equalizerValues) {
        mutableStateMapOf<Int,Float>().apply {
            putAll(equalizerValues.frequencies)
        }
    }

    val thumbPositions = remember { mutableStateListOf(Offset(0f,0f)) }

    Row(
        // THis is done so that padding.start can be used
        modifier = Modifier.drawConnectedGraph(
            thumbPositions = thumbPositions,
            color = colors.awareThumbColor(enabled = enabled)
        ),
        content = {
            val verticalSliderModifier = Modifier.graphicsLayer {
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

            frequencies.entries.forEachIndexed { index, (bandLevel,frequencyValue) ->
                val interactionSource = remember { MutableInteractionSource() }

                Slider(
                    modifier = verticalSliderModifier,
                    value = frequencyValue,
                    onValueChange = { frequencies[bandLevel] = frequencyValue },
                    onValueChangeFinished = { TODO("use this to draw connects , shadows colors etc") },
                    enabled = enabled,
                    colors = colors,
                    interactionSource = interactionSource,
                    thumb = {
                        Box(
                            modifier = Modifier.onPlaced {
                                thumbPositions.add(index,it.positionInWindow())
                            },
                            content = {
                                SliderDefaults.Thumb(
                                    interactionSource = interactionSource,
                                    colors = colors,
                                    enabled = enabled
                                )
                            }
                        )
                    }
                )
            }
        }
    )
}

private fun SliderColors.awareThumbColor(enabled: Boolean) = if(enabled) thumbColor else disabledThumbColor

private fun Modifier.drawConnectedGraph(
    thumbPositions : SnapshotStateList<Offset>,
    color: Color
): Modifier = drawBehind {
    val (gradient,brush) = createGradientVeil(
        thumbPositions = thumbPositions,
        color = color,
    )

    val (connectedThumbs,style) = createConnectedThumbs(thumbPositions = thumbPositions)

    drawPath(
        path = gradient,
        brush = brush
    )

    drawPath(
        path = connectedThumbs,
        color = color,
        style = style,
    )
}

// the adjust* adjusted the values so that they include the thumb.centre correctly instead of its bounds
private fun Float.adjustYComponent(drawScope: DrawScope) = this - with(drawScope) { 10.dp.toPx() }
private fun Float.adjustXComponent(drawScope: DrawScope) = this + with(drawScope) { 10.dp.toPx() }
private fun Offset.adjustBasedOnThumbCentre(drawScope: DrawScope): Offset {
    // As ThumbSize == 20dp * 20dp
    val offsetCorrection = with(drawScope) { 10.dp.toPx() }
    return this.copy(this.x + offsetCorrection,this.y - offsetCorrection)
}

private fun DrawScope.createConnectedThumbs(thumbPositions: SnapshotStateList<Offset>): Pair<Path, Stroke> {
    val connectedPoints = Path().apply {
        val (ix,iy) = thumbPositions.first().adjustBasedOnThumbCentre(this@createConnectedThumbs)
        moveTo(ix,iy)

        thumbPositions.subList(1,thumbPositions.size - 1).forEach {
            val (cx,cy) = it.adjustBasedOnThumbCentre(this@createConnectedThumbs)
            lineTo(cx,cy)
        }
    }

    // Slider Track Width
    val stokeWidth = 3.dp.toPx()
    val style = Stroke(width = stokeWidth)

    return connectedPoints to style
}

private fun DrawScope.createGradientVeil(
    thumbPositions : SnapshotStateList<Offset>,
    color: Color,
): Pair<Path, Brush> {
    val background = Path().apply {
        val (ix,iy) = thumbPositions.first().adjustBasedOnThumbCentre(this@createGradientVeil)

        moveTo(ix,size.height)
        lineTo(ix,iy)

        val lastIndex = thumbPositions.size - 1
        thumbPositions.subList(1,lastIndex).forEach {
            val (cx,cy) = it.adjustBasedOnThumbCentre(this@createGradientVeil)
            lineTo(cx,cy)
        }

        val fx = (size.width - thumbPositions.last().x)
        lineTo(fx,size.height)
        close()
    }

    val brush = Brush.verticalGradient(
        colorStops = arrayOf(
            0.0f to color,
            0.95f to Color.Transparent
        )
    )

    return background to brush
}*/