import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.deathsdoor.astroplayer.core.RepeatMode
import com.deathsdoor.astroplayer.testing.ui.AstroButton
import com.deathsdoor.astroplayer.testing.ui.AstroInput
import com.deathsdoor.astroplayer.testing.ui.AstroValue
import com.deathsdoor.astroplayer.testing.ui.AstroValueWithProduceState
import com.deathsdoor.astroplayer.ui.AstroPlayerState
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalLayoutApi::class)
@Composable
@Preview
fun App(state: AstroPlayerState) = MaterialTheme {
    val arrangement = Arrangement.spacedBy(12.dp)

    FlowRow(Modifier.padding(16.dp),arrangement,arrangement) {
        AstroButton("Prepare") { state.astroPlayer .prepare() }
        AstroButton("Play") { println("volume ${state.astroPlayer.volume}");state.astroPlayer.play() }
        AstroButton("Pause") { state.astroPlayer.pause() }

        AstroValue("Is Playing",state.isPlaying)
        AstroValue("Is Paused",state.isPaused)

        AstroValue("Speed",state.playBackSpeed)
        AstroButton("Increase Speed") { state.astroPlayer.playBackSpeed += 0.1f }
        AstroButton("Decrease Speed") { state.astroPlayer.playBackSpeed -= 0.1f }

        AstroButton(if (state.shuffleModeEnabled) "Disable Shuffle" else "Enable Shuffle") {
            state.astroPlayer.shuffleModeEnabled = !state.astroPlayer.shuffleModeEnabled
        }

        AstroValue("Volume",state.volume)
        AstroButton("Increase Volume") { state.astroPlayer.increaseVolume() }
        AstroButton("Decrease Volume") { state.astroPlayer.decreaseVolume() }

        AstroValue("Is Muted",state.isMuted)
        AstroButton("Mute") { state.astroPlayer.mute() }
        AstroButton("Unmute") { state.astroPlayer.unMute() }

        AstroInput("Seek to millisecond") { state.astroPlayer.seekTo(it.toLong()) }
        AstroInput("seekToMediaItem at index") { state.astroPlayer.seekToMediaItem(it.toInt()) }

        AstroValue("currentMediaItem",state.currentMediaItem)
        AstroValue("currentMediaItemIndex",state.currentMediaItemIndex)

        AstroButton(if (state.isEqualizerEnabled) "Disable Equalizer" else "Enable Equalizer") {
            state.astroPlayer.isEqualizerEnabled = !state.astroPlayer.isEqualizerEnabled
        }
        AstroButton(if (state.isSmartEqualizerEnabled) "Disable Smart Equalizer" else "Enable Smart Equalizer") {
            state.astroPlayer.isSmartEqualizerEnabled = ! state.astroPlayer.isSmartEqualizerEnabled
        }

        AstroValue("currentEqualizerValues",state.currentEqualizerValues)

        AstroButton("Toggle Repeat Mode : ${state.repeatMode}") {
            state.astroPlayer.repeatMode = when(state.astroPlayer.repeatMode) {
                RepeatMode.Off -> {
                    state.astroPlayer.repeatMode = RepeatMode.One
                    RepeatMode.One
                }
                RepeatMode.One -> {
                    state.astroPlayer.repeatMode = RepeatMode.All
                    RepeatMode.All
                }
                RepeatMode.All -> {
                    state.astroPlayer.repeatMode = RepeatMode.Off
                    RepeatMode.Off
                }
            }
        }

        AstroValueWithProduceState("contentDuration"){state.astroPlayer.contentDuration}
        AstroValueWithProduceState("currentPosition"){state.astroPlayer.currentPosition}
    }
}