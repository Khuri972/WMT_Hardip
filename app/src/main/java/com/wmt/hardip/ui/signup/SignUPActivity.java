package com.wmt.hardip.ui.signup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.wmt.hardip.BR;
import com.wmt.hardip.R;
import com.wmt.hardip.base.BaseActivity;
import com.wmt.hardip.data.local.PreferencesHelper;
import com.wmt.hardip.databinding.ActivitySignUpBinding;
import com.wmt.hardip.di.util.ViewModelFactory;
import com.wmt.hardip.ui.userList.UserListActivity;
import com.wmt.hardip.utils.UtilClass;

import javax.inject.Inject;

public class SignUPActivity extends BaseActivity<ActivitySignUpBinding, SignUpViewModel> implements SignUpNavigator {

    ActivitySignUpBinding binding;

    SignUpViewModel viewModel;

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    PreferencesHelper mPreferencesHelper;

    @Override
    public SignUpViewModel getViewModel() {
        viewModel = new ViewModelProvider(this, viewModelFactory).get(SignUpViewModel.class);
        return viewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    protected int layoutRes() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding = getBainding();
        viewModel.setNavigator(this);
        binding.setViewModel(viewModel);

        if(viewModel.getmPreferencesHelper().getUserId()!=0){
            Intent intent = new Intent(SignUPActivity.this, UserListActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFailCall(String message) {
        hideProgress();
        Toast.makeText(SignUPActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @SuppressLint("ShowToast")
    @Override
    public void onClickButton() {

        if (binding.edtUserName.getText().toString().trim().equals("")) {
            Toast.makeText(SignUPActivity.this, "Please enter user name.", Toast.LENGTH_LONG).show();
        } else if (!UtilClass.isValidEmail(binding.edtEmail.getText().toString().trim())) {
            Toast.makeText(SignUPActivity.this, "Please enter valid email.", Toast.LENGTH_LONG).show();
        } else if (binding.edtPassword.getText().toString().trim().length() < 8) {
            Toast.makeText(SignUPActivity.this, "Please enter password must be 8 character.", Toast.LENGTH_LONG).show();
        } else if (!binding.edtPassword.getText().toString().trim().equals(binding.edtConPassword.getText().toString().trim())) {
            Toast.makeText(SignUPActivity.this, "Password and confirm password must be same.", Toast.LENGTH_LONG).show();
        } else {
            if (!checkInternet())
                return;
            viewModel.register(binding.edtUserName.getText().toString().trim(), binding.edtEmail.getText().toString().trim(), binding.edtPassword.getText().toString().trim());
        }
    }

    @Override
    public void onRegisterSuccess(String text) {
        hideProgress();
        Intent intent = new Intent(SignUPActivity.this, UserListActivity.class);
        startActivity(intent);
        finish();
    }
}