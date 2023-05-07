package com.deathsdoor.astroplayer

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform