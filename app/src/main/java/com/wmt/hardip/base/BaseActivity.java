package com.wmt.hardip.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.wmt.hardip.R;
import com.wmt.hardip.ui.signup.SignUPActivity;
import com.wmt.hardip.utils.UtilClass;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.DaggerAppCompatActivity;
import dagger.android.support.HasSupportFragmentInjector;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends DaggerAppCompatActivity implements HasSupportFragmentInjector {

    T bainding;
    private V mViewModel;

    @Inject
    public DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    public T getBainding() {
        return bainding;
    }

    public abstract V getViewModel();

    public abstract int getBindingVariable();


    @LayoutRes
    protected abstract int layoutRes();

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        performDataBinding();
    }


    private void performDataBinding() {
        bainding = DataBindingUtil.setContentView(this, layoutRes());
        bainding.setLifecycleOwner(this);
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        bainding.setVariable(getBindingVariable(), mViewModel);
        bainding.executePendingBindings();
    }

    public void showProgress() {
        UtilClass.showProgress(this);
    }

    public void hideProgress() {
        UtilClass.hideProgress();
    }

    public boolean checkInternet() {
        return checkInternet(true);
    }

    public boolean checkInternet(boolean isShowProgress) {
        if (UtilClass.isOnline(this)) {
            if (isShowProgress)
                showProgress();
            return true;
        } else {
            if (isShowProgress)
            Toast.makeText(this, getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

}