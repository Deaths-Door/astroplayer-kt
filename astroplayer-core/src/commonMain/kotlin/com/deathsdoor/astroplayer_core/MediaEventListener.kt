package com.deathsdoor.astroplayer_core

interface MediaEventListener {
    fun onSeekForward() = Unit
    fun onSeekBackward() = Unit

    fun onSeekToNextMediaItem() = Unit
    fun onSeekToPreviousMediaItem() = Unit

    fun onPlaybackPlayed() = Unit
    fun onPlaybackPaused() = Unit
}