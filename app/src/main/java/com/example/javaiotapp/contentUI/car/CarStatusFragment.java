package com.example.javaiotapp.contentUI.car;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.javaiotapp.R;
import com.example.javaiotapp.contentUI.AccountFragment;
import com.example.javaiotapp.contentUI.MainActivity;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarStatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarStatusFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView carBeginDateText, carEndDateText, carBranchText, carDayText;

    private ImageButton backButton;
    private static CarInformation carInfor;

    public CarStatusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CarStatusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarStatusFragment newInstance(CarInformation carInformation) {
        CarStatusFragment fragment = new CarStatusFragment();
        Bundle args = new Bundle();

        carInfor = carInformation;

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_car_status, container, false);
        backButton = view.findViewById(R.id.backAccountFragmentButton1);

        carDayText = view.findViewById(R.id.carNumberOfDayDue);
        carBeginDateText = view.findViewById(R.id.carBeginDate);
        carEndDateText = view.findViewById(R.id.carExpirationDate);
        carBranchText = view.findViewById(R.id.carBanchName);

        carBeginDateText.setText(carInfor.getBeginDate());
        carEndDateText.setText(carInfor.getEndDate());
        carBranchText.setText(carInfor.getBrand());

        //Set day to due
        String today = getTodaysDate();
        String endDate = carInfor.getEndDate();

        int numDayToEnd = minusTwoDate(today, endDate);
        carDayText.setText(String.valueOf(numDayToEnd));

        backButton.setOnClickListener(v -> {
            hideKeyboard(v); // Hide the keyboard
                MainActivity mainActivity = (MainActivity) requireActivity();
                AccountFragment accountFragment = mainActivity.getAccountFragment();

                if (accountFragment != null) {
                    accountFragment.updateCarInfo(
                            carInfor
                    );
                } else {
                    Log.e("ChangeUserInfoFragment", "AccountFragment reference is null!");
                }

                requireActivity().getSupportFragmentManager().popBackStack();
        });

        return view;
    }

    private int minusTwoDate(String today, String endDate) {


        int[] date1 = convertDateToIntArray(today);
        int[] date2 = convertDateToIntArray(endDate);


        return calculateDateDifference(date1[2], date1[1], date1[0], date2[2], date2[1], date2[0]);
    }


    public static int calculateDateDifference(int year1, int month1, int day1, int year2, int month2, int day2) {
        int days1 = getTotalDays(year1, month1, day1);
        int days2 = getTotalDays(year2, month2, day2);

        return Math.abs(days1 - days2);
    }

    public static int getTotalDays(int year, int month, int day) {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        //Leap year
        if (isLeapYear(year)) {
            daysInMonth[1] = 29;
        }

        int totalDays = (year - 1) * 365 + countLeapYears(year - 1);

        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonth[i];
        }

        totalDays += day;

        return totalDays;
    }

    public static int countLeapYears(int year) {
        return (year / 4) - (year / 100) + (year / 400);
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }


    private int[] convertDateToIntArray(String dateString) {
        String[] parts = dateString.split(" ");
        int year = Integer.parseInt(parts[2]);
        int month = getMonthNumber(parts[0]);
        int day = Integer.parseInt(parts[1]);

        return new int[]{day, month, year};
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
    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
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

        return "JAN";
    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}