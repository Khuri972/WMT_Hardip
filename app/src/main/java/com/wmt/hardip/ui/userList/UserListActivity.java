package com.wmt.hardip.ui.userList;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wmt.hardip.BR;
import com.wmt.hardip.R;
import com.wmt.hardip.base.BaseActivity;
import com.wmt.hardip.data.local.PreferencesHelper;
import com.wmt.hardip.databinding.ActivityUserListBinding;
import com.wmt.hardip.di.util.ViewModelFactory;

import java.util.ArrayList;

import javax.inject.Inject;

public class UserListActivity extends BaseActivity<ActivityUserListBinding, UserListViewModel> implements UserListNavigator {

    ActivityUserListBinding binding;
    UserListViewModel viewModel;

    private int current_page = 1;
    private int total_pages = 4;
    private boolean loading = true;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    LinearLayoutManager layoutManager;
    private UserListAdapter adapter;


    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    PreferencesHelper mPreferencesHelper;

    @Override
    public UserListViewModel getViewModel() {
        viewModel = new ViewModelProvider(this, viewModelFactory).get(UserListViewModel.class);
        return viewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_user_list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding = getBainding();
        viewModel.setNavigator(this);
        binding.setViewModel(viewModel);
        layoutManager = new LinearLayoutManager(UserListActivity.this, LinearLayoutManager.VERTICAL, false);

        binding.rvRecycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                if (loading) {
                    if ((visibleItemCount + firstVisibleItem) >= totalItemCount) {
                        loading = false;
                        current_page++;
                        if (current_page <= total_pages) {
                            if (!checkInternet())
                                return;
                            viewModel.getUserData(current_page);
                        }
                    }
                }
            }
        });

        loadAdapter(new ArrayList<>());
        if (!checkInternet())
            return;
        viewModel.getUserData(current_page);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadAdapter(ArrayList<UserModel.dataObject.UsersArray> data) {

        if (adapter == null) {
            adapter = new UserListAdapter(UserListActivity.this, data);
            binding.rvRecycleview.setAdapter(adapter);
            binding.rvRecycleview.setLayoutManager(layoutManager);
        } else {
            adapter.data.addAll(data);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailCall(String message) {
        hideProgress();
        Toast.makeText(UserListActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(ArrayList<UserModel.dataObject.UsersArray> arrays, String success, int total) {
        hideProgress();
        loading = true;
        total_pages = total;
        loadAdapter(arrays);
    }

}