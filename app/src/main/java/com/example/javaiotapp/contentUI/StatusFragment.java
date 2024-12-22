package com.example.javaiotapp.contentUI;
import com.example.javaiotapp.R;
import com.example.javaiotapp.db.slotsMap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.HashMap;


public class StatusFragment extends Fragment {

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
        TextView tv_A9 = view.findViewById(R.id.textBoxA9);
        TextView tv_A10 = view.findViewById(R.id.textBoxA10);

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

        if(myMap.get("A9").equals("empty")){
            tv_A9.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        } else {
            tv_A9.setBackgroundResource(R.drawable.rounded_rectangle_red);
        }

        if(myMap.get("A10").equals("empty")){
            tv_A10.setBackgroundResource(R.drawable.rounded_rectangle_gray);
        } else {
            tv_A10.setBackgroundResource(R.drawable.rounded_rectangle_red);
        }

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_status, container, false);
        return view;
    }
}