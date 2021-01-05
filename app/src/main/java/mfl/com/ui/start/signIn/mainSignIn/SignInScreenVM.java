package mfl.com.ui.start.signIn.mainSignIn;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mfl.com.R;
import mfl.com.api.Client;
import mfl.com.api.Services;
import mfl.com.pojo.signin.SignInResponse;
import mfl.com.session.GeneralMethods;
import mfl.com.session.sp.TestLogin;
import mfl.com.ui.start.signIn.signInSteps.stepsHome.SignInStepsHome;

public class SignInScreenVM extends ViewModel {
    private Context context;
    private static final String TAG = SignInScreenVM.class.getSimpleName();
    private GeneralMethods generalMethods;
    public MutableLiveData<String> resultLD = new MutableLiveData<>();
    private ProgressDialog progressDialog;
    private Services services;
    private String userId, userPassword;
    private Single<SignInResponse> sendRequestObservable;
    private SingleObserver<SignInResponse> sendRequestObserver;
    private TestLogin testLogin;
    private Intent intent;

    public void initVM(Activity activity) {
        this.context = activity;
        testLogin = new TestLogin(context);
        generalMethods = new GeneralMethods(context);
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


        if (!generalMethods.checkInternet(context)) {
            Log.d(TAG, "Mohameek checkInternetConnection:  !isInternetPresent");
            resultLD.setValue("noInternetConnection");
        } else {
            progressDialog.show();
            Log.d(TAG, "Mohameek checkInternetConnection:  isInternetPresent");
            sendRequest();


        }
    }

    private void sendRequest() {

        sendRequestObservable = services.getSignInRequest(userId, userPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


        sendRequestObserver = new SingleObserver<SignInResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "Mohameek sendRequest:  subscribe Successfully");

            }

            @Override
            public void onSuccess(SignInResponse result) {

                if (result.getStatus()) {

                    intent = new Intent(context, SignInStepsHome.class);
                    //intent.putExtra("step", String.valueOf(result.getStep()));
                    intent.putExtra("step", String.valueOf(1));
                    testLogin.setToken(result.getAccessToken());
                    Log.d(TAG, "Mohameek sendRequest: token: " + testLogin.getToken());
                    progressDialog.dismiss();
                    context.startActivity(intent);


                } else {
                    progressDialog.dismiss();
                    resultLD.setValue("invalid idOrPass");
                    Log.d(TAG, "Mohameek sendRequest: false: ");

                }


            }

            @Override
            public void onError(Throwable e) {
                progressDialog.dismiss();
                resultLD.setValue("invalid idOrPass");
                Log.d(TAG, "Mohameek sendRequest:error request : " + e.getMessage());


            }

        };
        sendRequestObservable.subscribe(sendRequestObserver);
    }


}
