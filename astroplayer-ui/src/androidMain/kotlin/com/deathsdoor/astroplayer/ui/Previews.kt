package com.deathsdoor.astroplayer.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.deathsdoor.astroplayer.core.equalizer.EqualizerPresets
import com.deathsdoor.astroplayer.core.equalizer.EqualizerValues

class SampleUserProvider: PreviewParameterProvider<EqualizerValues> {
    override val values = EqualizerPresets.AllPresets.map { it.value } .asSequence()
}

@Preview
@Composable
internal fun EqualizerGraphPreviews(@PreviewParameter(SampleUserProvider::class) value : EqualizerValues) = MaterialTheme {
    Surface {
        EqualizerGraph(value)
    }
}