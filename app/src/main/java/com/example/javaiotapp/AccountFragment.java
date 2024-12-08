package com.example.javaiotapp;

import android.icu.text.Edits;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        ListView list_view = (ListView) view.findViewById(R.id.listview);

        String[] items_user_infor_list = {"Name", "Date of Birth","Address","Phone Number"};

        UserInformation User = new UserInformation();
        User.setName("Nguyen Dang Duy Manh");
        User.setAddress("407 - B5");
        User.setDate_of_birth("11 / 12 / 2003");
        User.setPhone_number("0832073486");

        User.setName("Manh Nguyen");
        LinkedHashMap<String, String> User_map = new LinkedHashMap<>();

        User_map.put(items_user_infor_list[0], User.getName());
        User_map.put(items_user_infor_list[1], User.getDate_of_birth());
        User_map.put(items_user_infor_list[2], User.getAddress());
        User_map.put(items_user_infor_list[3], User.getPhone_number());

        List<HashMap<String, String>> User_List = new ArrayList<>();

        SimpleAdapter user_adapter = new SimpleAdapter(this.getContext(), User_List, R.layout.display_user_information_list,
                new String[]{"First line", "Second line"},
                new int[]{R.id.User_infor_list1, R.id.User_infor_list2});

        Iterator it = User_map.entrySet().iterator();

        while (it.hasNext()) {
            HashMap<String, String> User_map_result = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            User_map_result.put("First line", pair.getKey().toString());
            User_map_result.put("Second line", pair.getValue().toString());
            User_List.add(User_map_result);
        }
        list_view.setAdapter(user_adapter);
        return view;
    }
}