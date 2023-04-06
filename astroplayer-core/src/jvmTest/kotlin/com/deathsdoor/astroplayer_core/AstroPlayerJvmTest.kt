package com.deathsdoor.astroplayer_core

import com.sun.jna.NativeLibrary
import org.junit.Test
import uk.co.caprica.vlcj.binding.lib.LibVlc
import uk.co.caprica.vlcj.binding.support.runtime.RuntimeUtil
import uk.co.caprica.vlcj.factory.MediaPlayerFactory
import uk.co.caprica.vlcj.factory.discovery.NativeDiscovery
import uk.co.caprica.vlcj.player.base.MediaPlayer
import java.io.File


class AstroPlayerJvmTest{
    @Test
    fun init(){
        val found = NativeDiscovery().discover()
        println(found)
        println(LibVlc.libvlc_get_version())
    }
    fun defuadefaultt(fileName:String){
        val libvlcPath = "C:\\Program Files (x86)\\VideoLAN\\VLC\\"

        // Add search path for libvlc library
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), libvlcPath)
        // Create media player factory
        val factory = MediaPlayerFactory("-plugin-path=$libvlcPath")

        // Create media player
        val mediaPlayer: MediaPlayer = factory.mediaPlayers().newMediaPlayer()

        // Play media
        val file = File("C:\\Users\\Aarav Aditya Shah\\Desktop\\$fileName")
        val mrl = file.toURI().toString()
        mediaPlayer.media().startPaused(mrl)
        mediaPlayer.controls().play()
        Thread.sleep(10000)

        // Release resources
        mediaPlayer.release()
        factory.release()
      /*  val file = File("C:\\Users\\Aarav Aditya Shah\\Desktop\\$fileName")
        val audioStream = AudioSystem.getAudioInputStream(file)
        val clip = AudioSystem.getClip()
        clip.open(audioStream)
        clip.start()

        Thread.sleep(10000)
        clip.close()*/
    }
    @Test
    fun wavFile(): Unit {
        println("Running wavFile")
        defuadefaultt("Applaus.wav")
    }

    @Test
    fun mp3File(){
        defuadefaultt("Kalimba.mp3")
    }

    @Test
    fun mp3Filev2(){
        defuadefaultt("test.mp3")
    }
}