package mfl.com.ui.start.signIn.signInSteps.addTime;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.suke.widget.SwitchButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mfl.com.R;
import mfl.com.databinding.FragmentAddTimeBinding;
import mfl.com.pojo.workTimes.WorkTimesModel;
import mfl.com.pojo.workTimes.WorkTimesRequest;
import mfl.com.session.GeneralMethods;
import mfl.com.session.sp.StoreLanguageData;
import mfl.com.ui.start.signIn.signInSteps.stepsHome.SignInStepsHome;

public class AddTime extends Fragment implements View.OnClickListener {
    private AddTimesVM viewModel;
    private FragmentAddTimeBinding binding;
    private GeneralMethods generalMethods;
    private List<String> durationTimesList = new ArrayList<>();
    private StoreLanguageData storeLanguageData;
    private String time = "";
    private List<WorkTimesRequest> workTimesModels = new ArrayList<>();
    private List<Integer> daysCheckedList = new ArrayList<>();
    private int satDuration = 0, sunDuration = 0, monDuration = 0, tueDuration = 0, wedDuration = 0, thuDuration = 0, friDuration = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddTimeBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        viewModel = ViewModelProviders.of(getActivity()).get(AddTimesVM.class);
        viewModel.initVM(getActivity());
        generalMethods = new GeneralMethods(getActivity());
        generalMethods.changeLanguage();
        generalMethods.setDirection(binding.mainLin);
        storeLanguageData = new StoreLanguageData(getActivity());
        setSpinner();
        listenOnSwitchers();
        setTextTime();
        listenOnSpinners();

        binding.saturdayCard.setOnClickListener(this::onClick);
        binding.sundayCard.setOnClickListener(this::onClick);
        binding.mondayCard.setOnClickListener(this::onClick);
        binding.tuesdayCard.setOnClickListener(this::onClick);
        binding.wednesdayCard.setOnClickListener(this::onClick);
        binding.thursdayCard.setOnClickListener(this::onClick);
        binding.fridayCard.setOnClickListener(this::onClick);

        binding.saturdayToHourBtn.setOnClickListener(this::onClick);
        binding.sundayToHourBtn.setOnClickListener(this::onClick);
        binding.mondayToHourBtn.setOnClickListener(this::onClick);
        binding.tuesdayToHourBtn.setOnClickListener(this::onClick);
        binding.wednesdayToHourBtn.setOnClickListener(this::onClick);
        binding.thursdayToHourBtn.setOnClickListener(this::onClick);
        binding.fridayToHourBtn.setOnClickListener(this::onClick);


        binding.saturdayFromHourBtn.setOnClickListener(this::onClick);
        binding.sundayFromHourBtn.setOnClickListener(this::onClick);
        binding.mondayFromHourBtn.setOnClickListener(this::onClick);
        binding.tuesdayFromHourBtn.setOnClickListener(this::onClick);
        binding.wednesdayFromHourBtn.setOnClickListener(this::onClick);
        binding.thursdayFromHourBtn.setOnClickListener(this::onClick);
        binding.fridayFromHourBtn.setOnClickListener(this::onClick);


