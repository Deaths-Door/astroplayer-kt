package com.deathsdoor.astroplayer_core
//TODO requires downloading of vlc
//TODO remvoe thsi use javax.sound.sampled instead
actual class AstroPlayer private actual constructor(){
//    private var mediaPlayer: MediaPlayer
    actual val play: Unit = Unit
    actual val pause: Unit = Unit
    actual val stop: Unit = Unit
    actual val isPlaying: Boolean = true
   /* init {
        NativeDiscovery().discover()
        mediaPlayer = CallbackMediaPlayerComponent().mediaPlayer()
            //EmbeddedMediaPlayerComponent().mediaPlayer()
    }*/


    fun testStart(){
   //     mediaPlayer.media().start("C:\\Users\\Aarav Aditya Shah\\Desktop\\Alan Walker - Different World (feat. Sofia Carson, K-391  CORSAK).mp3")
        Thread.sleep(10000)
     //   mediaPlayer.release()
    }
}