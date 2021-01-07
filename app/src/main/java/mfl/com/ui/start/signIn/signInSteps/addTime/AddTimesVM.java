package mfl.com.ui.start.signIn.signInSteps.addTime;

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
import mfl.com.pojo.location.Cities;
import mfl.com.pojo.workTimes.AddWorkTimesRequest;
import mfl.com.pojo.workTimes.AddWorkTimesResponse;
import mfl.com.pojo.workTimes.WorkTimesModel;
import mfl.com.pojo.workTimes.WorkTimesRequest;
import mfl.com.session.GeneralMethods;
import mfl.com.session.sp.TestLogin;
import mfl.com.ui.start.signIn.signInSteps.addLocation.AddLocationVM;
import mfl.com.ui.start.signIn.signInSteps.stepsHome.SignInStepsHome;

public class AddTimesVM extends ViewModel {
    private Context context;
    private static final String TAG = AddTimesVM.class.getSimpleName();
    private GeneralMethods generalMethods;
    public MutableLiveData<String> resultLD = new MutableLiveData<>();
    private ProgressDialog progressDialog;
    private Services services;
    private TestLogin testLogin;
    private Intent intent;
    private boolean durationFlag = true, timeFlag = true;
    private List<WorkTimesRequest> workTimesModels = new ArrayList<>();

    public void initVM(Activity activity) {
        this.context = activity;
        testLogin = new TestLogin(context);
        generalMethods = new GeneralMethods(context);
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(activity.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        services = Client.getClient().create(Services.class);
    }

    public void checkData(List<WorkTimesRequest> daysCheckedList) {
        durationFlag = true;
        timeFlag = true;
        workTimesModels.clear();
        resultLD.setValue("resetError");
        for (int i = 0; i < daysCheckedList.size(); i++) {
            if (daysCheckedList.get(i).getDuration() == 0) {
                resultLD.setValue("invalid duration");
                durationFlag = false;
            } else {
                if (daysCheckedList.get(i).getStartAt().equals(daysCheckedList.get(i).getEndAt())) {
                    resultLD.setValue("invalid time");
                    timeFlag = false;
                } else {
                    workTimesModels.add(new WorkTimesRequest(daysCheckedList.get(i).getDay(), daysCheckedList.get(i).getStartAt(), daysCheckedList.get(i).getEndAt(), daysCheckedList.get(i).getDuration()));
                }
            }

        }
        if (durationFlag&&timeFlag) {
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
            sendRequest();
        }
    }

    private void sendRequest() {
        services.setWorkTimesRequest(new AddWorkTimesRequest(testLogin.getToken(), workTimesModels)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<AddWorkTimesResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(AddWorkTimesResponse addWorkTimesResponse) {
                Log.d(TAG, "Mohameek sendRequest: onSuccess true: ");
                ((SignInStepsHome) context).selectIndex(4);
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
