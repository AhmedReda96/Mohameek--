package mfl.com.ui.home.fragment.news.fragment.New;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mfl.com.api.Client;
import mfl.com.api.Services;
import mfl.com.pojo.news.NewNewsList;
import mfl.com.pojo.news.NewNewsResponse;
import mfl.com.session.GeneralMethods;
import mfl.com.session.sp.TestLogin;

public class NewNewsVM extends ViewModel {
    private static final String TAG = NewNewsVM.class.getSimpleName();
    public MutableLiveData<String> resultLD = new MutableLiveData<>();
    private Observable<NewNewsResponse> newNewsRequestObservable;
    private Observer<NewNewsResponse> newNewsRequestObserver;
    private Services services;
    private Context context;
    private TestLogin testLogin;
    public MutableLiveData<List<NewNewsList>> newsList = new MutableLiveData<>();
    private GeneralMethods generalMethods;


    public void initVM(Activity activity) {
        this.context = activity;
        generalMethods=new GeneralMethods(context);
        testLogin=new TestLogin(context);
        services = Client.getClient().create(Services.class);
    }

    public void getData(int page, ProgressBar progress) {

        newNewsRequestObservable = services.getNewsRequestTest(page,testLogin.getToken()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        newNewsRequestObserver = new Observer<NewNewsResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(NewNewsResponse newNewsResponse) {
                if (newNewsResponse.getMessage().getFrom() != null) {
                    progress.setVisibility(View.VISIBLE);
                    newsList.setValue(newNewsResponse.getMessage().getData());

                } else {
                    progress.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        newNewsRequestObservable.subscribe(newNewsRequestObserver);


    }

    public void checkConnection() {
        if (!generalMethods.checkInternet(context)) {
            Log.d(TAG, "Mohameek checkInternetConnection:  !isInternetPresent");
            resultLD.setValue("noInternetConnection");
        } else {
            Log.d(TAG, "Mohameek checkInternetConnection:  isInternetPresent");
            resultLD.setValue("InternetConnection");
        }
    }


}