        binding.nextBtn.setOnClickListener(this::onClick);
        listenerOnLiveData();

    }

    private void listenerOnLiveData() {

        viewModel.resultLD.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String result) {
                binding.error.setVisibility(View.VISIBLE);
                switch (result) {

                    case "invalid duration":
                        binding.error.setText(getResources().getString(R.string.invalidDuration));
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.invalidDuration), Snackbar.LENGTH_LONG).show();

                        break;
                    case "resetError":
                        binding.error.setText("");
                        break;
                    case "invalid time":
                        binding.error.setText(getResources().getString(R.string.invalidTime));
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.invalidTime), Snackbar.LENGTH_LONG).show();

                        break;
                    case "noInternetConnection":
                        binding.error.setText(getResources().getString(R.string.noInternetConnection));
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.noInternetConnection), Snackbar.LENGTH_LONG).show();

                        break;
                    case "invalid ServerError":
                        binding.error.setText(getResources().getString(R.string.serverError));
                        Snackbar.make(binding.mainLin, getResources().getString(R.string.serverError), Snackbar.LENGTH_LONG).show();

                        break;

                }
            }
        });


    }


    private void listenOnSpinners() {
        binding.saturdayBookTimeSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                satDuration = Integer.parseInt(item.substring(0, item.indexOf(" ")));


            }
        });
        binding.sundayBookTimeSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                sunDuration = Integer.parseInt(item.substring(0, item.indexOf(" ")));
            }
        });
        binding.mondayBookTimeSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                monDuration = Integer.parseInt(item.substring(0, item.indexOf(" ")));
            }
        });
        binding.thursdayBookTimeSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                thuDuration = Integer.parseInt(item.substring(0, item.indexOf(" ")));
            }
        });
        binding.wednesdayBookTimeSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                wedDuration = Integer.parseInt(item.substring(0, item.indexOf(" ")));

            }
        });
        binding.thursdayBookTimeSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                thuDuration = Integer.parseInt(item.substring(0, item.indexOf(" ")));
            }
        });
        binding.fridayBookTimeSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                friDuration =Integer.parseInt(item.substring(0, item.indexOf(" ")));

            }
        });


    }

    private void setTextTime() {
        if (storeLanguageData.getAppLanguage().equals("ar")) {
            binding.saturdayFromHourBtn.setText("٠٩:٠٠ص");
            binding.saturdayToHourBtn.setText("٠٩:٠٠ م");
            binding.sundayFromHourBtn.setText("٠٩:٠٠ ص");
            binding.sundayToHourBtn.setText("٠٩:٠٠ م");
            binding.mondayFromHourBtn.setText("٠٩:٠٠ ص");
            binding.mondayToHourBtn.setText("٠٩:٠٠ م");
            binding.tuesdayFromHourBtn.setText("٠٩:٠٠ ص");
            binding.tuesdayToHourBtn.setText("٠٩:٠٠ م");
            binding.wednesdayFromHourBtn.setText("٠٩:٠٠ ص");
            binding.wednesdayToHourBtn.setText("٠٩:٠٠ م");
            binding.thursdayFromHourBtn.setText("٠٩:٠٠ ص");
            binding.thursdayToHourBtn.setText("٠٩:٠٠ م");
            binding.fridayFromHourBtn.setText("٠٩:٠٠ ص");
            binding.fridayToHourBtn.setText("٠٩:٠٠ م");


        } else {

            binding.saturdayFromHourBtn.setText("09:00 AM");
            binding.saturdayToHourBtn.setText("09:00 PM");
            binding.sundayFromHourBtn.setText("09:00 AM");
            binding.sundayToHourBtn.setText("09:00 PM");
            binding.mondayFromHourBtn.setText("09:00 AM");
            binding.mondayToHourBtn.setText("09:00 PM");
            binding.tuesdayFromHourBtn.setText("09:00 AM");
            binding.tuesdayToHourBtn.setText("09:00 PM");
            binding.wednesdayFromHourBtn.setText("09:00 AM");
            binding.wednesdayToHourBtn.setText("09:00 PM");
            binding.thursdayFromHourBtn.setText("09:00 AM");
            binding.thursdayToHourBtn.setText("09:00 PM");
            binding.fridayFromHourBtn.setText("09:00 AM");
            binding.fridayToHourBtn.setText("09:00 PM");


        }


    }

    private void listenOnSwitchers() {

        binding.saturdaySwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (isChecked) {
                    binding.saturdayTimeLin.setVisibility(View.VISIBLE);
                    daysCheckedList.add(1);

                } else {
                    binding.saturdayTimeLin.setVisibility(View.GONE);
                    daysCheckedList.remove(Integer.valueOf(1));

                }
            }
        });


        binding.sundaySwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (isChecked) {
                    binding.sundayTimeLin.setVisibility(View.VISIBLE);
                    daysCheckedList.add(2);

                } else {
                    binding.sundayTimeLin.setVisibility(View.GONE);
                    daysCheckedList.remove(Integer.valueOf(2));

                }
            }
        });


        binding.mondaySwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (isChecked) {
                    binding.mondayTimeLin.setVisibility(View.VISIBLE);
                    daysCheckedList.add(3);


                } else {
                    binding.mondayTimeLin.setVisibility(View.GONE);
                    daysCheckedList.remove(Integer.valueOf(3));

                }
            }
        });


        binding.tuesdaySwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (isChecked) {
                    binding.tuesdayTimeLin.setVisibility(View.VISIBLE);
                    daysCheckedList.add(4);

                } else {
                    binding.tuesdayTimeLin.setVisibility(View.GONE);
                    daysCheckedList.remove(Integer.valueOf(4));

                }
            }
        });

        binding.wednesdaySwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (isChecked) {
                    binding.wednesdayTimeLin.setVisibility(View.VISIBLE);
                    daysCheckedList.add(5);

                } else {
                    binding.wednesdayTimeLin.setVisibility(View.GONE);
                    daysCheckedList.remove(Integer.valueOf(5));

                }
            }
        });

        binding.thursdaySwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (isChecked) {
                    binding.thursdayTimeLin.setVisibility(View.VISIBLE);
                    daysCheckedList.add(6);

                } else {
                    binding.thursdayTimeLin.setVisibility(View.GONE);
                    daysCheckedList.remove(Integer.valueOf(6));

                }
            }
        });

        binding.fridaySwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (isChecked) {
                    binding.fridayTimeLin.setVisibility(View.VISIBLE);
                    daysCheckedList.add(7);

                } else {
                    binding.fridayTimeLin.setVisibility(View.GONE);
                    daysCheckedList.remove(Integer.valueOf(7));

                }
            }
        });


    }

    private void setSpinner() {
        if (storeLanguageData.getAppLanguage().equals("ar")) {
            durationTimesList.add("10 دقائق");
            durationTimesList.add("15 دقيقة");
            durationTimesList.add("20 دقيقة");
            durationTimesList.add("25 دقيقة");
            durationTimesList.add("30 دقيقة");
            durationTimesList.add("35 دقيقة");
            durationTimesList.add("40 دقيقة");
            durationTimesList.add("45 دقيقة");
            durationTimesList.add("50 دقيقة");
            durationTimesList.add("60 دقيقة");
        } else {
            durationTimesList.add("10 Minute");
            durationTimesList.add("15 Minute");
            durationTimesList.add("20 Minute");
            durationTimesList.add("25 Minute");
            durationTimesList.add("30 Minute");
            durationTimesList.add("35 Minute");
            durationTimesList.add("40 Minute");
            durationTimesList.add("45 Minute");
            durationTimesList.add("50 Minute");
            durationTimesList.add("60 Minute");
        }
        binding.saturdayBookTimeSpinner.setItems(durationTimesList);
        binding.sundayBookTimeSpinner.setItems(durationTimesList);
        binding.mondayBookTimeSpinner.setItems(durationTimesList);
        binding.tuesdayBookTimeSpinner.setItems(durationTimesList);
        binding.wednesdayBookTimeSpinner.setItems(durationTimesList);
        binding.thursdayBookTimeSpinner.setItems(durationTimesList);
        binding.fridayBookTimeSpinner.setItems(durationTimesList);


    }

    @Override
    public void onClick(View v) {
        if (binding.nextBtn.equals(v)) {
            if (daysCheckedList.size() > 0) {
                addValuesOfDays();
            } else {

                binding.error.setText(getActivity().getResources().getString(R.string.chooseAppointemnt));
                Snackbar.make(binding.mainLin, getResources().getString(R.string.chooseAppointemnt), Snackbar.LENGTH_LONG).show();

            }
            ((SignInStepsHome) getActivity()).selectIndex(4);

        }

        if (binding.saturdayCard.equals(v)) {
            if (!binding.saturdaySwitch.isChecked())
                binding.saturdaySwitch.setChecked(true);
        }
        if (binding.sundayCard.equals(v)) {
            if (!binding.sundaySwitch.isChecked())
                binding.sundaySwitch.setChecked(true);
        }
        if (binding.mondayCard.equals(v)) {
            if (!binding.mondaySwitch.isChecked())
                binding.mondaySwitch.setChecked(true);
        }
        if (binding.tuesdayCard.equals(v)) {
            if (!binding.tuesdaySwitch.isChecked())
                binding.tuesdaySwitch.setChecked(true);
        }
        if (binding.wednesdayCard.equals(v)) {
            if (!binding.wednesdaySwitch.isChecked())
                binding.wednesdaySwitch.setChecked(true);
        }
        if (binding.thursdayCard.equals(v)) {
            if (!binding.thursdaySwitch.isChecked())
                binding.thursdaySwitch.setChecked(true);
        }
        if (binding.fridayCard.equals(v)) {
            if (!binding.fridaySwitch.isChecked())
                binding.fridaySwitch.setChecked(true);
        }


        if (binding.saturdayFromHourBtn.equals(v)) {
            setTimePicker("f1");

        }
        if (binding.saturdayToHourBtn.equals(v)) {
            setTimePicker("t1");

        }
        if (binding.sundayFromHourBtn.equals(v)) {
            setTimePicker("f2");

        }
        if (binding.sundayToHourBtn.equals(v)) {
            setTimePicker("t2");

        }
        if (binding.mondayFromHourBtn.equals(v)) {
            setTimePicker("f3");

        }
        if (binding.mondayToHourBtn.equals(v)) {
            setTimePicker("t3");

        }
        if (binding.tuesdayFromHourBtn.equals(v)) {
            setTimePicker("f4");

        }
        if (binding.tuesdayToHourBtn.equals(v)) {
            setTimePicker("t4");

        }
        if (binding.wednesdayFromHourBtn.equals(v)) {
            setTimePicker("f5");

        }
        if (binding.wednesdayToHourBtn.equals(v)) {
            setTimePicker("t5");

        }
        if (binding.thursdayFromHourBtn.equals(v)) {
            setTimePicker("f6");

        }
        if (binding.thursdayToHourBtn.equals(v)) {
            setTimePicker("t6");

        }
        if (binding.fridayFromHourBtn.equals(v)) {
            setTimePicker("f7");

        }
        if (binding.fridayToHourBtn.equals(v)) {
            setTimePicker("t7");

        }


    }

    private void addValuesOfDays() {
        workTimesModels.clear();
        for (int i = 0; i < daysCheckedList.size(); i++) {
            switch (daysCheckedList.get(i)) {
                case 1:
                    workTimesModels.add(new WorkTimesRequest(1, binding.saturdayFromHourBtn.getText().toString().trim(),
                            binding.saturdayToHourBtn.getText().toString().trim(), satDuration));
                    break;
                case 2:
                    workTimesModels.add(new WorkTimesRequest(2, binding.sundayFromHourBtn.getText().toString().trim(),
                            binding.sundayToHourBtn.getText().toString().trim(), sunDuration));
                    break;
                case 3:
                    workTimesModels.add(new WorkTimesRequest(3, binding.mondayFromHourBtn.getText().toString().trim(),
                            binding.mondayToHourBtn.getText().toString().trim(), monDuration));
                    break;
                case 4:
                    workTimesModels.add(new WorkTimesRequest(4, binding.tuesdayFromHourBtn.getText().toString().trim(),
                            binding.tuesdayToHourBtn.getText().toString().trim(), tueDuration));
                    break;
                case 5:
                    workTimesModels.add(new WorkTimesRequest(5, binding.wednesdayFromHourBtn.getText().toString().trim(),
                            binding.wednesdayToHourBtn.getText().toString().trim(), wedDuration));
                    break;
                case 6:
                    workTimesModels.add(new WorkTimesRequest(6, binding.thursdayFromHourBtn.getText().toString().trim(),
                            binding.thursdayToHourBtn.getText().toString().trim(), thuDuration));
                    break;
                case 7:
                    workTimesModels.add(new WorkTimesRequest(1, binding.fridayFromHourBtn.getText().toString().trim(),
                            binding.fridayToHourBtn.getText().toString().trim(), friDuration));
                    break;
            }


        }

        viewModel.checkData(workTimesModels);
    }

    private void setTimePicker(String dayNum) {
        time = "";
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), android.R.style.Theme_DeviceDefault_Dialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int min) {

                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm", new Locale(storeLanguageData.getAppLanguage()));

                try {
                    Date date = f24Hours.parse(String.valueOf(hourOfDay + ":" + min));
                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa", new Locale(storeLanguageData.getAppLanguage()));


                    time = f12Hours.format(date);
                    switch (dayNum) {
                        case "f1":
                            binding.saturdayFromHourBtn.setText(time);
                            break;
                        case "t1":
                            binding.saturdayToHourBtn.setText(time);
                            break;
                        case "f2":
                            binding.sundayFromHourBtn.setText(time);
                            break;
                        case "t2":
                            binding.sundayToHourBtn.setText(time);
                            break;
                        case "f3":
                            binding.mondayFromHourBtn.setText(time);
                            break;
                        case "t3":
                            binding.mondayToHourBtn.setText(time);
                            break;
                        case "f4":
                            binding.tuesdayFromHourBtn.setText(time);
                            break;
                        case "t4":
                            binding.tuesdayToHourBtn.setText(time);
                            break;
                        case "f5":
                            binding.wednesdayFromHourBtn.setText(time);
                            break;
                        case "t5":
                            binding.wednesdayToHourBtn.setText(time);
                            break;
                        case "f6":
                            binding.thursdayFromHourBtn.setText(time);
                            break;
                        case "t6":
                            binding.thursdayToHourBtn.setText(time);
                            break;
                        case "f7":
                            binding.fridayFromHourBtn.setText(time);
                            break;
                        case "t7":
                            binding.fridayToHourBtn.setText(time);
                            break;

                    }


                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        }, 12, 0, false
        );
        timePickerDialog.show();
    }


}
