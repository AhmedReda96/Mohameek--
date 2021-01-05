package mfl.com.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import mfl.com.db.news.NewsDao;
import mfl.com.db.news.NewsEntity;

@Database(entities = {NewsEntity.class}, version = 1,exportSchema = false)
public abstract class MainDataBase extends RoomDatabase {
    private static MainDataBase instance;

    public abstract NewsDao productDao();


    public static synchronized MainDataBase getInstance(final Context context) {
        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(), MainDataBase.class, "database")
                    .fallbackToDestructiveMigration()
                    .build();


        }
        return instance;
    }
}