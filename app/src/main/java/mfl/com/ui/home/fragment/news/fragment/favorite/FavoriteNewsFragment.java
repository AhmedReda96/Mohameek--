package mfl.com.ui.home.fragment.news.fragment.favorite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mfl.com.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteNewsFragment extends Fragment {

    public FavoriteNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_favorite_news, container, false);
    }
}
