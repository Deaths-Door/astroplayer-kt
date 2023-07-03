package com.deathsdoor.astroplayer.core.common

import com.deathsdoor.astroplayer.core.AstroPlayer

fun AstroPlayer.seekForwardBy(milliseconds: Long) = seekTo(currentMediaItemPosition + milliseconds)
fun AstroPlayer.seekBackwardBy(milliseconds: Long) = seekTo(currentMediaItemPosition - milliseconds)

fun AstroPlayer.seekToStartOfMediaItem() = seekTo(0)
fun AstroPlayer.seekToEndOfMediaItem() = seekTo(currentMediaItemTrackLength)