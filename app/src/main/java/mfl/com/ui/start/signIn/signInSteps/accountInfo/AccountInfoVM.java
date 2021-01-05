package mfl.com.ui.start.signIn.signInSteps.accountInfo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mfl.com.R;
import mfl.com.api.Client;
import mfl.com.api.Services;
import mfl.com.pojo.accountInfo.get.GetProfileResponse;
import mfl.com.pojo.accountInfo.put.EditAccountInfoRequest;
import mfl.com.pojo.accountInfo.put.EditAccountInfoResponse;
import mfl.com.session.GeneralMethods;
import mfl.com.session.sp.TestLogin;
import mfl.com.ui.start.signIn.signInSteps.stepsHome.SignInStepsHome;


public class AccountInfoVM extends ViewModel {
    private Context context;
    private static final String TAG = AccountInfoVM.class.getSimpleName();
    private GeneralMethods generalMethods;
    public MutableLiveData<String> resultLD = new MutableLiveData<>();
    public MutableLiveData<String> getFNResultLD = new MutableLiveData<>();
    public MutableLiveData<String> getLNResultLD = new MutableLiveData<>();
    public MutableLiveData<String> getPhoneResultLD = new MutableLiveData<>();

    private ProgressDialog progressDialog;
    private Services services;
    private String userImage, userFirstName, userLastName, userDate, userPhone, userBio;
    private List<Integer> userSpecialises = new ArrayList<>();
    private int userGender;
    private TestLogin testLogin;
    private ByteArrayOutputStream outputStream;
    private byte[] byteArray;

    public void initVM(Activity activity) {
        this.context = activity;
        testLogin = new TestLogin(context);
        generalMethods = new GeneralMethods(context);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(activity.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        outputStream = new ByteArrayOutputStream();

        services = Client.getClient().create(Services.class);
    }


    public void checkData(Bitmap imageUri, String imgExtension, String fistName, String lastName, int gender, String date, int currentYear, int choiceYear, String phone, List<Integer> specialises, String bio) {

        Log.d(TAG, "Mohameek checkData: " + testLogin.getToken());

        resultLD.setValue("resetError");
        if (imageUri == null) {
            resultLD.setValue("invalid card Image");
            Log.d(TAG, "Mohameek checkData: invalid card Image");

        } else {
            if (fistName.length() < 2 || fistName.isEmpty()) {
                resultLD.setValue("invalid firstName");
                Log.d(TAG, "Mohameek checkData: invalid firstName");

            } else {

                if (lastName.length() < 2 || lastName.isEmpty()) {
                    resultLD.setValue("invalid lastName");
                    Log.d(TAG, "Mohameek checkData: invalid lastName");

                } else {
                    if (phone.length() < 11) {
                        resultLD.setValue("invalid Phone");
                        Log.d(TAG, "Mohameek checkData: invalid phone");
                    } else {
                        if (date == null) {
                            resultLD.setValue("invalid date");
                            Log.d(TAG, "Mohameek checkData: invalid specialises");
                        } else {
                            if ((currentYear - choiceYear) <= 20) {
                                resultLD.setValue("invalid year");
                                Log.d(TAG, "Mohameek checkData: invalid year c=" + String.valueOf(currentYear) + "-ch=" + choiceYear);
                            } else {
                                if (specialises.size() == 0) {
                                    resultLD.setValue("invalid specialises");
                                    Log.d(TAG, "Mohameek checkData: invalid specialises");
                                } else {

                                    if (bio.length() < 5 || bio.isEmpty()) {
                                        resultLD.setValue("invalid bio");
                                        Log.d(TAG, "Mohameek checkData: invalid bio");
                                    } else {
                                        this.userFirstName = fistName;
                                        this.userLastName = lastName;
                                        this.userGender = gender;
                                        this.userDate = date;
                                        this.userPhone = phone;
                                        this.userSpecialises.addAll(specialises);
                                        this.userBio = bio;
                                        imageUri.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                                        byteArray = outputStream.toByteArray();
                                        this.userImage = "data:image/" + imgExtension + ";base64," + Base64.encodeToString(byteArray, Base64.DEFAULT);
                                        Log.d(TAG, "Mohameek  imgInBase64=" + userImage);

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
        if (generalMethods.checkInternet(context)) {
            Log.d(TAG, "Mohameek checkInternetConnection:  isInternetPresent");
            progressDialog.show();
            sendRequest();

        } else {
            Log.d(TAG, "Mohameek checkInternetConnection:  !isInternetPresent");
            resultLD.setValue("noInternetConnection");
        }

    }

    private void sendRequest() {
        Log.d(TAG, "Mohameek sendRequest token: " + testLogin.getToken());

        services.getAccountInfoRequest(new EditAccountInfoRequest(userGender, userSpecialises, userImage, userPhone, userDate, testLogin.getToken(), userBio))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<EditAccountInfoResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(EditAccountInfoResponse result) {

                if (result.getStatus()) {
                    Log.d(TAG, "Mohameek sendRequest: onSuccess true: ");
                    ((SignInStepsHome) context).selectIndex(2);
                    progressDialog.dismiss();


                } else {
                    progressDialog.dismiss();

                    Log.d(TAG, "Mohameek sendRequest: onSuccess false: ");

                }
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                resultLD.setValue("ServerError");
                Log.d(TAG, "Mohameek sendRequest:onError : " + e.getMessage());

            }
        });


    }


    public void getProfileData() {
        progressDialog.show();

        services.getProfileResponse(testLogin.getToken()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<GetProfileResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(GetProfileResponse result) {

                if (result.getUser().getFirstname() != null) {
                    getFNResultLD.setValue(result.getUser().getFirstname());
                }
                if (result.getUser().getLastname() != null) {
                    getLNResultLD.setValue(result.getUser().getLastname());

                }
                if (result.getUser().getPhone() != null) {
                    getPhoneResultLD.setValue(result.getUser().getPhone());

                }

                Log.d(TAG, "Mohameek getProfileData: onSuccess true: ");
                progressDialog.dismiss();


            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                resultLD.setValue("ServerError");
                Log.d(TAG, "Mohameek getProfileData:onError : " + e.getMessage());

            }
        });


    }
}
