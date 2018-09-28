package br.com.caelum.cadastro.gps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class GPS extends LocationCallback {
    private FusedLocationProviderClient client;
    private GoogleMap googleMap;

    public GPS(Context contexto, GoogleMap googleMap) {
        client = LocationServices.getFusedLocationProviderClient(contexto);
        this.googleMap = googleMap;
    }
    @SuppressLint("MissingPermission")
    public void inicializaBusca() {
        LocationRequest request = new LocationRequest();

        request.setInterval(15 * 1000);
        request.setSmallestDisplacement(5);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        client.requestLocationUpdates(request, this, null);
    }
    @Override
    public void onLocationResult(LocationResult locationResult) {
        super.onLocationResult(locationResult);
        Location lastLocation = locationResult.getLastLocation();

        double latitude = lastLocation.getLatitude();
        double longitude = lastLocation.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }
}
