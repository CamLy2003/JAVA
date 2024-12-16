package com.example.javaiotapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.ViewHolder> {

    private List<UserInfo> userList;

    // Constructor to initialize the user list
    public UserInfoAdapter(List<UserInfo> userList) {
        this.userList = userList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the custom layout for each item in the list
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_user_information_list, parent, false);
        return new ViewHolder(view);
    }

    // Bind the data to the views (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the current user info
        UserInfo userInfo = userList.get(position);

        // Set the text in each view
        holder.keyTextView.setText(userInfo.getKey());
        holder.valueTextView.setText(userInfo.getValue());
    }

    // Return the size of the data set (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return userList.size();
    }

    // ViewHolder class to hold references to the views for each item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView keyTextView;
        TextView valueTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            keyTextView = itemView.findViewById(R.id.User_infor_list1);
            valueTextView = itemView.findViewById(R.id.User_infor_list2);
        }
    }

    public void updateUserInfo(int position, UserInfo updatedUserInfo) {
        userList.set(position, updatedUserInfo);
        notifyItemChanged(position);
    }
}

