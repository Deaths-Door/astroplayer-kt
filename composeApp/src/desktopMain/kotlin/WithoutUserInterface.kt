import kotlinx.coroutines.delay
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent
import java.io.File
import java.net.URL
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.PathWalkOption
import kotlin.io.path.absolute
import kotlin.io.path.walk


internal suspend fun playAudio(){
    println("Please enter directory for music:")
    // Replace with own directory
    val directory = "audio file directory"
    val mediaPlayerComponent = AudioPlayerComponent()
    mediaPlayerComponent.mediaPlayer().media().play(File(directory).walk().first().absolutePath);
    delay(20000)
}