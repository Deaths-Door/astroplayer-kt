package com.deathsdoor.astroplayer_core.platfromCommonFunctions

import com.deathsdoor.astroplayer_core.AstroPlayer

fun AstroPlayer.seekForwardBy(milliseconds: Long) = seekTo(currentMediaItemPosition + milliseconds)
fun AstroPlayer.seekBackwardBy(milliseconds: Long) = seekTo(currentMediaItemPosition - milliseconds)

fun AstroPlayer.seekToStartOfMediaItem() = seekTo(0)
fun AstroPlayer.seekToEndOfMediaItem() = seekTo(currentMediaItemTrackLength)
