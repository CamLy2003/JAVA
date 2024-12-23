package com.example.javaiotapp.contentUI;
import com.example.javaiotapp.R;
import com.example.javaiotapp.db.slotsMap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.HashMap;


public class StatusFragment extends Fragment {
    private boolean isExpandedB1 = false;
    private boolean isExpandedB2 = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        slotsMap tempMap = new slotsMap();
        HashMap<String, String> myMap = tempMap.getMap();

        TextView tv_A1 = view.findViewById(R.id.textBoxA1);
        TextView tv_A2 = view.findViewById(R.id.textBoxA2);
        TextView tv_A3 = view.findViewById(R.id.textBoxA3);
        TextView tv_A4 = view.findViewById(R.id.textBoxA4);
        TextView tv_A5 = view.findViewById(R.id.textBoxA5);
        TextView tv_A6 = view.findViewById(R.id.textBoxA6);
        TextView tv_A7 = view.findViewById(R.id.textBoxA7);
        TextView tv_A8 = view.findViewById(R.id.textBoxA8);

        if(myMap.get("A1").equals("empty")){
            tv_A1.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        } else {
            tv_A1.setBackgroundResource(R.drawable.rounded_rectangle_red);
        }

        if(myMap.get("A2").equals("empty")){
            tv_A2.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        } else {
            tv_A2.setBackgroundResource(R.drawable.rounded_rectangle_red);
        }

        if(myMap.get("A3").equals("empty")){
            tv_A3.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        } else {
            tv_A3.setBackgroundResource(R.drawable.rounded_rectangle_red);
        }

        if(myMap.get("A4").equals("empty")){
            tv_A4.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        } else {
            tv_A4.setBackgroundResource(R.drawable.rounded_rectangle_red);
        }

        if(myMap.get("A5").equals("empty")){
            tv_A5.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        } else {
            tv_A5.setBackgroundResource(R.drawable.rounded_rectangle_red);
        }

        if(myMap.get("A6").equals("empty")){
            tv_A6.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        } else {
            tv_A6.setBackgroundResource(R.drawable.rounded_rectangle_red);
        }

        if(myMap.get("A7").equals("empty")){
            tv_A7.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        } else {
            tv_A7.setBackgroundResource(R.drawable.rounded_rectangle_red);
        }

        if(myMap.get("A8").equals("empty")){
            tv_A8.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        } else {
            tv_A8.setBackgroundResource(R.drawable.rounded_rectangle_red);
        }

        TextView tv_B1 = view.findViewById(R.id.textBoxB1);
        TextView tv_B2 = view.findViewById(R.id.textBoxB2);
        TextView tv_B3 = view.findViewById(R.id.textBoxB3);
        TextView tv_B4 = view.findViewById(R.id.textBoxB4);
        TextView tv_B5 = view.findViewById(R.id.textBoxB5);
        TextView tv_B6 = view.findViewById(R.id.textBoxB6);
        TextView tv_B7 = view.findViewById(R.id.textBoxB7);
        TextView tv_B8 = view.findViewById(R.id.textBoxB8);

        if(myMap.get("B1").equals("empty")){
            tv_B1.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        } else {
            tv_B1.setBackgroundResource(R.drawable.rounded_rectangle_red);
        }

        if(myMap.get("B2").equals("empty")){
            tv_B2.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        } else {
            tv_B2.setBackgroundResource(R.drawable.rounded_rectangle_red);
        }

        if(myMap.get("B3").equals("empty")){
            tv_B3.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        } else {
            tv_B3.setBackgroundResource(R.drawable.rounded_rectangle_red);
        }

        if(myMap.get("B4").equals("empty")){
            tv_B4.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        } else {
            tv_B4.setBackgroundResource(R.drawable.rounded_rectangle_red);
        }

        if(myMap.get("B5").equals("empty")){
            tv_B5.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        } else {
            tv_B5.setBackgroundResource(R.drawable.rounded_rectangle_red);
        }

        if(myMap.get("B6").equals("empty")){
            tv_B6.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        } else {
            tv_B6.setBackgroundResource(R.drawable.rounded_rectangle_red);
        }

        if(myMap.get("B7").equals("empty")){
            tv_B7.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        } else {
            tv_B7.setBackgroundResource(R.drawable.rounded_rectangle_red);
        }

        if(myMap.get("B8").equals("empty")){
            tv_B8.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        } else {
            tv_B8.setBackgroundResource(R.drawable.rounded_rectangle_red);
        }

        Button floorB1ToggleButton = view.findViewById(R.id.floorB1ToggleButton);
        LinearLayout floorB1 = view.findViewById(R.id.floorB1);

        floorB1ToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpandedB1) {
                    floorB1.setVisibility(View.GONE);
                    floorB1ToggleButton.setText("▼ Floor B1 (-1F)");
                } else {
                    floorB1.setVisibility(View.VISIBLE);
                    floorB1ToggleButton.setText("▲ Floor B1 (-1F)");
                }
                isExpandedB1 = !isExpandedB1;
            }
        });

        Button floorB2ToggleButton = view.findViewById(R.id.floorB2ToggleButton);
        LinearLayout floorB2 = view.findViewById(R.id.floorB2);

        floorB2ToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isExpandedB2) {
                    floorB2.setVisibility(View.GONE);
                    floorB2ToggleButton.setText("▼ Floor B2 (-2F)");
                } else {
                    floorB2.setVisibility(View.VISIBLE);
                    floorB2ToggleButton.setText("▲ Floor B2 (-2F)");
                }
                isExpandedB2 = !isExpandedB2;
            }
        });
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_status, container, false);
        return view;
    }
}