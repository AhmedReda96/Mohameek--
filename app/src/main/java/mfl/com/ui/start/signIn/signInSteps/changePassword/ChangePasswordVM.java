package mfl.com.ui.start.signIn.signInSteps.changePassword;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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
import mfl.com.pojo.changePassword.ChangePasswordResponse;
import mfl.com.session.GeneralMethods;
import mfl.com.session.sp.TestLogin;
import mfl.com.ui.start.signIn.signInSteps.stepsHome.SignInStepsHome;

public class ChangePasswordVM extends ViewModel {
    private Context context;
    private static final String TAG = ChangePasswordVM.class.getSimpleName();
    private GeneralMethods generalMethods;
    public MutableLiveData<String> resultLD = new MutableLiveData<>();
    private ProgressDialog progressDialog;
    private Services services;
    private String userPassword, userConfirmPassword;
    private Single<ChangePasswordResponse> sendRequestObservable;
    private SingleObserver<ChangePasswordResponse> sendRequestObserver;
    private TestLogin testLogin;

    public void initVM(Activity activity) {
        this.context = activity;
        testLogin = new TestLogin(context);
        generalMethods = new GeneralMethods(context);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(activity.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        services = Client.getClient().create(Services.class);
    }


    public void checkData(String password, String confirmPassword) {
        resultLD.setValue("resetError");
        if (password.length() < 5) {
            resultLD.setValue("invalid password");
            Log.d(TAG, "Mohameek checkData: invalid password");

        } else {

            if (confirmPassword.length() < 5) {
                resultLD.setValue("invalid confirmPassword1");
                Log.d(TAG, "Mohameek checkData: invalid confirmPassword");
            } else {
                if (!confirmPassword.equals(password)) {
                    resultLD.setValue("invalid confirmPassword2");
                    Log.d(TAG, "Mohameek checkData: invalid confirmPassword notMatched");
                } else {
                    this.userPassword = password;
                    this.userConfirmPassword = confirmPassword;
                    checkInternetConnection();

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

        sendRequestObservable = services.getChangePasswordRequest(userPassword, userConfirmPassword, testLogin.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


        sendRequestObserver = new SingleObserver<ChangePasswordResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "Mohameek sendRequest subscribe: Successfully");

            }

            @Override
            public void onSuccess(ChangePasswordResponse result) {

                if (result.getStatus()) {
                    Log.d(TAG, "Mohameek sendRequest: onSuccess true: ");
                    ((SignInStepsHome) context).selectIndex(1);
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

        };
        sendRequestObservable.subscribe(sendRequestObserver);
    }

}
