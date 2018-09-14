// This file is generated.

package com.mapbox.mapboxsdk.plugins.annotation;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.LongSparseArray;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.style.layers.PropertyValue;
import com.mapbox.mapboxsdk.style.layers.CircleLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.style.layers.Property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.mapbox.mapboxsdk.style.expressions.Expression.get;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.*;
//import static com.mapbox.mapboxsdk.annotations.symbol.Symbol.Z_INDEX;

/**
 * The circle manager allows to add circles to a map.
 */
public class CircleManager extends AnnotationManager<Circle, OnCircleClickListener> {

  public static final String ID_GEOJSON_SOURCE = "mapbox-android-circle-source";
  public static final String ID_GEOJSON_LAYER = "mapbox-android-circle-layer";

  private CircleLayer layer;
  private final MapClickResolver mapClickResolver;

  /**
   * Create a circle manager, used to manage circles.
   *
   * @param mapboxMap the map object to add circles to
   */
  @UiThread
  public CircleManager(@NonNull MapboxMap mapboxMap) {
    this(mapboxMap, null);
  }

  /**
   * Create a circle manager, used to manage circles.
   *
   * @param mapboxMap the map object to add circles to
   * @param belowLayerId the id of the layer above the circle layer
   */
  @UiThread
  public CircleManager(@NonNull MapboxMap mapboxMap, @Nullable String belowLayerId) {
    this(mapboxMap, new GeoJsonSource(ID_GEOJSON_SOURCE), new CircleLayer(ID_GEOJSON_LAYER, ID_GEOJSON_SOURCE)
      .withProperties(
        getLayerDefinition()
      ), belowLayerId);
  }

  /**
   * Create a circle manager, used to manage circles.
   *
   * @param mapboxMap     the map object to add circles to
   * @param geoJsonSource the geojson source to add circles to
   * @param layer         the circle layer to visualise Circles with
   */
  @VisibleForTesting
  public CircleManager(MapboxMap mapboxMap, @NonNull GeoJsonSource geoJsonSource, @NonNull CircleLayer layer, @Nullable String belowLayerId) {
    super(mapboxMap, geoJsonSource);
    initLayer(layer, belowLayerId);
    mapboxMap.addOnMapClickListener(mapClickResolver = new MapClickResolver(mapboxMap));
  }

  /**
   * Initialise the layer on the map.
   *
   * @param layer the layer to be added
   * @param belowLayerId the id of the layer above the circle layer
   */
  private void initLayer(@NonNull CircleLayer layer, @Nullable String belowLayerId) {
    this.layer = layer;
    if (belowLayerId == null) {
      mapboxMap.addLayer(layer);
    } else {
      mapboxMap.addLayerBelow(layer, belowLayerId);
    }
  }

  /**
   * Cleanup circle manager, used to clear listeners
   */
  @UiThread
  public void onDestroy() {
    super.onDestroy();
    mapboxMap.removeOnMapClickListener(mapClickResolver);
  }

  /**
   * Create a circle on the map from a LatLng coordinate.
   *
   * @param latLng place to layout the circle on the map
   * @return the newly created circle
   */
  @UiThread
  public Circle createCircle(@NonNull LatLng latLng) {
    Circle circle = new Circle(this, currentId);
    circle.setLatLng(latLng);
    add(circle);
    return circle;
  }

  private static PropertyValue<?>[] getLayerDefinition() {
    return new PropertyValue[]{
      circleRadius(get("circle-radius")),
      circleColor(get("circle-color")),
      circleBlur(get("circle-blur")),
      circleOpacity(get("circle-opacity")),
      circleStrokeWidth(get("circle-stroke-width")),
      circleStrokeColor(get("circle-stroke-color")),
      circleStrokeOpacity(get("circle-stroke-opacity")),
    };
  }

  // Property accessors
  /**
   * Get the CircleTranslate property
   *
   * @return property wrapper value around Float[]
   */
  public Float[] getCircleTranslate() {
    return layer.getCircleTranslate().value;
  }

  /**
   * Set the CircleTranslate property
   *
   * @param value property wrapper value around Float[]
   */
  public void setCircleTranslate( Float[] value) {
    layer.setProperties(circleTranslate(value));
  }

  /**
   * Get the CircleTranslateAnchor property
   *
   * @return property wrapper value around String
   */
  public String getCircleTranslateAnchor() {
    return layer.getCircleTranslateAnchor().value;
  }

  /**
   * Set the CircleTranslateAnchor property
   *
   * @param value property wrapper value around String
   */
  public void setCircleTranslateAnchor(@Property.CIRCLE_TRANSLATE_ANCHOR String value) {
    layer.setProperties(circleTranslateAnchor(value));
  }

  /**
   * Get the CirclePitchScale property
   *
   * @return property wrapper value around String
   */
  public String getCirclePitchScale() {
    return layer.getCirclePitchScale().value;
  }

  /**
   * Set the CirclePitchScale property
   *
   * @param value property wrapper value around String
   */
  public void setCirclePitchScale(@Property.CIRCLE_PITCH_SCALE String value) {
    layer.setProperties(circlePitchScale(value));
  }

  /**
   * Get the CirclePitchAlignment property
   *
   * @return property wrapper value around String
   */
  public String getCirclePitchAlignment() {
    return layer.getCirclePitchAlignment().value;
  }

  /**
   * Set the CirclePitchAlignment property
   *
   * @param value property wrapper value around String
   */
  public void setCirclePitchAlignment(@Property.CIRCLE_PITCH_ALIGNMENT String value) {
    layer.setProperties(circlePitchAlignment(value));
  }

  /**
   * Inner class for transforming map click events into circle clicks
   */
  private class MapClickResolver implements MapboxMap.OnMapClickListener {

    private MapboxMap mapboxMap;

    private MapClickResolver(MapboxMap mapboxMap) {
      this.mapboxMap = mapboxMap;
    }

    @Override
    public void onMapClick(@NonNull LatLng point) {
      if (clickListeners.isEmpty()) {
        return;
      }

      PointF screenLocation = mapboxMap.getProjection().toScreenLocation(point);
      List<Feature> features = mapboxMap.queryRenderedFeatures(screenLocation, ID_GEOJSON_LAYER);
      if (!features.isEmpty()) {
        long circleId = features.get(0).getProperty(Circle.ID_KEY).getAsLong();
        Circle circle = annotations.get(circleId);
        if (circle != null) {
          for (OnCircleClickListener listener : clickListeners) {
            listener.onCircleClick(circle);
          }
        }
      }
    }
  }
}
