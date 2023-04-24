package com.deathsdoor.astroplayer_core

@ExperimentalMultiplatform
interface MediaEventListener {
    fun onSeekForward() = Unit
    fun onSeekBackward() = Unit

    fun onSeekToNextMediaItem() = Unit
    fun onSeekToPreviousMediaItem() = Unit

    fun onPlaybackPlayed() = Unit
    fun onPlaybackPaused() = Unit
}