package com.deathsdoor.astroplayer.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.deathsdoor.astroplayer.core.equalizer.EqualizerPresets
import com.deathsdoor.astroplayer.core.equalizer.EqualizerValues
import com.deathsdoor.astroplayer.ui.equalizer.EqualizerGraph
import com.deathsdoor.astroplayer.ui.equalizer.rememberEqualizerGraphState

class SampleUserProvider: PreviewParameterProvider<EqualizerValues> {
    override val values = EqualizerPresets.AllPresets.map { it.value } .asSequence()
}

@Preview
@Composable
internal fun EqualizerGraphPreviews(@PreviewParameter(SampleUserProvider::class) values : EqualizerValues) = MaterialTheme {
    Surface {
        val state = rememberEqualizerGraphState(values = values)
        EqualizerGraph(state = state)
    }
}