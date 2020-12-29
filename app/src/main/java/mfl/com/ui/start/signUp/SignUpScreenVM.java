package mfl.com.ui.start.signUp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.webkit.MimeTypeMap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mfl.com.R;
import mfl.com.api.Client;
import mfl.com.api.Services;
import mfl.com.pojo.signup.SignUpRequest;
import mfl.com.session.checkNetwork.ConnectionDetector;
import mfl.com.ui.start.signIn.mainSignIn.SignInScreen;

public class SignUpScreenVM extends ViewModel {
    private Context context;
    private static final String TAG = SignUpScreenVM.class.getSimpleName();
    private ConnectionDetector cd;
    private Boolean isInternetPresent = false;
    public MutableLiveData<String> resultLD = new MutableLiveData<>();
    private ProgressDialog progressDialog;
    private Services services;
    private String userFirstName, userLastName, userEmail, userPhone, userId, userCardImage;
    private ByteArrayOutputStream outputStream;
    private SweetAlertDialog sweetAlertDialog;
    private byte[] byteArray;
    private Single<SignUpRequest> sendRequestObservable;
    private SingleObserver<SignUpRequest> sendRequestObserver;



    public void initVM(Activity activity) {
        this.context = activity;

        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(activity.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);

        services = Client.getClient().create(Services.class);

        outputStream = new ByteArrayOutputStream();

        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
        sweetAlertDialog.setTitle(context.getResources().getString(R.string.applicationSuccessfully));
        sweetAlertDialog.setContentText(context.getResources().getString(R.string.applicationSuccessfully_purpose1));
        sweetAlertDialog.setConfirmText(context.getResources().getString(R.string.ok));
        sweetAlertDialog.setCancelable(false);

    }


    public void checkData(String firstName, String lastName, String email, String phone, String id, Bitmap imageUri, String imgExtension) {
        if (firstName.isEmpty() || firstName.length() <= 2) {
            resultLD.setValue("invalid FirstName");
            Log.d(TAG, "Mohameek checkData: invalid FirstName");
        } else {

            if (lastName.isEmpty() || lastName.length() <= 2) {
                resultLD.setValue("invalid LastName");
                Log.d(TAG, "Mohameek checkData: invalid LastName");

            } else {

                if (email.isEmpty() || email.length() <= 10 || !email.contains("@") || !email.contains(".")) {
                    resultLD.setValue("invalid Email");
                    Log.d(TAG, "Mohameek checkData: invalid Email");

                } else {
                    if (phone.isEmpty() || phone.length() < 11) {
                        resultLD.setValue("invalid Phone");
                        Log.d(TAG, "Mohameek checkData: invalid Phone");

                    } else {

                        if (id.isEmpty()) {
                            resultLD.setValue("invalid Id");
                            Log.d(TAG, "Mohameek checkData: invalid Id");

                        } else {

                            if (imageUri == null) {
                                resultLD.setValue("invalid card Image");
                                Log.d(TAG, "Mohameek checkData: invalid card Image");

                            } else {
                                this.userEmail = email;
                                this.userFirstName = firstName;
                                this.userLastName = lastName;
                                this.userPhone = phone;
                                this.userId = id;
                                imageUri.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                                byteArray = outputStream.toByteArray();
                                this.userCardImage = "data:image/"+imgExtension+";base64,"+ Base64.encodeToString(byteArray, Base64.DEFAULT);
                                Log.d(TAG, "Mohameek  imgInBase64=" + userCardImage);

                                 checkInternetConnection();

                            }

                        }
                    }


                }
            }


        }


    }

    private void checkInternetConnection() {
        cd = new ConnectionDetector(context);
        isInternetPresent = cd.isConnectingToInternet();

        if (!isInternetPresent) {
            Log.d(TAG, "Mohameek checkInternetConnection:  !isInternetPresent");
            resultLD.setValue("noInternetConnection");
        } else {
            progressDialog.show();


            Log.d(TAG, "Mohameek checkInternetConnection:  isInternetPresent");
            sendRequest();


        }
    }

    private void sendRequest() {

        sendRequestObservable = services.getSignUpRequest(userFirstName, userLastName, userEmail, userPhone, userId, userCardImage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


        sendRequestObserver = new SingleObserver<SignUpRequest>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "Mohameek sendRequest:  subscribe Successfully");

            }

            @Override
            public void onSuccess(SignUpRequest result) {

                if (result.getStatus()) {
                    progressDialog.dismiss();
                    showDialog();
                    Log.d(TAG, "Mohameek sendRequest: true: "+result.getMessage());

                } else {
                    progressDialog.dismiss();

                    Log.d(TAG, "Mohameek sendRequest: false: "+result.getMessage());

                }



            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();

                Log.d(TAG, "Mohameek sendRequest:error request : "+e.getStackTrace().toString());


            }

        };
        sendRequestObservable.subscribe(sendRequestObserver);


}

    private void showDialog() {

        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                     @Override
                                                     public void onClick(SweetAlertDialog sDialog) {
                                                         context.startActivity(new Intent(context, SignInScreen.class));
                                                     }
                                                 }
        );

        sweetAlertDialog.show();
    }

}
