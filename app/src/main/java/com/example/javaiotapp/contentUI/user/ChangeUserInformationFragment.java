package com.example.javaiotapp.contentUI.user;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.javaiotapp.R;
import com.example.javaiotapp.contentUI.AccountFragment;
import com.example.javaiotapp.contentUI.MainActivity;

import java.util.Calendar;

public class ChangeUserInformationFragment extends Fragment {

    private static final String ARG_NAME = "name";
    private static final String ARG_GENDER = "gender";
    private static final String ARG_DOB = "dob";
    private static final String ARG_ADDRESS = "address";
    private static final String ARG_PHONE = "phone";

    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    static private UserInformation userInfor;

    private Button genderButton;
    private EditText nameEditText, addressEditText, phoneEditText;
    private ImageButton backButton;

    private boolean isNeedToUpdate = false;

    public ChangeUserInformationFragment() {
        // Required empty public constructor
    }

    public static ChangeUserInformationFragment newInstance(UserInformation userInformation) {
        ChangeUserInformationFragment fragment = new ChangeUserInformationFragment();
        Bundle args = new Bundle();
        userInfor = userInformation;
        args.putString(ARG_NAME, userInfor.getName());
        args.putString(ARG_GENDER, userInfor.getSex().toString());
        args.putString(ARG_DOB, userInfor.getDate_of_birth());
        args.putString(ARG_ADDRESS, userInfor.getAddress());
        args.putString(ARG_PHONE, userInfor.getPhone_number());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_user_information, container, false);

        isNeedToUpdate = false;

        initDatePicker();

        // Initialize views
        nameEditText = view.findViewById(R.id.nameEditText);
        genderButton = view.findViewById(R.id.GenderPickerButton);
        dateButton = view.findViewById(R.id.datePickerButton);
        addressEditText = view.findViewById(R.id.addressEditText);
        phoneEditText = view.findViewById(R.id.phoneEditText);
        backButton = view.findViewById(R.id.backAccountFragmentButton);

        //SetFilter to limit input number in java
        setFilter();

        // Set initial data if arguments are present
        setInitData();

        genderButton.setOnClickListener(v -> chooseGender());

        dateButton.setOnClickListener(v -> openDatePicker(v));

        backButton.setOnClickListener(v -> backToAccountFragment(v));

        return view;
    }

    private void setInitData() {
        if (getArguments() != null) {
            nameEditText.setText(getArguments().getString(ARG_NAME, ""));
            genderButton.setText(getArguments().getString(ARG_GENDER, ""));
            String dob = getArguments().getString(ARG_DOB, null);
            dateButton.setText(dob != null ? dob : getTodaysDate());
            addressEditText.setText(getArguments().getString(ARG_ADDRESS, ""));
            phoneEditText.setText(getArguments().getString(ARG_PHONE, ""));
        }
    }

    private void setFilter() {
        nameEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(30)});
        phoneEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});
        addressEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(30)});
    }

    private void backToAccountFragment(View v) {
        {
            hideKeyboard(v); // Hide the keyboard
            if (isInputValid()) {
                isNeedToUpdate = true;
                MainActivity mainActivity = (MainActivity) requireActivity();
                AccountFragment accountFragment = mainActivity.getAccountFragment();

                userInfor.setName(nameEditText.getText().toString());
                userInfor.setSex(getGender(genderButton.getText().toString()));
                userInfor.setDate_of_birth(dateButton.getText().toString());
                userInfor.setAddress(addressEditText.getText().toString());
                userInfor.setPhone_number(phoneEditText.getText().toString());


                if (accountFragment != null) {
                    accountFragment.updateUserInfo(
                            userInfor
                    );
                } else {
                    Log.e("ChangeUserInfoFragment", "AccountFragment reference is null!");
                }

                requireActivity().getSupportFragmentManager().popBackStack();
            }
        }
    }
    private void chooseGender() {

        Gender[]genderOption = {Gender.Male, Gender.Female, Gender.Other};

        String[] genderNames = new String[genderOption.length];
        for (int i = 0; i < genderOption.length; i++) {
            genderNames[i] = genderOption[i].toString();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Choose Gender")
                .setItems(genderNames, (dialog, which) -> {
                    // Set selected gender
                    Gender option = genderOption[which];
                    genderButton.setText(option.toString());
                })
                .show();
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            dateButton.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        String dob = getArguments() != null ? getArguments().getString(ARG_DOB, null) : null;
        if (dob != null) {
            // Parse the DOB to set DatePicker default values
            String[] parts = dob.split(" ");
            int year = Integer.parseInt(parts[2]);
            int month = getMonthNumber(parts[0]) - 1; // Convert to zero-based
            int day = Integer.parseInt(parts[1]);
            cal.set(year, month, day);
        }

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(requireContext(), style, dateSetListener, year, month, day);
    }


    private Gender getGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            return Gender.Male;
        } else if (gender.equalsIgnoreCase("Female")) {
            return Gender.Female;
        } else {
            return Gender.Other;
        }
    }

    private int getMonthNumber(String month) {
        switch (month) {
            case "JAN": return 1;
            case "FEB": return 2;
            case "MAR": return 3;
            case "APR": return 4;
            case "MAY": return 5;
            case "JUN": return 6;
            case "JUL": return 7;
            case "AUG": return 8;
            case "SEP": return 9;
            case "OCT": return 10;
            case "NOV": return 11;
            case "DEC": return 12;
            default: return 1; // Default to JAN
        }
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }


    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private boolean isInputValid() {
        // Example validation logic
        return !nameEditText.getText().toString().isEmpty()
                && !genderButton.getText().toString().isEmpty()
                && !addressEditText.getText().toString().isEmpty()
                && !phoneEditText.getText().toString().isEmpty()
                && !dateButton.getText().toString().isEmpty();
    }

    public boolean getNeedToUpdate() {
        return isNeedToUpdate;
    }
}
