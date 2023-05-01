# AstroPlayer
AstroPlayer is an open-source media player designed for the Kotlin Multiplatform Mobile (KMM) framework. It provides a simple API for audio playback and supports multiple media formats.

**Note :**  The AstroPlayer library is currently under development, and as such, some parts of the library may not work as expected. The Android platform is the only one that has been tested, while the Javascript platform is untested. The Desktop and iOS platforms are not yet supported. Additionally, not all of the methods listed in the code may be available, as they are still being worked on. Certain functions may also not work as expected due to the ongoing development process.

**Note :** Desktop will come out soon and IOS implementation has began

# Usage
To use the library, add the following dependency to your app's `build.gradle` file:

## **For Astroplayer-Core module**

The astroplayer-core module is the core library for the AstroPlayer media player. It provides the essential components for playing media, such as the AstroPlayer class and the MediaItem class.

```kotlin
    implementation("com.github.Deaths-Door:AstroPlayer-core:0.1.0")
    implementation("com.github.Deaths-Door:AstroPlayer-core-ios:0.1.0")
    implementation("com.github.Deaths-Door:AstroPlayer-core-js:0.1.0")
    implementation("com.github.Deaths-Door:AstroPlayer-core-android:0.1.0")
    implementation("com.github.Deaths-Door:AstroPlayer-core-desktop:0.1.0")
```

## **For Astroplayer-UI module**

The astroplayer-ui module is a UI library for the AstroPlayer media player. It provides a set of UI components that can be used to create a customized media player interface. The UI components are designed to be highly customizable and can be adapted to fit a wide range of design requirements.
```kotlin
    implementation("com.github.Deaths-Door:AstroPlayer-ui:0.1.0")
    implementation("com.github.Deaths-Door:AstroPlayer-ui-ios:0.1.0")
    implementation("com.github.Deaths-Door:AstroPlayer-ui-js:0.1.0")
    implementation("com.github.Deaths-Door:AstroPlayer-ui-android:0.1.0")
    implementation("com.github.Deaths-Door:AstroPlayer-ui-desktop:0.1.0")
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

## Intefaces 

### MediaEventListener 

- `onSeekForward()` : Called when the user seeks forward in the current media item.
- `onSeekBackward()` : Called when the user seeks backward in the current media item.
- `onSeekToNextMediaItem()` : Called when the user skips to the next media item in the playlist.
- `onSeekToPreviousMediaItem()` : Called when the user skips to the previous media item in the playlist.
- `onPlaybackPlayed()` : Called when the media playback starts playing.
- `onPlaybackPaused()` : Called when the media playback is paused.

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
- `mediaEventListener` : Callback functions that can be implemented by a client app to handle media playback events.

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


# User Interface

# Contribution
 Any contributions, whether they are large or small, are greatly appreciated. This can include major features, bug fixes, additional language translations, recommendations, or unit/integration tests.
To contribute, please follow these steps:
-Fork the repository and create your own branch for your changes.
- Make your changes and ensure that they are thoroughly tested.
- Submit a pull request with a detailed description of your changes and the problem it solves.
- All contributions will be thoroughly reviewed and discussed before being merged. Please ensure that your contributions align with the project's goals and coding standards.

Thank you for contributing to this project!
