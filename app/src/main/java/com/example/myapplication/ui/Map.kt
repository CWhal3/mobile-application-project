package com.example.myapplication.ui

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng

@Composable
fun MapScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    AndroidView(
        { ctx ->
            MapView(ctx).apply {
                // Pass a new instance of Bundle, since onCreate requires it but doesn't use it internally.
                onCreate(Bundle())
                getMapAsync(OnMapReadyCallback { googleMap ->
                    setUpMap(googleMap)
                })
            }
        },
        modifier = modifier.fillMaxSize(), // Fill the entire space available
        update = { mapView ->
            // Lifecycle events forwarding from the activity or fragment
            mapView.onStart()
            mapView.onResume()
            mapView.onPause()
            mapView.onStop()
            mapView.onDestroy()
        }
    )
}

private fun setUpMap(googleMap: GoogleMap) {
    // Example: Set the map type, add markers, or move the camera
    val halifax = LatLng(44.651070, -63.582687) // Coordinates for Halifax
    googleMap.addMarker(
        com.google.android.gms.maps.model.MarkerOptions()
            .position(halifax)
            .title("Marker in Halifax")
    )
    googleMap.moveCamera(CameraUpdateFactory.newLatLng(halifax))
}
