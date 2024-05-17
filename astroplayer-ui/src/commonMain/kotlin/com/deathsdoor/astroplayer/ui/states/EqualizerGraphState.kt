package com.deathsdoor.astroplayer.ui.states

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import com.deathsdoor.astroplayer.core.equalizer.EqualizerValues

/**
 * A class that encapsulates the state for [EqualizerGraph](com.deathsdoor.astroplayer.ui.equalizer.EqualizerGraph)
 */
@Stable
class EqualizerGraphState constructor(values : EqualizerValues) {
    internal val _frequencies = mutableStateMapOf<Int,Float>().apply {
        putAll(values.frequencies)
    }

    /**
     * An internal mutable state list that stores the positions (Offsets) of the slider thumbs.
     * This list is used to draw the connected lines and gradient veil that visualize the equalizer bands.
     *
     * **Important:** The initial value of this list is set to a single `Offset.Zero` element. This is essential
     * for the proper rendering of the graph. Without this initial value, the path connecting the slider thumbs
     * would be missing, leading to a broken visual representation of the equalizer.
     */
    internal val sliderThumbPositions = mutableStateListOf(Offset.Zero)

    // used to undraw the previous thing when moving slider thumb it is there and doesnt remove old paths
  //  internal var previousConnectedThumb by mutableStateOf<Path?>(null)
    //internal var previousGradient by mutableStateOf<Path?>(null)

    /**
     * Returns a copy of the current equalizer settings as a map.
     *
     * This method provides a read-only view of the internal frequency data. Modifying the returned map
     * won't directly affect the state.
     */
    fun currentEqualizerValues(): Map<Int, Float> = _frequencies.toMap()
}