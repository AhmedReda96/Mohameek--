package mfl.com.ui.home.fragment.news.fragment.favorite;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mfl.com.db.MainDataBase;
import mfl.com.db.news.NewsDao;
import mfl.com.db.news.NewsEntity;
import mfl.com.pojo.news.NewNewsList;
import mfl.com.ui.home.fragment.news.details.NewsDetailsVM;

public class FavoriteNewsVM extends ViewModel {
    private Context context;
    private static final String TAG = FavoriteNewsVM.class.getSimpleName();
    public MutableLiveData<String> resultLD = new MutableLiveData<>();
    public MutableLiveData<List<NewsEntity>> favoriteNewsList = new MutableLiveData<>();
    private NewsDao newsDao;


    public void initVM(Activity activity) {
        this.context = activity;
        newsDao = MainDataBase.getInstance(context).productDao();

    }

    public void getData() {
        newsDao.getFavoriteNewsData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NewsEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "Mohameek onSubscribe: getData ");

                    }

                    @Override
                    public void onNext(List<NewsEntity> newsEntities) {
                        favoriteNewsList.setValue(newsEntities);
                        Log.d(TAG, "Mohameek onNext: getData " + String.valueOf(newsEntities.size()));

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Mohameek onError:getData  " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


}
