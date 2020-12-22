package mfl.com.ui.home.fragment.setting.accountSetting.editOfficeWorkingHours;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import com.suke.widget.SwitchButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import mfl.com.R;
import mfl.com.databinding.ActivityEditOfficeWorkingHoursBinding;
import mfl.com.session.GeneralMethods;
import mfl.com.session.sp.StoreLanguageData;
import mfl.com.ui.start.signIn.signInSteps.stepsHome.SignInStepsHome;

public class EditOfficeWorkingHours extends AppCompatActivity implements View.OnClickListener {
    private ActivityEditOfficeWorkingHoursBinding binding;
    private GeneralMethods generalMethods;
    private List<String> durationTimesList = new ArrayList<>();
    private String time = "";
    private StoreLanguageData storeLanguageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_office_working_hours);
        binding.setLifecycleOwner(this);
        generalMethods = new GeneralMethods(this);
        generalMethods.setDirection(binding.mainLin);
        generalMethods.changeLanguage();

        storeLanguageData = new StoreLanguageData(this);

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


        binding.saveBtn.setOnClickListener(this::onClick);

        setSpinner();
        listenOnSwitchers();
        setTextTime();

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

                } else {
                    binding.saturdayTimeLin.setVisibility(View.GONE);

                }
            }
        });


        binding.sundaySwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (isChecked) {
                    binding.sundayTimeLin.setVisibility(View.VISIBLE);

                } else {
                    binding.sundayTimeLin.setVisibility(View.GONE);

                }
            }
        });


        binding.mondaySwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (isChecked) {
                    binding.mondayTimeLin.setVisibility(View.VISIBLE);

                } else {
                    binding.mondayTimeLin.setVisibility(View.GONE);

                }
            }
        });


        binding.tuesdaySwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (isChecked) {
                    binding.tuesdayTimeLin.setVisibility(View.VISIBLE);

                } else {
                    binding.tuesdayTimeLin.setVisibility(View.GONE);

                }
            }
        });


        binding.wednesdaySwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (isChecked) {
                    binding.wednesdayTimeLin.setVisibility(View.VISIBLE);

                } else {
                    binding.wednesdayTimeLin.setVisibility(View.GONE);

                }
            }
        });

        binding.thursdaySwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (isChecked) {
                    binding.thursdayTimeLin.setVisibility(View.VISIBLE);

                } else {
                    binding.thursdayTimeLin.setVisibility(View.GONE);

                }
            }
        });

        binding.fridaySwitch.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (isChecked) {
                    binding.fridayTimeLin.setVisibility(View.VISIBLE);

                } else {
                    binding.fridayTimeLin.setVisibility(View.GONE);

                }
            }
        });


    }

    private void setSpinner() {

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
        if (binding.saveBtn.equals(v)) {
          onBackPressed();

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

    private void setTimePicker(String dayNum) {
        time = "";
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_DeviceDefault_Dialog, new TimePickerDialog.OnTimeSetListener() {
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

}
