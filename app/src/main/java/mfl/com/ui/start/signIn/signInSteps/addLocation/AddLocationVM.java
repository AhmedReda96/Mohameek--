package mfl.com.ui.start.signIn.signInSteps.addLocation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mfl.com.R;
import mfl.com.api.Client;
import mfl.com.api.Services;
import mfl.com.pojo.location.AddLocationResponse;
import mfl.com.pojo.location.CitesResponse;
import mfl.com.pojo.location.Cities;
import mfl.com.session.GeneralMethods;
import mfl.com.session.sp.TestLogin;
import mfl.com.ui.start.signIn.signInSteps.stepsHome.SignInStepsHome;

public class AddLocationVM extends ViewModel {
    private Context context;
    private static final String TAG = AddLocationVM.class.getSimpleName();
    private GeneralMethods generalMethods;
    public MutableLiveData<String> resultLD = new MutableLiveData<>();
    private ProgressDialog progressDialog;
    private Services services;
    private String userId, userLat, userLng, userMapAddress, userAddress, userCityName;
    private int userCityId;
    private TestLogin testLogin;
    private Intent intent;
    private List<String> citesListName = new ArrayList<>();
    private List<Cities> citesListItem = new ArrayList<>();

    public MutableLiveData<List<String>> citesResultLD = new MutableLiveData<>();

    public void initVM(Activity activity) {
        this.context = activity;
        testLogin = new TestLogin(context);
        generalMethods = new GeneralMethods(context);
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(activity.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        services = Client.getClient().create(Services.class);
    }

    public void checkData(String lat, String lng, String mapAddress, int cityId, String cityName,String area, String street, String build, String floor, String office, String address) {

        resultLD.setValue("error");
        if (lat.equals("0.0") || lng.equals("0.0")) {
            resultLD.setValue("invalid mapLatLng");
            Log.d(TAG, "Mohameek checkData: invalid mapLatLng");

        } else {

            if (mapAddress.isEmpty()) {
                resultLD.setValue("invalid mapAddress");
                Log.d(TAG, "Mohameek checkData: invalid mapAddress");

            } else {

                if (cityId == 0) {
                    resultLD.setValue("invalid cityId");
                    Log.d(TAG, "Mohameek checkData: invalid cityId");

                } else {
                    if (area.isEmpty() || area.length() < 2) {
                        resultLD.setValue("invalid area");
                        Log.d(TAG, "Mohameek checkData: invalid area");

                    } else {
                        if (street.isEmpty() || street.length() < 2) {
                            resultLD.setValue("invalid street");
                            Log.d(TAG, "Mohameek checkData: invalid street");

                        }
                        if (build.isEmpty()) {
                            resultLD.setValue("invalid build");
                            Log.d(TAG, "Mohameek checkData: invalid build");

                        } else {
                            if (floor.isEmpty()) {
                                resultLD.setValue("invalid floor");
                                Log.d(TAG, "Mohameek checkData: invalid floor");

                            } else {
                                if (office.isEmpty()) {
                                    resultLD.setValue("invalid office");
                                    Log.d(TAG, "Mohameek checkData: invalid office");

                                } else {
                                    if (address.isEmpty() ) {
                                        resultLD.setValue("invalid address");
                                        Log.d(TAG, "Mohameek checkData: invalid address");

                                    } else {
                                        this.userLat = lat;
                                        this.userLng = lng;
                                        this.userMapAddress = mapAddress;
                                        this.userCityName = cityName;
                                        this.userAddress = address;
                                        checkInternetConnection();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void checkInternetConnection() {
        if (!generalMethods.checkInternet(context)) {
            Log.d(TAG, "Mohameek checkInternetConnection:  !isInternetPresent");
            resultLD.setValue("noInternetConnection");
        } else {
            progressDialog.show();
            Log.d(TAG, "Mohameek checkInternetConnection:  isInternetPresent");
            getCityId();

        }
    }

    private void getCityId() {
        for (int i = 0; i < citesListItem.size(); i++) {
            if (citesListItem.get(i).getCityName().equals(userCityName)) {
                userCityId = citesListItem.get(i).getId();
                sendRequest();
            }
        }


    }

    public void getCites(int index) {

        if (!generalMethods.checkInternet(context)) {
            Log.d(TAG, "Mohameek checkInternetConnection:  !isInternetPresent");
            resultLD.setValue("noInternetConnectionSpinner");
        } else {
            citesListItem.clear();
            citesListName.clear();
            services.getCitesResponse(testLogin.getToken(), index)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<CitesResponse>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onSuccess(CitesResponse citesResponse) {
                    for (int i = 0; citesResponse.getCities().size() > i; i++) {

                        citesListName.add(citesResponse.getCities().get(i).getCityName());


                    }

                    citesListItem.addAll(citesResponse.getCities());

                    citesResultLD.setValue(citesListName);
                }

                @Override
                public void onError(Throwable e) {
                    resultLD.setValue("invalid ServerError");
                    Log.d(TAG, "Mohameek sendRequest:error request : " + e.getMessage());
                }
            });

        }
    }


    private void sendRequest() {

        services.setOfficeLocationRequest(userCityId, userLat, userLng, userAddress, testLogin.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<AddLocationResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(AddLocationResponse addLocationResponse) {
                Log.d(TAG, "Mohameek sendRequest: onSuccess true: ");
                ((SignInStepsHome) context).selectIndex(3);
                progressDialog.dismiss();

            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                resultLD.setValue("invalid ServerError");
                Log.d(TAG, "Mohameek sendRequest:error request : " + e.getMessage());
            }
        });

    }
}