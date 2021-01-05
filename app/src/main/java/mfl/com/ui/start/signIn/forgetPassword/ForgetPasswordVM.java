package mfl.com.ui.start.signIn.forgetPassword;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import mfl.com.R;
import mfl.com.session.GeneralMethods;
import mfl.com.session.sp.TestLogin;
import mfl.com.ui.start.signIn.mainSignIn.SignInScreen;
import mfl.com.ui.start.signIn.resetPassword.ResetPasswordScreen;
import mfl.com.ui.start.signIn.signInSteps.stepsHome.SignInStepsHome;

public class ForgetPasswordVM extends ViewModel {
    private Context context;
    private static final String TAG = ForgetPasswordVM.class.getSimpleName();
    private GeneralMethods generalMethods;
    public MutableLiveData<String> resultLD = new MutableLiveData<>();
    private ProgressDialog progressDialog;
    private TestLogin testLogin;
    private String userPhone;
    private Activity activity;


    public void initVM(Activity activity) {
        this.context = activity;
        this.activity=activity;
        testLogin = new TestLogin(context);
        generalMethods = new GeneralMethods(context);
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(activity.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
    }

    public void checkData(String phone) {
        resultLD.setValue("error");
        if (phone.isEmpty() || phone.length() < 11) {
            resultLD.setValue("invalid Phone");
            Log.d(TAG, "Mohameek checkData: invalid Phone");

        } else {
                this.userPhone = phone;
                checkInternetConnection();

            }
        }

    private void checkInternetConnection() {


        if (!generalMethods.checkInternet(context)) {
            Log.d(TAG, "Mohameek checkInternetConnection:  !isInternetPresent");
            resultLD.setValue("noInternetConnection");
        } else {
            progressDialog.show();
            Log.d(TAG, "Mohameek checkInternetConnection:  isInternetPresent");
            checkNumber();


        }
    }

    private void checkNumber() {

    sendRequest();

    }

    private void sendRequest() {

        PhoneAuthProvider.getInstance().verifyPhoneNumber("+2" + userPhone, 60, TimeUnit.SECONDS
                , activity, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        Log.d(TAG, "Mohameek sendRequest :onVerificationCompleted ");

                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        Log.d(TAG, "Mohameek sendRequest:onCodeSent : " );

                        progressDialog.dismiss();
                        context.startActivity(new Intent(context, ResetPasswordScreen.class));


                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        resultLD.setValue("invalid Phone");
                        Log.d(TAG, "Mohameek sendRequest:error request :onVerificationFailed: "+e.getStackTrace().toString());

                    }
                });

    }


}
