package com.example.localizacionygps

import android.Manifest
import android.content.Context
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val PERMISSION_ID = 33
    }

    lateinit var mFusedLocationClient: FusedLocationProviderClient
    lateinit var tvLatitude: TextView
    lateinit var tvLongitude: TextView
    lateinit var btnLocate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        tvLatitude = findViewById(R.id.tvLatitude)
        tvLongitude = findViewById(R.id.tvLongitude)
        btnLocate = findViewById(R.id.btnLocate)

        btnLocate.setOnClickListener {
            getLocation()
        }
    }

    private fun checkGranted(permission: String): Boolean {
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkPermissions() =
        checkGranted(Manifest.permission.ACCESS_COARSE_LOCATION) && checkGranted(Manifest.permission.ACCESS_FINE_LOCATION)

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun getLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                mFusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
                    if (location != null) {
                        tvLatitude.text = location.latitude.toString()
                        tvLongitude.text = location.longitude.toString()
                    } else {
                        // Handle null location
                    }
                }
            } else {
                // Prompt user to enable location
                // ... (same code as provided earlier)
            }
        } else {
            requestPermissions()
        }
    }
}
