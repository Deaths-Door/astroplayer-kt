import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.deathsdoor.astroplayer.core.AstroMediaItem
import com.deathsdoor.astroplayer.core.AstroPlayer
import com.deathsdoor.astroplayer.core.NativeMediaPLayer
import com.deathsdoor.astroplayer.ui.rememberAstroPlayerState
import com.eygraber.uri.Uri
import kotlinx.coroutines.runBlocking
import java.io.File

internal fun showUI() =  application {
    Window(onCloseRequest = ::exitApplication, title = "AstroPlayer") {
        val astroPlayer = remember { AstroPlayer(NativeMediaPLayer()) }
        val state = rememberAstroPlayerState(astroPlayer)

        LaunchedEffect(Unit) {
            astroPlayer.readMediaItemsFromDirectory()

            println(astroPlayer.mediaItemCount)
            println(astroPlayer.allMediaItems())
        }

        App(state)
    }
}