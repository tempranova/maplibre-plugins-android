<h1 align="center">
  <br>
  <img src="https://github.com/maplibre/maplibre-plugins-android/blob/master/.github/mlb-plugins-logo.png" alt="MapLibre Plugins" width="500">
</h1>

<h4 align="center">Plugins are single-purpose libraries built on top of the <a href="https://maplibre.org/maplibre-gl-native/android/api/">MapLibre Maps SDK for Android</a> that you can include in your apps like any other Android dependency</h4>

<p align="center">
  <a href="https://circleci.com/gh/maplibre/maplibre-plugins-android">
    <img src="https://circleci.com/gh/maplibre/maplibre-plugins-android.svg?style=shield&circle-token=:circle-token">
  </a>
</p>
<br>

# MapLibre Plugins for Android

Plugins are single-purpose libraries built on top of the [MapLibre Maps SDK for Android](https://maplibre.org/maplibre-gl-native/android/api/) that you can include in your apps like any other Android dependency. A full list of the current plugins is available below.

We are looking for maintainers for these plugins! Please reach out to the Maplibre team and take a look at [the contribution guide](https://github.com/maplibre/maplibre-plugins-android/blob/master/CONTRIBUTING.md).

## Available Plugins

* [**Annotation:** Simplify the way to set and adjust the visual properties of annotations on a map.](https://github.com/maplibre/maplibre-plugins-android/tree/master/plugin-annotation)

* [**MarkerView:** Add map markers that are Android views.](https://github.com/maplibre/maplibre-plugins-android/tree/master/plugin-markerview)

* [**Traffic:** Adds a real-time traffic layer to any MapLibre base map.](https://github.com/maplibre/maplibre-plugins-android/tree/master/plugin-traffic)

* [**Location layer:** [Deprecated] Add a location marker on your map indicating the user's location.](https://github.com/maplibre/maplibre-plugins-android/tree/master/plugin-locationlayer)

* [**Building:** Add extruded "3D" buildings in your map style.](https://github.com/maplibre/maplibre-plugins-android/tree/master/plugin-building)

* [**Offline:** Download maps tiles and manage downloaded regions for situations when a user's device doesn't have an internet connection.](https://github.com/maplibre/maplibre-plugins-android/tree/master/plugin-offline)

* [**Places:** Add location search to your app with beautiful UI.](https://github.com/maplibre/maplibre-plugins-android/tree/master/plugin-places)

* [**Localization:** Have your map's text automatically match the device's default language setting.](https://github.com/maplibre/maplibre-plugins-android/tree/master/plugin-localization)

* [**Scale bar:** Provide a visual map scale bar for your users to determine distance.](https://github.com/maplibre/maplibre-plugins-android/tree/master/plugin-scalebar)

## Installing a plugin

By using a plugin, you also have to include the MapLibre Maps SDK for Android which means that you'll need to setup your project to use the Maps SDK if you haven't already. Head over to the [overview page for the Maps SDK](https://maplibre.org/projects/maplibre-native/) to learn more.

Note that depending on the plugin you add, there might be required permissions and additional setup steps. You'll find more information on whether or not more configuration steps are involved when looking at the specific plugin documentation.

1. Start Android Studio
2. Open up your application's `build.gradle`
3. Make sure that your project's `minSdkVersion` is at API 14 or higher
4. Under dependencies, add a new build rule for the latest plugin version you are trying to use.
```gradle
repositories {
  mavenCentral()
  maven {
    url 'https://api.maplibre.com/downloads/v2/releases/maven'
  }
}

dependencies {
  implementation 'com.maplibre.maplibresdk:{PLUGIN_NAME}-v{MAJOR_MAPS_SDK_VERSION_NUMBER}:PLUGIN_VERSION_NUMBER'
}
```

Plugin artifacts are versioned based on the major release of the Maps SDK for Android, which means, that each artifact's name has a major version of the Maps SDK it's compatible with appended.

5. Click the Sync Project with Gradle Files near the toolbar in Studio.

## Maps SDK compatibility

The MapLibre Plugins for Android are heavily dependent on the major semantic versioning number of the Maps SDK. They either won't compile or hide runtime bugs when paired with a different major version of the Maps SDK. Each plugin's dependency name has a `vX` suffix which states the major version of the Maps SDK that the plugin is compatible with. This suffix makes the transition between versions easier and more educated without the need to jump into changelogs and compare repositories.

## Help and Usage

A plugin is simply a library module built on top of the MapLibre Maps SDK for Android. Plugins can be easy to use. Currently, we are not requiring plugins to register themselves or to implement any specific interfaces so that they're simple to consume.

### Test app

This repository includes an app with examples showing how you can use each plugin.
- To access ready-to-use snippets, [see its code here](https://github.com/maplibre/maplibre-plugins-android/tree/master/app/src/main/java/com/mapbox/mapboxsdk/plugins/testapp).
- To run the application locally, open the [`PluginApplication`](https://github.com/maplibre/maplibre-plugins-android/blob/4ff768983323cc4a57791bcb577639109e4fd9ce/app/src/main/java/com/mapbox/mapboxsdk/plugins/testapp/PluginApplication.kt) file

This might change in the future as we build more plugins and learn how you use them. We'd love to [hear your feedback](https://github.com/maplibre/maplibre-plugins-android/issues).

## Why Plugins?

Splitting specific functionality into plugins makes our Maps SDK lighter and nimble for you to use, and it also lets us iterate faster. We can release plugins more often than the SDK, which requires a slower pace due to its larger codebase.

The MapLibre Android team creates plugins but this plugins repository is an open-source project similar to the various MapLibre SDKs for Android.
Plugins' lightweight nature makes them much easier for you and anyone else to contribute rather than trying to add the same feature to the more robust Maps SDK. The MapLibre team can also more easily accept contributed plugins and keep the plugin list growing.

## Contributing

We welcome contributions to this plugin repository!

If you're interested in building and sharing your own plugin, please read [the contribution guide](https://github.com/maplibre/maplibre-plugins-android/blob/master/CONTRIBUTING.md) to learn how to get started.
