package mfl.com.ui.home.fragment.schedule;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mfl.com.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecheduleFragment extends Fragment {

    public SecheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sechedule, container, false);
    }
}
