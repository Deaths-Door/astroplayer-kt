package com.deathsdoor.astroplayer.testing.ui

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.coroutines.delay

@Composable
internal fun AstroButton(
    text : String,
    onClick : () -> Unit
) = Button(onClick = onClick) {
    Text(text)
}

@Composable
internal fun <T> AstroValue(text : String, value : T) {
    AstroButton("$text - $value") {}
}

@Composable
internal fun <T> AstroValueWithProduceState(text : String, value : () ->  T) {
    val state by produceState(initialValue = value()) {
        // https://stackoverflow.com/a/76331112/20243803
        while(true) {
            this.value = value()
            delay(1000L)
        }
    }

    AstroButton("$text - $state") {}
}

@Composable
internal fun AstroInput(label : String, then : (value : String) -> Unit) {
    var value by remember { mutableStateOf("") }

    TextField(
        value = value,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { value = it.trim().replace("\n","").replace("\r","");if(value.isNotEmpty()) then(value) }
    )
}