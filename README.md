# AstroPlayer
AstroPlayer is an open-source media player designed for the Kotlin Multiplatform Mobile (KMM) framework. It provides a simple API for audio playback and supports multiple media formats.

**Note :**  The AstroPlayer library is currently under development, and as such, some parts of the library may not work as expected. The Android platform is the only one that has been tested, while the Javascript platform is untested. The JVM and iOS platforms are not yet supported. Additionally, not all of the methods listed in the code may be available, as they are still being worked on. Certain functions may also not work as expected due to the ongoing development process.

# Usage
To use the library, add the following dependency to your app's `build.gradle` file:

```kotlin
  implementation("com.github.Deaths-Door:AstroPlayer:0.1.0")
```

# Methods

The `AstroPlayer` class is the main class for the audio player functionality.

## Enums 

### RepeatMode
An enumeration class representing the repeat modes for the AstroPlayer:

- `Auf` : Repeat mode for the current media item only.
- `Current` : Repeat mode for the current media item and the subsequent media items.
- `Alle` : Repeat mode for all the media items in the media item list.
- `Group` : Repeat mode for a specific range of media items.
- `Shuffle` : Shuffle mode where media items are played in random order.

## Public Properties:

- `mediaItems` : A mutable list of `MediaItem` objects that represent the audio files to be played.
- `isPlaying` : A Boolean value that indicates if the audio player is currently playing.
- `isPaused` : A Boolean value that indicates if the audio player is currently paused.
- `playBackSpeed` : A Float value that controls the playback speed of the audio player.
- `volume` : A Float value that controls the volume of the audio player.
- `currentMediaItemIndex` : An Integer value that represents the index of the currently playing `MediaItem` object in the `mediaItems` list.
- `currentMediaItemPosition` : A Long value that represents the current position of the audio playback in milliseconds.
- `repeatMode` : A `RepeatMode` enum value that represents the current repeat mode of the audio player.
- `shuffleModeEnabled` : Indicates if the shuffle mode is enabled in the audio player.
- `totalMediaItems` : Total number of media items in the player's media item list.
- `currentMediaItem` : Current media item being played by the player.
- `currentMediaItemMetadata` : Metadata of the current media item being played by the player.
- `currentMediaItemTrackLength` : Length of the current media item's track in milliseconds.
- 
## Public Functions:
- `play()` : Starts playing the audio player.
- `pause()` : Pauses the audio player.
- `stop()` : Stops the audio player.
- `seekTo(milliseconds : Long)` : Seeks to the specified position in the audio playback.
- `seekToNextMediaItem()` : Seeks to the next `MediaItem` object in the `mediaItems` list.
- `seekToPreviousMediaItem()` : Seeks to the previous `MediaItem` object in the `mediaItems` list.
- `repeatByGroup(startIndex:Int,endIndex : Int)` : Repeats the specified range of `MediaItem` objects in the `mediaItems` list.
- `mediaEventListener` : A property that sets the `MediaEventListener` for the audio player.
- `clear()` : Clears all media items from the `AstroPlayer` instance.
- `addMediaItem(mediaItem: MediaItem)` : Adds a single media item to the end of the media items list in the `AstroPlayer` instance.
- `addMediaItem(index:Int, mediaItem: MediaItem)` : Adds a single media item to the media items list at the specified index in the `AstroPlayer` instance.
- `addMediaItems(mediaItems: List<MediaItem>)` : Adds a list of media items to the end of the media items list in the `AstroPlayer` instance.
- `addMediaItems(index:Int, mediaItems: List<MediaItem>)` : Adds a list of media items to the media items list at the specified index in the `AstroPlayer` instance.
- `remove(mediaItem: MediaItem)` : Removes a single media item from the media items list in the `AstroPlayer` instance.
- `removeAt(index:Int)` : Removes a media item at the specified index from the media items list in the `AstroPlayer` instance.
-  `rearrange(from:Int, to:Int)` : Rearranges media items in the media items list by swapping the items at the specified indices in the `AstroPlayer` instance.
