# AstroPlayer

AstroPlayer is an open-source media player designed for the Kotlin Multiplatform. It provides a simple API for audio playback, supports multiple media formats, and offers a Jetpack Compose UI.

**Key Features:**

- **Multiplatform Support** : Play audio across Android, Java (Desktop), iOS, and WebAssembly using platform-specific backends (Media3 for Android, VLCJ for Java, AVPlayer for iOS, and Holwer.js for WebAssembly).
- **Simple API** : Focus on easy-to-use APIs for managing playback.
- **Jetpack Compose UI** : Build beautiful and responsive user interfaces with Jetpack Compose.

**Packages:**

- `astroplayer-core`: Contains the core player logic.
- `astroplayer-ui`: Provides the Jetpack Compose UI components for playback control.

**Documentation:**

Detailed documentation can be found at https://www.here.com/docs/.

**Installation:**

AstroPlayer is published to Maven Central. To add it to your project, include the following dependencies in your `build.gradle` file:

```kotlin
sourceSets {
    commonMain.dependencies  {
        // TODO : Replace this with cords 
      implementation("com.example.astroplayer:astroplayer-core:VERSION")
      implementation("com.example.astroplayer:astroplayer-ui:VERSION")
    }
}
```

## Contributing:

Contributions are welcome! If you find any issues or have suggestions for improvement, please open an issue or submit a pull request.

## License

AstroPlayer is licensed under the Apache License 2.0. See the LICENSE file for details.