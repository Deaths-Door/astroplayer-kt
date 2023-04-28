Pod::Spec.new do |spec|
    spec.name                     = 'astroplayer_core'
    spec.version                  = '1.0'
    spec.homepage                 = 'https://www.google.comm'
    spec.source                   = { :http=> ''}
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'AstroPlayer is an open-source media player designed for the Kotlin Multiplatform Mobile (KMM) framework. It provides a simple API for audio playback and supports multiple media formats.'
    spec.vendored_frameworks      = 'build/cocoapods/framework/astroplayer-core.framework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target = '14.1'
                
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':astroplayer-core',
        'PRODUCT_MODULE_NAME' => 'astroplayer-core',
    }
                
    spec.script_phases = [
        {
            :name => 'Build astroplayer_core',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$COCOAPODS_SKIP_KOTLIN_BUILD" ]; then
                  echo "Skipping Gradle build task invocation due to COCOAPODS_SKIP_KOTLIN_BUILD environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
                
end