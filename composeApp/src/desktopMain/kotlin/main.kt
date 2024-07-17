import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.deathsdoor.astroplayer.core.AstroMediaItem
import com.deathsdoor.astroplayer.core.AstroPlayer
import com.deathsdoor.astroplayer.core.NativeMediaPLayer
import com.deathsdoor.astroplayer.ui.rememberAstroPlayerState
import com.eygraber.uri.Uri
import java.io.File

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "AstroPlayer") {
        val astroPlayer = remember { AstroPlayer(NativeMediaPLayer()) }
        val state = rememberAstroPlayerState(astroPlayer)

        LaunchedEffect(Unit) {
            File("C:\\Users\\Aarav Aditya Shah\\Documents\\GitHub\\project-codebases\\kotlin\\astroplayer-kt\\assets\\audio")
                .walk()
                .forEach {
                    astroPlayer.addMediaItem(
                        AstroMediaItem(
                            mediaId = it.hashCode().toString(),
                            source = Uri.parse(it.absolutePath),
                            metadata = null
                        )
                    )
                }
        }

        App(state)
    }
}