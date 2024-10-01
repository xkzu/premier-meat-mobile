package cl.duoc.premier_meat

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import cl.duoc.premier_meat.model.User
import com.google.android.gms.location.*

class MenuActivity : ComponentActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private val locationRequestCode = 100

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000)
            .setMinUpdateDistanceMeters(10f)
            .build()

        val user = intent.getParcelableExtra<User>("user", User::class.java)
        val userBackup: String = user?.name ?: "Usuario"

        setContent {
            MenuScreen(
                userName = userBackup,
                onChangePasswordClick = {
                    goToChangePassword(user)
                },
                onGeolocateClick = {
                    Log.d("MenuActivity", "Botón de geolocalización presionado")
                    requestLocationPermissionAndGeolocateUser()
                },
                onBackClick = {
                    goToBack()
                }
            )
        }
    }

    private fun requestLocationPermissionAndGeolocateUser() {
        Log.d("MenuActivity", "Solicitando permisos de ubicación")
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationRequestCode)
        } else {
            Log.d("MenuActivity", "Permisos de ubicación otorgados, geolocalizando...")
            startLocationUpdates()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == locationRequestCode && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("MenuActivity", "Permiso de ubicación concedido")
            startLocationUpdates()
        } else {
            Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
            Log.d("MenuActivity", "Permiso de ubicación denegado")
        }
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    val location = locationResult.lastLocation
                    if (location != null) {
                        Log.d("MenuActivity", "Ubicación obtenida: Latitud ${location.latitude}, Longitud ${location.longitude}")
                        openMapActivity(location.latitude, location.longitude)
                        fusedLocationClient.removeLocationUpdates(this)
                    } else {
                        Log.d("MenuActivity", "No se pudo obtener la ubicación")
                        Toast.makeText(this@MenuActivity, "No se pudo obtener la ubicación", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            null
        )
    }

    private fun openMapActivity(latitude: Double, longitude: Double) {
        Log.d("MenuActivity", "Navegando a MapActivity")
        val intent = Intent(this, MapActivity::class.java).apply {
            putExtra("latitude", latitude)
            putExtra("longitude", longitude)
        }
        startActivity(intent)
    }

    private fun goToChangePassword(user: User?) {
        val intent = Intent(this, ChangePasswordActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    private fun goToBack() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
