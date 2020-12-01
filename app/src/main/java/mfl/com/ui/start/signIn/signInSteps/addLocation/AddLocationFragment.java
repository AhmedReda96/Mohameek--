package mfl.com.ui.start.signIn.signInSteps.addLocation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import mfl.com.R;
import mfl.com.databinding.FragmentAddLocationBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.ui.start.signIn.signInSteps.stepsHome.SignInStepsHome;

import static android.app.Activity.RESULT_OK;

/*
المدني جنائي  عسكر اداري اسرة
*/
public class AddLocationFragment extends Fragment implements View.OnClickListener {
    private final String TAG = AddLocationFragment.class.getSimpleName();
    private FragmentAddLocationBinding binding;
    private GeneralMethods generalMethods;
    private GoogleMap mMap;
    private double latitude = 31.2357;
    private double longitude = 31.2357;
    private String address = null, getLat, getLong, governorateName = "", cityName = "", zoneName = "", streetName = "", buildNumber = "", floorNumber = "", unitNumber = "";
    private CameraUpdate center;
    private CameraUpdate zoom;
    private List<String> governorate = new ArrayList<>();
    private List<String> cities = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddLocationBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        generalMethods = new GeneralMethods(getActivity());
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);


        binding.selectLocationBtn.setOnClickListener(this::onClick);
        binding.nextBtn.setOnClickListener(this::onClick);
        binding.mapLin.setOnClickListener(this::onClick);

        setupSpinners();
        addressListener();

    }

    private void addressListener() {
        binding.governorateSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                //Snackbar.make(view, "Clicked " + String.valueOf(position+1), Snackbar.LENGTH_LONG).show();
                governorateName = item;
                binding.address.setText(governorateName + cityName + streetName + buildNumber + floorNumber + unitNumber);
                binding.zoneLin.setVisibility(View.VISIBLE);
            }
        });

        binding.citySpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                //Snackbar.make(view, "Clicked " + String.valueOf(position+1), Snackbar.LENGTH_LONG).show();
                cityName = " -" + item;
                binding.address.setText(governorateName + cityName + zoneName + streetName + buildNumber + floorNumber + unitNumber);

            }
        });

        binding.zoneName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                zoneName = " -" + s;
                binding.address.setText(governorateName + cityName + zoneName + streetName + buildNumber + floorNumber + unitNumber);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.streetName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                streetName = " -شارع " + s;
                binding.address.setText(governorateName + cityName + zoneName + streetName + buildNumber + floorNumber + unitNumber);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.buildingNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buildNumber = " -عمارة رقم " + s;
                binding.address.setText(governorateName + cityName + zoneName + streetName + buildNumber + floorNumber + unitNumber);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.floorNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                floorNumber = " -رقم الدور " + s;
                binding.address.setText(governorateName + cityName + zoneName + streetName + buildNumber + floorNumber + unitNumber);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.unitNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                unitNumber = " -رقم المكتب " + s;
                binding.address.setText(governorateName + cityName + zoneName + streetName + buildNumber + floorNumber + unitNumber);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setupSpinners() {
        governorate.add("القاهرة");
        governorate.add("الجيزة");
        governorate.add("الأسكندرية");
        governorate.add("الدقهلية");
        governorate.add("البحر الأحمر");
        governorate.add("البحيرة");
        governorate.add("الفيوم");
        governorate.add("الإسماعلية");
        governorate.add("المنوفية");
        governorate.add("القليوبية");
        governorate.add("الوادي الجديد");
        governorate.add("السويس");
        governorate.add("اسوان");
        governorate.add("اسيوط");
        governorate.add("بني سويف");
        governorate.add("بورسعيد");
        governorate.add("دمياط");
        governorate.add("الشرقية");
        governorate.add("جنوب سيناء");
        governorate.add("كفر الشيخ");
        governorate.add("مطروح");
        governorate.add("الأقصر");
        governorate.add("قنا");
        governorate.add("شمال سيناء");
        governorate.add("سوهاج");

        cities.add("سوهاج");
        cities.add("سوهاج");
        cities.add("سوهاج");
        cities.add("سوهاج");
        cities.add("سوهاج");
        cities.add("سوهاج");
        cities.add("سوهاج");
        cities.add("سوهاج");
        cities.add("سوهاج");
        cities.add("سوهاج");
        cities.add("سوهاج");
        cities.add("سوهاج");
        cities.add("سوهاج");
        cities.add("سوهاج");
        cities.add("سوهاج");
        cities.add("سوهاج");
        cities.add("سوهاج");
        cities.add("سوهاج");
        cities.add("سوهاج");
        cities.add("سوهاج");


        binding.governorateSpinner.setItems(governorate);
        binding.citySpinner.setItems(cities);


    }


    @Override
    public void onClick(View v) {
        if (binding.selectLocationBtn.equals(v)) {
            Intent i = new Intent(getActivity(), Map.class);
            startActivityForResult(i, 1);

        }



        if (binding.nextBtn.equals(v)) {
            ((SignInStepsHome) getActivity()).selectIndex(3);

        }

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                SupportMapFragment fragment = new SupportMapFragment();
                transaction.add(R.id.map, fragment);
                transaction.commit();

                binding.scroll.scrollTo(0, binding.scroll.getBottom());

                fragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap map) {
                        mMap = map;
                        mMap.getUiSettings().setScrollGesturesEnabled(false);
                        mMap.getUiSettings().setAllGesturesEnabled(false);
                        mMap.getUiSettings().setScrollGesturesEnabledDuringRotateOrZoom(false);
                        mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        mMap.getUiSettings().setIndoorLevelPickerEnabled(false);

                        address = data.getStringExtra("address");
                        getLat = data.getStringExtra("latitude");
                        getLong = data.getStringExtra("longitude");

                        setMapData();

                    }
                });


            }
        }
    }

    private void setMapData() {
        binding.mapLin.setEnabled(false);
        binding.mapLin.setClickable(false);
        binding.mapLin.setFocusable(false);
        latitude = Double.parseDouble(getLat);
        longitude = Double.parseDouble(getLong);
        LatLng position = new LatLng(latitude, longitude);
        center = CameraUpdateFactory.newLatLng(position);
        zoom = CameraUpdateFactory.zoomTo(19f);

        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        mMap.addMarker(new MarkerOptions().position(position).title(address)).showInfoWindow();


        binding.mapLin.setVisibility(View.VISIBLE);
        binding.selectLocationBtn.setText(R.string.updateLocation);
        binding.addressLin.setVisibility(View.VISIBLE);
        binding.mapAddress.setText(address);
        Log.d(TAG, "Mohameek setMapData:" + address + "," + String.valueOf(getLat + "+" + getLong));


    }

}