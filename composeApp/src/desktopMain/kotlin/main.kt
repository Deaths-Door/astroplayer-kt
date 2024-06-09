import com.deathsdoor.astroplayer.core.AstroMediaItem
import com.deathsdoor.astroplayer.core.AstroPlayer
import com.deathsdoor.astroplayer.core.NativeMediaPLayer
import com.deathsdoor.astroplayer.core.addListener
import com.deathsdoor.astroplayer.core.listeners.AstroListener
import com.eygraber.uri.Uri
import kotlinx.coroutines.delay
import uk.co.caprica.vlcj.media.Media
import uk.co.caprica.vlcj.media.MediaEventAdapter
import uk.co.caprica.vlcj.media.MediaRef
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter
import uk.co.caprica.vlcj.player.base.State
import uk.co.caprica.vlcj.player.component.CallbackMediaPlayerComponent
import uk.co.caprica.vlcj.player.component.callback.ScaledCallbackImagePainter


suspend fun main() {
    val astroPlayer = AstroPlayer(NativeMediaPLayer())
    astroPlayer.prepare()
    println("started running")

    astroPlayer.nativeMediaPlayer.mediaPlayer().events().addMediaPlayerEventListener(object : MediaPlayerEventAdapter() {
        override fun mediaChanged(mediaPlayer: MediaPlayer?, media: MediaRef?) {
            super.mediaChanged(mediaPlayer, media)
            println("changed mediaitem")
        }
    })

    astroPlayer.nativeMediaPlayer.mediaPlayer().events().addMediaEventListener(object : MediaEventAdapter() {
        override fun mediaStateChanged(media: Media?, newState: State?) {
            super.mediaStateChanged(media, newState)
            println("update state = $newState")
        }
    })

    astroPlayer.addListener(object : AstroListener {
        override fun onCurrentMediaItemChanged(mediaItem: AstroMediaItem?) {
            super.onCurrentMediaItemChanged(mediaItem)
            println("changed mediaitem")
        }

    })
    while(true) {
        astroPlayer.addMediaItem(
            AstroMediaItem("test-id",
                source= Uri.parse("C:\\Users\\Aarav Aditya Shah\\Music\\Alan Walker - Dreamer.mp3"),
                metadata = null
            )
        )

        astroPlayer.play()


        delay(2000L)
    }

    /*application {
        Window(onCloseRequest = ::exitApplication, title = "AstroPlayer") {
            App()
        }
    }*/
}