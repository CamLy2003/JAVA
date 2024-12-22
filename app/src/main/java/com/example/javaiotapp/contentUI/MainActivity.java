package com.example.javaiotapp.contentUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.javaiotapp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        fragmentAdapter = new FragmentAdapter(this);
        viewPager2.setAdapter(fragmentAdapter);
        viewPager2.setOffscreenPageLimit(3);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Home");
                    tab.setIcon(R.drawable.baseline_home_24);
                    break;
                case 1:
                    tab.setText("Status");
                    tab.setIcon(R.drawable.baseline_local_parking_24);
                    break;
                case 2:
                    tab.setText("Account");
                    tab.setIcon(R.drawable.baseline_account_circle_24);
                    break;
            }
        }).attach();
    }

    /**
     * Get the AccountFragment instance.
     */
    public AccountFragment getAccountFragment() {
        return fragmentAdapter.getAccountFragment();
    }
}
