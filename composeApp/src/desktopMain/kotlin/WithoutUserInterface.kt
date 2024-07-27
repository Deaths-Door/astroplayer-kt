import com.deathsdoor.astroplayer.core.AstroMediaItem
import com.deathsdoor.astroplayer.core.AstroPlayer
import com.deathsdoor.astroplayer.core.NativeMediaPLayer
import com.eygraber.uri.Uri
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent
import java.io.File
import java.net.URL
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.PathWalkOption
import kotlin.io.path.absolute
import kotlin.io.path.walk

internal fun playAudio() = runBlocking {
    /*println("Please enter directory for music:")
    // Replace with own directory
    val directory = //"audio file directory"
        "C:\\Users\\Aarav Aditya Shah\\Documents\\GitHub\\project-codebases\\kotlin\\astroplayer-kt\\assets\\audio"
    val mediaPlayerComponent = AudioPlayerComponent()
    mediaPlayerComponent.mediaPlayer().media().play(File(directory).walk().first().absolutePath);
    delay(20000)*/


    /* val directory = DIRECTORY
     val it = File(directory).walk().first()
     astroPlayer.prepare()
     astroPlayer.addMediaItem(
         AstroMediaItem(
             mediaId = it.hashCode().toString(),
             source = Uri.parse(it.absolutePath),
             metadata = null
         )
     )
     astroPlayer.play()

     delay(DELAY_SECONDS * 1000L)*/

    println("lets go amigos")
    val nativeMediaPlayer = NativeMediaPLayer()
    val astroPlayer = AstroPlayer(nativeMediaPlayer)

    astroPlayer.readMediaItemsFromDirectory()
    astroPlayer.prepare()


    astroPlayer.play()
    delay(5000)
    astroPlayer.seekTo(astroPlayer.contentDuration - 5000)

    delay(7000)

    println(astroPlayer.currentMediaItem)
    println(astroPlayer.isPlaying)
    astroPlayer.play()
    println(astroPlayer.volume)

    delay(10000)
}