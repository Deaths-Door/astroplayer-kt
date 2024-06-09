package com.deathsdoor.astroplayer.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.deathsdoor.astroplayer.core.equalizer.EqualizerPresets
import com.deathsdoor.astroplayer.core.equalizer.EqualizerValues
import com.deathsdoor.astroplayer.ui.equalizer.EqualizerGraph
import com.deathsdoor.astroplayer.ui.equalizer.rememberEqualizerGraphState

class SampleUserProvider: PreviewParameterProvider<EqualizerValues> {
    override val values = EqualizerPresets.AllPresets.map { it.value } .asSequence()
}

@Preview(device = "spec:id=reference_desktop,shape=Normal,width=1920,height=1080,unit=dp,dpi=160")
@Composable
internal fun EqualizerGraphPreviews(@PreviewParameter(SampleUserProvider::class) values : EqualizerValues) = MaterialTheme(colorScheme = darkColorScheme()) {
    ElevatedCard(modifier = Modifier
        .fillMaxHeight()
        .width((1920f * 0.34f).dp)) {
        Box {
            val state = rememberEqualizerGraphState(values = values)
            EqualizerGraph(state = state)

            Text(modifier = Modifier.align(Alignment.TopStart),text = "Preset - ${values.identifier.capitalize()}",fontWeight = FontWeight.Bold,style=MaterialTheme.typography.displayLarge)
        }
    }

    /*Card(modifier = Modifier.fillMaxSize()) {
        Card(modifier = Modifier.weight(1f)) {
            val state = rememberEqualizerGraphState(values = values)
            EqualizerGraph(state = state)
        }

        Text(modifier = Modifier,text = values.identifier,fontWeight = FontWeight.Bold,style=MaterialTheme.typography.displayLarge)
    }*/
}