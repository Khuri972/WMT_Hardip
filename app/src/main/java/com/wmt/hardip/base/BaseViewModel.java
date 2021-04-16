/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.wmt.hardip.base;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModel;


import com.wmt.hardip.data.local.PreferencesHelper;
import com.wmt.hardip.data.rest.RepoService;
import com.wmt.hardip.utils.rx.SchedulerProvider;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;


public abstract class BaseViewModel<N> extends ViewModel {

    private final ObservableBoolean mIsLoading = new ObservableBoolean();
    private final PreferencesHelper mPreferencesHelper;
    private final SchedulerProvider schedulerProvider;
    private final RepoService mRepoService;
    private CompositeDisposable mCompositeDisposable;
    private WeakReference<N> mNavigator;


    public BaseViewModel(PreferencesHelper mPreferencesHelper,
                         RepoService mRepoService, SchedulerProvider schedulerProvider) {
        this.mPreferencesHelper = mPreferencesHelper;
        this.schedulerProvider = schedulerProvider;
        this.mRepoService = mRepoService;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public PreferencesHelper getmPreferencesHelper() {
        return mPreferencesHelper;
    }

    public SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    public RepoService getmRepoService() {
        return mRepoService;
    }

    public CompositeDisposable getmCompositeDisposable() {
        return mCompositeDisposable;
    }

    public N getNavigator() {
        return mNavigator.get();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

}
