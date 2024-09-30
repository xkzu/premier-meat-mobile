package cl.duoc.premier_meat

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MapActivity : AppCompatActivity() {

    private lateinit var map: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Configuration.getInstance().userAgentValue = packageName
        map = MapView(this)
        setContentView(map)
        map.setMultiTouchControls(true)

        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)

        if (latitude != 0.0 && longitude != 0.0) {
            Log.d("MapActivity", "Mostrando ubicación: Latitud $latitude, Longitud $longitude")
            val userLocation = GeoPoint(latitude, longitude)
            map.controller.setZoom(15.0)
            map.controller.setCenter(userLocation)

            val marker = Marker(map)
            marker.position = userLocation
            marker.title = "Tu ubicación"
            map.overlays.add(marker)
        } else {
            Log.d("MapActivity", "Coordenadas inválidas")
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }
}
