package com.example.javaiotapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.HashMap;
import java.util.Map;

public class FragmentAdapter extends FragmentStateAdapter {
    private final Map<Integer, Fragment> fragmentMap = new HashMap<>();

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new StatusFragment();
                break;
            case 2:
                fragment = new AccountFragment();
                break;
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }

        // Store the created fragment in the map
        fragmentMap.put(position, fragment);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    /**
     * Retrieve the fragment at a specific position.
     *
     * @param position The tab position (0, 1, or 2).
     * @return The fragment instance, or null if it hasn’t been created yet.
     */
    public Fragment getFragment(int position) {
        return fragmentMap.get(position);
    }

    /**
     * Retrieve the specific AccountFragment instance.
     */
    public AccountFragment getAccountFragment() {
        return (AccountFragment) fragmentMap.get(2); // Assuming AccountFragment is at position 2
    }
}
