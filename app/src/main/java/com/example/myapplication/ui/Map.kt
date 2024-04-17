package com.example.myapplication.ui

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp


@Composable
fun MapScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val mapView = remember {
        MapView(context).apply {

        }
    }

    DisposableEffect(lifecycleOwner) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> throw IllegalStateException()
            }
        }
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
            mapView.onDestroy()
        }
    }





    AndroidView(
        factory = { mapView },
        modifier = modifier.fillMaxSize(),
        update = {
            mapView.getMapAsync { googleMap ->
                Log.d("MapScreen", "GoogleMap is ready")
                setUpMap(googleMap)
                googleMap.setOnMarkerClickListener { marker ->
                    // Handle marker click event here if needed
                    false // Return false to indicate that we haven't consumed the event
                }

            }


        }




    )

//    Button(
//        onClick = { addEmergencyRoomMarker(mapView) },
//        modifier = Modifier.padding(16.dp)
//    ) {
//        Text("Add Emergency Room Marker")
//    }


}
private fun setUpMap(googleMap: GoogleMap) {

    Log.d("MapScreen", "setUpMap function called successfully")

    // Map view initial location and zoom level = Halifax / HRM area
    val location = LatLng(44.651070, -63.582687)
    val zoomLevel = 12f

    // Move the camera to the defined location and zoom level
    val cameraPosition = CameraPosition.Builder()
        .target(location)
        .zoom(zoomLevel)
        .build()
    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

    val emergencyRoomLocation = LatLng(44.645866, -63.588099)
    googleMap.addMarker(
        MarkerOptions()
            .position(emergencyRoomLocation)
            .title("Emergency Room")
    )

}


//private fun addEmergencyRoomMarker(googleMap: GoogleMap) {
//    val emergencyRoomLocation = LatLng(44.651070, -63.582687)
//    googleMap.addMarker(
//        MarkerOptions()
//            .position(emergencyRoomLocation)
//            .title("Emergency Room")
//    )
//}



