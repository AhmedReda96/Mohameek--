package mfl.com.ui.home.fragment.news.details;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mfl.com.db.MainDataBase;
import mfl.com.db.news.NewsDao;
import mfl.com.db.news.NewsEntity;
import mfl.com.ui.home.fragment.news.fragment.New.NewNewsVM;

public class NewsDetailsVM extends ViewModel {
    private Context context;
    private static final String TAG = NewsDetailsVM.class.getSimpleName();
    public MutableLiveData<String> resultLD = new MutableLiveData<>();

    private NewsDao newsDao;


    public void initVM(Activity activity) {

        this.context = activity;
        newsDao = MainDataBase.getInstance(context).productDao();

    }


    public void checkId(int id) {
        newsDao.existsNewsId(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Boolean result) {

                        if (result) {
                            resultLD.setValue("existedId");
                            Log.d(TAG, "Mohameek onSuccess:  true existedId");

                        } else {
                            resultLD.setValue("notExistedId");

                            Log.d(TAG, "Mohameek onSuccess:  false existedId");


                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Mohameek onError:existedId  " + e.getMessage());

                    }
                });


    }


    public void deleteNewsFromRoom(int id) {

        newsDao.deleteNewsItem(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Mohameek onComplete:deleteNewsFromRoom  " );

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Mohameek onError:deleteNewsFromRoom-  " + e.getMessage());

                    }
                });

    }

    public void insertNewsToRoom(NewsEntity newsEntity) {

        newsDao.insertNewsItem(newsEntity).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Mohameek onComplete:insertNewsToRoom  " );

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Mohameek onError:insertNewsToRoom - " + e.getMessage());

                    }
                });

    }
}
