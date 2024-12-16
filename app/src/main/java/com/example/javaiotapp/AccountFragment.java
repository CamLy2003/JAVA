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
import android.widget.ListView;
import android.widget.SimpleAdapter;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class AccountFragment extends Fragment {
    List<UserInfo> userList;
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



        changeUserInfoButton.setOnClickListener(v -> {
            changeUserInformation();
        });
        logoutButton.setOnClickListener(v -> showNotification());

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create a list of user information
        userList = new ArrayList<>();
        userList.add(new UserInfo("Name", "Nguyen Dang Duy Manh"));
        userList.add(new UserInfo("Gender", "Male"));
        userList.add(new UserInfo("Date of birth", "DEC 11 2003"));
        userList.add(new UserInfo("Address", "407 - B5"));
        userList.add(new UserInfo("Phone Number", "0832073486"));

        // Set the adapter
        adapter = new UserInfoAdapter(userList);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


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

    public void updateUserInfo(String name, String gender, String dob, String address, String phone) {
        // Update user list
        TextView userName = requireView().findViewById(R.id.HelloUser);
        userName.setText(name);
        userName.setAllCaps(true);
        userList.get(0).setValue(name); // Assuming Name is the first item
        userList.get(1).setValue(gender);
        userList.get(2).setValue(dob);
        userList.get(3).setValue(address);
        userList.get(4).setValue(phone);
        adapter.notifyDataSetChanged(); // Notify adapter of changes
    }

    private void showNotification() {
        Context context = requireContext();
        Intent intent = new Intent(context, MainActivity.class); // Target activity hosting the fragment
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "my_channel_id")
                .setSmallIcon(R.drawable.account_logout_icon)
                .setContentTitle("Logout Notification")
                .setContentText("You have clicked the logout button.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
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
                userList.get(0).getValue(), // Name
                userList.get(1).getValue(), // Gender
                userList.get(2).getValue(), // DOB
                userList.get(3).getValue(), // Address
                userList.get(4).getValue()  // Phone
        );

        transaction.replace(R.id.fragment_account_container, changeFragment, "ACCOUNT_FRAGMENT") // Use tag for ChangeUserInformationFragment
                .addToBackStack(null) // Add to back stack
                .commit();

        Log.d("AccountFragment", "Navigated to ChangeUserInformationFragment.");
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

}
