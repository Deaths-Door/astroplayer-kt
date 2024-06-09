import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import astroplayer.composeapp.generated.resources.Res
import astroplayer.composeapp.generated.resources.compose_multiplatform
import com.deathsdoor.astroplayer.core.AstroMediaItem
import com.deathsdoor.astroplayer.core.AstroPlayer
import com.deathsdoor.astroplayer.core.NativeMediaPLayer
import com.deathsdoor.astroplayer.ui.PreviousMediaItemButton
import com.deathsdoor.astroplayer.ui.rememberAstroPlayerState
import com.eygraber.uri.Uri
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            val state = rememberAstroPlayerState(AstroPlayer(NativeMediaPLayer()))

            state.astroPlayer.addMediaItem(
                AstroMediaItem("test-id",
                source= Uri.parse("C:\\Users\\Aarav Aditya Shah\\Music\\Alan Walker - Dreamer.mp3"),
                metadata = null
            )
            )
            PreviousMediaItemButton(state)
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}