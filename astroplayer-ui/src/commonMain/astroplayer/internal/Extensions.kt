package com.deathsdoor.astroplayer.internal

internal val Long.formatToTime : String get() {
    val seconds = this / 1000
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return "$minutes:${remainingSeconds.toString().padStart(2, '0')}" // "0:05"
}