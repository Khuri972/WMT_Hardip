package com.wmt.hardip.ui.userList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.wmt.hardip.R;
import com.wmt.hardip.databinding.ItemUsersBinding;
import com.wmt.hardip.utils.UtilClass;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    public ArrayList<UserModel.dataObject.UsersArray> data;
    private Context context;

    public UserListAdapter(Context context, ArrayList<UserModel.dataObject.UsersArray> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        ItemUsersBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_users, parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.binding.txtName.setText("User Name : " + data.get(position).getUsername());
        viewHolder.binding.txtEmail.setText("Email : " + data.get(position).getEmail());
        UtilClass.loadImage(context, data.get(position).getProfile_pic(), R.mipmap.ic_launcher, viewHolder.binding.imgImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemUsersBinding binding;

        public ViewHolder(ItemUsersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
