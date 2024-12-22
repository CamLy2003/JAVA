package com.example.javaiotapp;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class AccountFragment extends Fragment {
    List<UserInfo> userList;

    CarInformation carInfor;
    UserInformation userInfor;
    UserInfoAdapter adapter;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        createNotificationChannel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        Button logoutButton = view.findViewById(R.id.ExitButton);
        Button changeUserInfoButton = view.findViewById(R.id.ChangeUserInfo);
        Button carStatusButton  = view.findViewById(R.id.CarStatus);


        changeUserInfoButton.setOnClickListener(v -> {
            changeUserInformation();
        });

        carStatusButton.setOnClickListener(v -> {
            displayCarStatus();
        });




        logoutButton.setOnClickListener(v -> Logout());

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create a list of user information
        userInfor = new UserInformation();
        userInfor.setName("Nguyen Dang Duy Manh");
        userInfor.setSex(Gender.Male);
        userInfor.setDate_of_birth("DEC 11 2003");
        userInfor.setAddress("407 - B5");
        userInfor.setPhone_number("0832073486");

        //Create a list of car information
        carInfor = new CarInformation();
        carInfor.setBeginDate("JAN 1 2023");
        carInfor.setEndDate("DEC 12 2025");
        carInfor.setBrand("Toyota");
        carInfor.setDescription("Car00001");

        userList = new ArrayList<>();
        userList.add(new UserInfo("Name", userInfor.getName()));
        userList.add(new UserInfo("Gender", userInfor.getSex().toString()));
        userList.add(new UserInfo("Date of birth", userInfor.getDate_of_birth()));
        userList.add(new UserInfo("Address", userInfor.getAddress()));
        userList.add(new UserInfo("Phone Number", userInfor.getPhone_number()));

        String gender = userList.get(1).getValue();
        ImageView boy_image = view.findViewById(R.id.account_avatar_boy);
        ImageView girl_image = view.findViewById(R.id.account_avatar_girl);
        if (gender.equalsIgnoreCase("male")) {
            boy_image.setVisibility(View.VISIBLE);
            girl_image.setVisibility(View.GONE);
        } else if (gender.equalsIgnoreCase("female")) {
            boy_image.setVisibility(View.GONE);
            girl_image.setVisibility(View.VISIBLE);
        }

        // Set the adapter
        adapter = new UserInfoAdapter(userList);
        recyclerView.setAdapter(adapter);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.post(() -> {
            adapter.notifyDataSetChanged();
        });

        requireActivity().getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            int backStackCount = requireActivity().getSupportFragmentManager().getBackStackEntryCount();
            Log.d("AccountFragment", "Back stack changed. Count: " + backStackCount);
            if (backStackCount == 0) {
                toggleContainerVisibility(
                        requireView().findViewById(R.id.fragment_account),
                        requireView().findViewById(R.id.fragment_account_container),
                        false
                );
            }
        });

        return view;
    }

    public void updateUserInfo(UserInformation userInfor) {
        // Update user list
        TextView userName = requireView().findViewById(R.id.HelloUser);
        ImageView boy_image = requireView().findViewById(R.id.account_avatar_boy);
        ImageView girl_image = requireView().findViewById(R.id.account_avatar_girl);

        // Update user infor
        this.userInfor = userInfor;

        userName.setText(userInfor.getName());
        userName.setAllCaps(true);
        userList.get(0).setValue(userInfor.getName());
        userList.get(1).setValue(userInfor.getSex().toString());
        userList.get(2).setValue(userInfor.getDate_of_birth());
        userList.get(3).setValue(userInfor.getAddress());
        userList.get(4).setValue(userInfor.getPhone_number());

        if (userInfor.getSex().toString().equalsIgnoreCase("male")) {
            boy_image.setVisibility(View.VISIBLE);
            girl_image.setVisibility(View.GONE);
        } else if (userInfor.getSex().toString().equalsIgnoreCase("female")) {
            boy_image.setVisibility(View.GONE);
            girl_image.setVisibility(View.VISIBLE);
        }


        adapter.notifyDataSetChanged(); // Notify adapter of changes
    }

    private void Logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(AccountFragment.this.getContext(), Login.class);
        startActivity(intent);
    }

    private void createNotificationChannel() {
        String name = "My Notification Channel";
        String description = "Channel for app notifications";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("my_channel_id", name, importance);
        channel.setDescription(description);

        NotificationManager notificationManager = requireContext().getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void changeUserInformation() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        View staticContent = requireView().findViewById(R.id.fragment_account);
        View fragmentContainer = requireView().findViewById(R.id.fragment_account_container);

        toggleContainerVisibility(staticContent, fragmentContainer, true);

        // Use newInstance to pass user information
        ChangeUserInformationFragment changeFragment = ChangeUserInformationFragment.newInstance(
                userInfor
        );

        transaction.replace(R.id.fragment_account_container, changeFragment, "ACCOUNT_FRAGMENT") // Use tag for ChangeUserInformationFragment
                .addToBackStack(null) // Add to back stack
                .commit();

        Log.d("AccountFragment", "Navigated to ChangeUserInformationFragment.");
    }

    private void displayCarStatus() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        View staticContent = requireView().findViewById(R.id.fragment_account);
        View fragmentContainer = requireView().findViewById(R.id.fragment_account_container);

        toggleContainerVisibility(staticContent, fragmentContainer, true);

        CarStatusFragment changeFragment = CarStatusFragment.newInstance(
                carInfor
        );

        transaction.replace(R.id.fragment_account_container, changeFragment, "ACCOUNT_FRAGMENT") // Use tag for ChangeUserInformationFragment
                .addToBackStack(null) // Add to back stack
                .commit();

        Log.d("AccountFragment", "Navigated to CarStatusFragment.");

    }

    public void toggleContainerVisibility(View staticContent, View fragmentContainer, boolean showFragment) {
        if (staticContent == null || fragmentContainer == null) {
            Log.e("AccountFragment", "toggleContainerVisibility failed: Views are null!");
            return;
        }
        staticContent.setVisibility(showFragment ? View.GONE : View.VISIBLE);
        fragmentContainer.setVisibility(showFragment ? View.VISIBLE : View.GONE);
        Log.d("AccountFragment", "Static content visibility: " + staticContent.getVisibility() +
                ", Fragment container visibility: " + fragmentContainer.getVisibility());
    }

    public void updateCarInfo(CarInformation carInfor) {
        this.carInfor = carInfor;
    }
}
