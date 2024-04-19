package com.deathsdoor.astroplayer.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.deathsdoor.astroplayer.core.AstroPlayer

/**
 * This Jetpack Compose function creates and remembers an [AstroPlayerState] object associated with the provided `AstroPlayer` instance.
 *
 * @param astroPlayer The `AstroPlayer` object whose state you want to manage in a composable.
 * @return An `AstroPlayerState` object that provides mutable state properties and listens to changes in the underlying `AstroPlayer` instance.
 *
 * This function uses Jetpack Compose's `remember` composable to ensure that the `AstroPlayerState` object is only created once
 * during the initial composition and then reused for subsequent recompositions as long as the `astroPlayer` reference remains the
 * same. This optimizes performance by avoiding unnecessary recreations of the state object.
 */

@Composable
fun rememberAstroPlayerState(astroPlayer: AstroPlayer) : AstroPlayerState = remember { AstroPlayerState(astroPlayer) }