# AstroPlayer

AstroPlayer is an open-source media player designed for the Kotlin Multiplatform. It provides a simple API for audio playback, supports multiple media formats, and offers a Jetpack Compose UI.

**Key Features:**

- **Multiplatform Support** : Play audio across Android, Java (Desktop), iOS, and WebAssembly using platform-specific backends (Media3 for Android, VLCJ for Java, AVPlayer for iOS, and Holwer.js for WebAssembly).
- **Simple API** : Focus on easy-to-use APIs for managing playback.
- **Jetpack Compose UI** : Build beautiful and responsive user interfaces with Jetpack Compose.

**Packages:**

- `astroplayer-core`: Contains the core player logic.
- `astroplayer-ui`: Provides the Jetpack Compose UI components for playback control. While the package currently provides a limited set of media player control components, 
  I am actively considering expanding its functionality in future releases.

**Documentation:**

Detailed documentation can be found [here](https://deaths-door.github.io/astroplayer-kt/).

## Development Status

**Important Note:** AstroPlayer is currently under active development. This means there may be some errors, unimplemented methods, and limitations in functionality. I are actively testing the core functionalities and working towards a stable release.

**Current Status:**

- **Android:** The Android platform has the most complete implementation and is expected to have the fewest issues.
- **Desktop (Java):** The desktop version using VLCJ might encounter some limitations or bugs.
- **WebAssembly (WASM):** The WebAssembly implementation using Holwer.js is in an early stage and may have more issues compared to Android.
- **iOS:** The iOS version using AVPlayer is currently just scaffolding. No functional code has been implemented yet.

**Publishing to Maven Central:**

Once the core functionalities are thoroughly tested and stabilized, I plan to publish AstroPlayer to Maven Central for wider accessibility.

**Installation:**

AstroPlayer is published to Maven Central. To add it to your project, include the following dependencies in your `build.gradle` file:

```kotlin
sourceSets {
    commonMain.dependencies  {
        // TODO : Replace this with cords 
      implementation("com.example.astroplayer:astroplayer-core:$astroplayer_vesion")
      implementation("com.example.astroplayer:astroplayer-ui:$astroplayer_vesion")
    }
}
```
## Contributing:

Contributions are welcome! If you find any issues or have suggestions for improvement, please open an issue or submit a pull request.

## License

AstroPlayer is licensed under the Apache License 2.0. See the LICENSE file for details.
