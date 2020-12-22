package mfl.com.ui.start.signUp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.ByteArrayOutputStream;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mfl.com.R;
import mfl.com.api.Client;
import mfl.com.api.Services;
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
    private String imgInBase64;
    private ByteArrayOutputStream outputStream;
    private SweetAlertDialog sweetAlertDialog;
    private byte[] byteArray;

    //BuildConfig.THE_MOVIE_DB_API_TOKEN


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


    public void checkData(String firstName, String lastName, String email, String phone, String id, Bitmap imageUri) {
        if (firstName.isEmpty() || firstName.length() <= 2) {
            resultLD.setValue("invalid FirstName");
            Log.d(TAG, "Mohameek checkData: invalid FirstName");
        } else {

            if (lastName.isEmpty() || lastName.length() <= 2) {
                resultLD.setValue("invalid LastName");
                Log.d(TAG, "Mohameek checkData: invalid LastName");

            } else {

                if (email.isEmpty() || email.length() <= 10 || !email.contains("@")) {
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
                                imageUri.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                                byteArray = outputStream.toByteArray();
                                imgInBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                                Log.d(TAG, "Mohameek  imgInBase64=" + imgInBase64);

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
            //  progressDialog.dismiss();
            // checkMutableLiveData.setValue("noInternetConnection");
        } else {
            // progressDialog.show();
            showDialog();

            Log.d(TAG, "Mohameek checkInternetConnection:  isInternetPresent");
            //checkDataFounded();


        }
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
