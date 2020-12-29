package mfl.com.ui.home.fragment.schedule.mainSchedule;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.google.android.material.appbar.AppBarLayout;

import mfl.com.R;
import mfl.com.databinding.FragmentScheduleBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.ui.home.fragment.schedule.orderDetails.OrderDetailsScreen;


public class ScheduleFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {
    private FragmentScheduleBinding binding;
    private boolean isHideToolbarView = false;
    private String selectedDate = "";
    private GeneralMethods generalMethods;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentScheduleBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getActivity());
        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }


    private void init() {
        generalMethods=new GeneralMethods(getActivity());
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");
        binding.appbar.addOnOffsetChangedListener(this);
        binding.collapsingToolbar.setTitle("");


        binding.calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = String.valueOf(dayOfMonth + "-" + month + "-" + year);
                Log.d("TAG", "onSelectedDayChange: " + selectedDate);
            }
        });

        binding.lin.setOnClickListener(this::onClick);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        if (percentage == 1f && isHideToolbarView) {
            binding.titleAppbar.setVisibility(View.VISIBLE);
            binding.toolbar.setVisibility(View.VISIBLE);
            binding.toolbar.setBackgroundColor(getResources().getColor(R.color.mainColor));
            binding.dayToolbar.setText(selectedDate);
            isHideToolbarView = !isHideToolbarView;


        } else if (percentage < 1f && !isHideToolbarView) {
            binding.toolbar.setVisibility(View.GONE);
            binding.titleAppbar.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;
            binding.toolbar.setBackgroundColor(Color.TRANSPARENT);


        }
    }

    @Override
    public void onClick(View v) {
        if (binding.lin.equals(v)) {
            startActivity(new Intent(getActivity(), OrderDetailsScreen.class));
        }
    }
}
