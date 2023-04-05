package com.deathsdoor.astroplayer_core

import org.junit.Test
import java.io.File
import java.sql.Driver
import javax.sound.sampled.AudioSystem
class AstroPlayerJvmTest{
    @Test
    fun init(){

    }
    fun defuadefaultt(fileName:String){
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