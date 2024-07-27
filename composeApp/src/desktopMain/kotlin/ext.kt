import com.deathsdoor.astroplayer.core.AstroMediaItem
import com.deathsdoor.astroplayer.core.AstroPlayer
import com.eygraber.uri.Uri
import java.io.File


internal fun AstroPlayer.readMediaItemsFromDirectory() {
    File(DIRECTORY)
        .walk()
        .filter { it.isFile }
        .forEach {
            this@readMediaItemsFromDirectory.addMediaItem(
                AstroMediaItem(
                    mediaId = it.hashCode().toString(),
                    source = Uri.parse(it.absolutePath),
                    metadata = null
                )
            )
        }
}