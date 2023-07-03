package com.deathsdoor.astroplayer.core.dataclasses

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class Lyric constructor(val lines : List<Line> = listOf()){
    @Serializable
    class Line(val text: String,val timestamp : Long){
        private val formatTimeStamp : String get() {
            val totalSeconds = timestamp / 1000
            val seconds = totalSeconds % 60
            val minutes = totalSeconds / 60
            return "$minutes:${seconds.toString().padStart(2, '0')}:${(this.timestamp % 1000).toString().padStart(3, '0')}"
        }
        override fun toString(): String = "[${formatTimeStamp}] $text"
    }
    init {
        lines.forEachIndexed { index, line ->
            require(line.timestamp >= 0) { "startTimeStamp in the LyricsLine must >= 0" }
            if(index != 0) require(line.timestamp > lines[index - 1].timestamp){ "startTimeStamp in the LyricsLine[index] must >= LyricsLine[index - 1]"}
        }
    }

    override fun toString(): String = this.lines.joinToString(separator = "\n") { it.toString() }

    companion object {
        private val TimeTag by lazy { Regex("(\\d+):(\\d+)(\\.(\\d+))?") }
        private val Line by lazy { Regex("((\\[${TimeTag.pattern}])+)(.*)") }
        fun parseInput(input : String) : Lyric {
            if(input.isEmpty()) return Lyric()

            val lines = mutableListOf<Line>()

            Line.findAll(input).forEach {
                val indexOfBracket =  it.value.indexOf(']')
                require(indexOfBracket != -1) { "Closing Bracket for timestamp not found , ${it.value}" }

                val timeTagResult = TimeTag.find(it.value.substring(1,indexOfBracket - 1))
                val time = timeTagResult!!.groupValues[1].toLong() *
                        60000 + timeTagResult.groupValues[2].toLong() * 1000 +
                        (if(timeTagResult.groupValues[3].isEmpty()) 0L else timeTagResult.groupValues[3].padEnd(3,'0').toLong())

                val text = it.value.substring(indexOfBracket + 1,it.value.length)

                lines.add(Line(text,time))
            }
            return Lyric(lines)
        }
    }
}