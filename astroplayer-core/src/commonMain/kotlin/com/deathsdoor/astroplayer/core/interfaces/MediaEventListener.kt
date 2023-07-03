package com.deathsdoor.astroplayer.core.interfaces

interface MediaEventListener {
    fun onSeekForward() = Unit
    fun onSeekBackward() = Unit

    fun onSeekToNextMediaItem() = Unit
    fun onSeekToPreviousMediaItem() = Unit

    fun onMediaItemChange() = Unit

    fun onPlaybackPlayed() = Unit
    fun onPlaybackPaused() = Unit

    fun onMediaItemEnd() = Unit
    fun onMediaItemStart() = Unit
}