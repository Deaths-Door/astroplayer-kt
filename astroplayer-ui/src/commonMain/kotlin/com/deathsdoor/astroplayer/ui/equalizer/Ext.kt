package com.deathsdoor.astroplayer.ui.equalizer

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.deathsdoor.astroplayer.ui.states.EqualizerGraphState


/**
 * A modifier extension function that draws a connected graph behind the composable it's applied to.
 *
 * This modifier is intended for use with the `EqualizerGraph` composable to visualize the equalizer
 * bands and their current levels.
 *
 * @param state An [EqualizerGraphState] object that holds the data for the equalizer, including
 *        band frequencies and slider thumb positions.
 * @param color The color to use for the connected lines and gradient veil.
 * @return A new modifier that applies the drawing behavior.
 */
internal fun Modifier.drawConnectedGraph(
    state : EqualizerGraphState,
    color: Color
) = drawBehind {
    drawGradientVeil(
        state = state,
        color = color
    )

    drawConnectedThumbs(
        state = state,
        color = color
    )
}

/**
 * This function draws the connected lines between the slider thumbs in the equalizer graph.
 *
 * It takes an [EqualizerGraphState] object and a color as arguments. It iterates through the
 * `sliderstate.sliderThumbPositions` in the state and connects them with lines using a [Path].
 *
 * @param state An [EqualizerGraphState] object that holds the data for the equalizer.
 * @param color The color to use for the connected lines.
 * @param drawScope The DrawScope object provided by Jetpack Compose for drawing operations.
 */
private fun DrawScope.drawConnectedThumbs(
    state: EqualizerGraphState,
    color : Color
) {
    val connectedThumb = Path().apply {
        reset()

        val (ix,iy) = state.sliderThumbPositions.first().adjustBasedOnThumbCentre(this@drawConnectedThumbs)
        moveTo(ix,iy)

        state.sliderThumbPositions.subList(1,state.sliderThumbPositions.size - 1).forEach {
            val (cx,cy) = it.adjustBasedOnThumbCentre(this@drawConnectedThumbs)
            lineTo(cx,cy)
        }
    }


    // Slider Track Width
    val stokeWidth = 3.dp.toPx()
    val style = Stroke(width = stokeWidth)

    drawPath(
        path = connectedThumb,
        color = color,
        style = style,
        blendMode = BlendMode.SrcOver
    )
}

/**
 * This function draws a gradient veil behind (or under) the connected lines in the equalizer graph.
 *
 * It takes an [EqualizerGraphState] object and a color as arguments. It iterates through the
 * `sliderstate.sliderThumbPositions` in the state and creates a path that defines the shape of the veil.
 * The path starts from the top of the composable and connects to each slider thumb position,
 * then closes the shape at the bottom-right corner. Finally, it uses a vertical gradient brush
 * to fill the path with a color that fades to transparent towards the bottom.
 *
 * @param state An [EqualizerGraphState] object that holds the data for the equalizer.
 * @param color The color to use for the gradient veil.
 * @param drawScope The DrawScope object provided by Jetpack Compose for drawing operations.
 */
private fun DrawScope.drawGradientVeil(
    state : EqualizerGraphState,
    color : Color
) {
    val gradient = Path().apply {
        reset()

        val (ix,iy) = state.sliderThumbPositions.first().adjustBasedOnThumbCentre(this@drawGradientVeil)

        moveTo(ix,size.height)
        lineTo(ix,iy)

        val lastIndex = state.sliderThumbPositions.size - 1
        state.sliderThumbPositions.subList(1,lastIndex).forEach {
            val (cx,cy) = it.adjustBasedOnThumbCentre(this@drawGradientVeil)
            lineTo(cx,cy)
        }

        val fx = (size.width - state.sliderThumbPositions.last().x).adjustXBasedOnThumbCentre(this@drawGradientVeil)
        lineTo(fx,size.height)
        close()
    }

    val brush = Brush.verticalGradient(
        colorStops = arrayOf(
            0.0f to color,
            0.95f to Color.Transparent
        )
    )

    drawPath(
        path = gradient,
        brush = brush
    )
}

/**
 * This helper function adjusts an `Offset` based on the assumed center of the rotated slider thumb.
 *
 * It takes an `Offset` representing the raw position of a slider thumb and a [DrawScope] object as arguments.
 * Since the equalizer graph is rotated vertically and this function assumes a square slider thumb with a specific size
 * (e.g., 20dp x 20dp), hence adjusts the offset by half the width **and height** to position the path at the center of the thumb
 * visually.
 *
 * **Note:** You should not modify this behavior unless you have a specific reason to do so and understand the implications
 * for the visual representation of the slider thumbs.
 *
 * @param drawScope The DrawScope object provided by Jetpack Compose for drawing operations.
 * @return A new `Offset` object adjusted based on the assumed center of the rotated slider thumb.
 */
private fun Offset.adjustBasedOnThumbCentre(
    drawScope: DrawScope
): Offset  {
    // As ThumbSize == 20dp * 20dp
    val offsetCorrection = with(drawScope) { 10.dp.toPx() }
    return this.copy(this.x + offsetCorrection,this.y - offsetCorrection)
}

private fun Float.adjustXBasedOnThumbCentre(
    drawScope: DrawScope
): Float = this + with(drawScope) { 20.dp.toPx() }