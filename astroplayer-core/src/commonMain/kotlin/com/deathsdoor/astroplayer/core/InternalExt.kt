package com.deathsdoor.astroplayer.core

import com.deathsdoor.astroplayer.core.listeners.AstroListener

internal fun AstroPlayer.forEachListener(action: (AstroListener) -> Unit) = listenersStore.values.forEach(action)