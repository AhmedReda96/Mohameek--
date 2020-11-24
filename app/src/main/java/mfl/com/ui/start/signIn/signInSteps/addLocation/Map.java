package mfl.com.ui.start.signIn.signInSteps.addLocation;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.Manifest;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import mfl.com.R;
import mfl.com.databinding.ActivityMapBinding;
import mfl.com.session.GeneralMethods;

public class Map extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {
    private final String TAG = Map.class.getSimpleName();

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 44;
    private ActivityMapBinding binding;
    private GoogleMap mMap;
    private double latitude = 30.0444;
    private double longitude = 31.2357;
    private Geocoder geocoder;
    private List<Address> addresses;
    private String selectedAddress = null, finalCity;
    private GeneralMethods generalMethods;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location location;
    private CameraUpdate center;
    private CameraUpdate zoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        binding.saveBtn.setOnClickListener(this::onClick);
        binding.backBtn.setOnClickListener(this::onClick);

        init();
    }

    private void init() {
        generalMethods = new GeneralMethods(this);
        generalMethods.changeLanguage();
        Locale loc = new Locale("ar");
        geocoder = new Geocoder(this, loc);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        binding.locationBtn.setOnClickListener(this::onClick);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSIONS_REQUEST_CODE);

        }

        if (location == null) {
            center = CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude));
            zoom = CameraUpdateFactory.zoomTo(15);

            mMap.moveCamera(center);
            mMap.animateCamera(zoom);
        }


        mMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
                binding.pin.setBackgroundResource(R.drawable.pin);
                binding.progress.setVisibility(View.VISIBLE);
                binding.addressTv.setVisibility(View.GONE);
                selectedAddress = null;
            }
        });
        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                latitude = mMap.getCameraPosition().target.latitude;
                longitude = mMap.getCameraPosition().target.longitude;
                getLocationAddress(latitude, longitude);

            }

        });


    }


    private void getLocationAddress(double latitude, double longitude) {

        try {
            if (latitude != 0.0 && longitude != 0.0)

                addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

        binding.progress.setVisibility(View.GONE);
        selectedAddress = address;
        binding.addressTv.setText(selectedAddress);
        binding.addressTv.setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View view) {
        if (binding.locationBtn.equals(view)) {


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

            }

        }
        if (binding.saveBtn.equals(view)) {
            Log.d(TAG, "Mohameek saveBtn:" + String.valueOf(latitude + "," + longitude));

            Intent intent = new Intent();
            intent.putExtra("address", binding.addressTv.getText().toString());
            intent.putExtra("latitude", String.valueOf(latitude));
            intent.putExtra("longitude",String.valueOf(longitude));
            setResult(RESULT_OK, intent);
            finish();


        }
        if (binding.backBtn.equals(view)) {
            finish();
        }
    }

    private void getCurrentLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                location = task.getResult();
                if (location != null) {
                    center = CameraUpdateFactory
                            .newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));

                    Log.d(TAG, "Mohameek onComplete:" + String.valueOf(location.getLatitude() + "," + location.getLongitude()));
                    zoom = CameraUpdateFactory.zoomTo(20);

                    mMap.moveCamera(center);
                    mMap.animateCamera(zoom);

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("TAG", "Mohameek onRequestPermissionsResult:");
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                Log.d("TAG", "Mohameek onRequestPermissionsResult:User interaction was cancelled");

            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("TAG", "Mohameek onRequestPermissionsResult:Permission granted");

                getCurrentLocation();
            } else {

                Log.d("TAG", "Mohameek onRequestPermissionsResult:Permission denied");

            }
        }

    }
}
