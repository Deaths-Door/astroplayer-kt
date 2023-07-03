package com.deathsdoor.astroplayer.state

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.deathsdoor.astroplayer.core.AstroPlayer

@Composable
actual fun rememberAstroPlayerState(): AstroPlayerState = rememberAstroPlayerState(AstroPlayer(LocalContext.current))