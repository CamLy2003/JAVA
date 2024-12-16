package com.example.javaiotapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;

public class ChangeUserInformationFragment extends Fragment {

    private static final String ARG_NAME = "name";
    private static final String ARG_GENDER = "gender";
    private static final String ARG_DOB = "dob";
    private static final String ARG_ADDRESS = "address";
    private static final String ARG_PHONE = "phone";

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private EditText nameEditText, addressEditText, genderEditText, phoneEditText;
    private ImageButton backButton;

    public ChangeUserInformationFragment() {
        // Required empty public constructor
    }

    public static ChangeUserInformationFragment newInstance(String name, String gender, String dob, String address, String phone) {
        ChangeUserInformationFragment fragment = new ChangeUserInformationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putString(ARG_GENDER, gender);
        args.putString(ARG_DOB, dob);
        args.putString(ARG_ADDRESS, address);
        args.putString(ARG_PHONE, phone);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_user_information, container, false);


        initDatePicker();

        // Initialize views
        nameEditText = view.findViewById(R.id.nameEditText);
        genderEditText = view.findViewById(R.id.genderEditText);
        dateButton = view.findViewById(R.id.datePickerButton);
        addressEditText = view.findViewById(R.id.addressEditText);
        phoneEditText = view.findViewById(R.id.phoneEditText);
        backButton = view.findViewById(R.id.backAccountFragmentButton);

        // Set initial data if arguments are present
        if (getArguments() != null) {
            nameEditText.setText(getArguments().getString(ARG_NAME, ""));
            genderEditText.setText(getArguments().getString(ARG_GENDER, ""));
            String dob = getArguments().getString(ARG_DOB, null);
            dateButton.setText(dob != null ? dob : getTodaysDate());
            addressEditText.setText(getArguments().getString(ARG_ADDRESS, ""));
            phoneEditText.setText(getArguments().getString(ARG_PHONE, ""));
        }

        dateButton.setOnClickListener(v -> openDatePicker(v));

        backButton.setOnClickListener(v -> {
            MainActivity mainActivity = (MainActivity) requireActivity();
            AccountFragment accountFragment = mainActivity.getAccountFragment();

            if (accountFragment != null) {
                accountFragment.updateUserInfo(
                        nameEditText.getText().toString(),
                        genderEditText.getText().toString(),
                        dateButton.getText().toString(),
                        addressEditText.getText().toString(),
                        phoneEditText.getText().toString()
                );
            } else {
                Log.e("ChangeUserInfoFragment", "AccountFragment reference is null!");
            }

            // Pop back stack only
            requireActivity().getSupportFragmentManager().popBackStack();
            Log.d("ChangeUserInfoFragment", "Back stack popped. Current back stack count: " +
                    requireActivity().getSupportFragmentManager().getBackStackEntryCount());
        });



        return view;
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

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(requireContext(), style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
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

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }



//    private boolean isInputValid() {
//        // Example validation logic
//        return !nameEditText.getText().toString().isEmpty()
//                && !genderEditText.getText().toString().isEmpty()
//                && !dobEditText.getText().toString().isEmpty()
//                && !addressEditText.getText().toString().isEmpty()
//                && !phoneEditText.getText().toString().isEmpty();
//    }
}
