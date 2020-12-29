package mfl.com.ui.start.signIn.mainSignIn;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.ByteArrayOutputStream;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mfl.com.R;
import mfl.com.api.Client;
import mfl.com.api.Services;
import mfl.com.pojo.signin.SignInRequest;
import mfl.com.pojo.signup.SignUpRequest;
import mfl.com.session.checkNetwork.ConnectionDetector;
import mfl.com.ui.home.mainHome.HomeActivity;
import mfl.com.ui.start.signUp.SignUpScreenVM;

public class SignInScreenVM extends ViewModel {
    private Context context;
    private static final String TAG = SignInScreenVM.class.getSimpleName();
    private ConnectionDetector cd;
    private Boolean isInternetPresent = false;
    public MutableLiveData<String> resultLD = new MutableLiveData<>();
    private ProgressDialog progressDialog;
    private Services services;
    private String userId, userPassword;
    private Single<SignInRequest> sendRequestObservable;
    private SingleObserver<SignInRequest> sendRequestObserver;


    public void initVM(Activity activity) {
        this.context = activity;

        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(activity.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);

        services = Client.getClient().create(Services.class);


    }

    public void checkData(String id, String password) {

        if (id.isEmpty()) {
            resultLD.setValue("invalid id");
            Log.d(TAG, "Mohameek checkData: invalid Id");

        } else {

            if (password.length() < 5) {
                resultLD.setValue("invalid password");
                Log.d(TAG, "Mohameek checkData: invalid password");

            } else {
                this.userId = id;
                this.userPassword = password;
                checkInternetConnection();

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

        sendRequestObservable = services.getSignInRequest(userId,userPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


        sendRequestObserver = new SingleObserver<SignInRequest>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "Mohameek sendRequest:  subscribe Successfully");

            }

            @Override
            public void onSuccess(SignInRequest result) {

                if (result.getStatus()) {
                    Log.d(TAG, "Mohameek sendRequest: true: "+result.getAccessToken());
                    context.startActivity(new Intent(context, HomeActivity.class));
                    progressDialog.dismiss();


                } else {
                    progressDialog.dismiss();

                    Log.d(TAG, "Mohameek sendRequest: false: ");

                }



            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                resultLD.setValue("invalid idOrPass");
                Log.d(TAG, "Mohameek sendRequest:error request : "+e.getMessage());


            }

        };
        sendRequestObservable.subscribe(sendRequestObserver);



    }


}
