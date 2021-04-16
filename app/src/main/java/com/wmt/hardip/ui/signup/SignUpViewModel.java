package com.wmt.hardip.ui.signup;

import com.wmt.hardip.base.BaseViewModel;
import com.wmt.hardip.data.local.PreferencesHelper;
import com.wmt.hardip.data.rest.RepoService;
import com.wmt.hardip.utils.rx.SchedulerProvider;

public class SignUpViewModel extends BaseViewModel<SignUpNavigator> {

    public SignUpViewModel(PreferencesHelper mPreferencesHelper, RepoService mRepoService, SchedulerProvider schedulerProvider) {
        super(mPreferencesHelper, mRepoService, schedulerProvider);
    }

    void register(String username, String email, String password) {
        getmCompositeDisposable()
                .add(getmRepoService().register(username, email, password)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(response -> {
                            if (response.getMetaObj().isSuccess()) {
                                getmPreferencesHelper().setUserId(response.getDataObj().getUserObj().getId());
                                getmPreferencesHelper().setUserName(response.getDataObj().getUserObj().getUsername());
                                getmPreferencesHelper().setEmail(response.getDataObj().getUserObj().getEmail());
                                getmPreferencesHelper().setAuthToken(response.getDataObj().getTokenObj().getToken());
                                getNavigator().onRegisterSuccess(response.getMetaObj().getMessage());
                            } else {
                                getNavigator().onFailCall(response.getMetaObj().getMessage());
                            }
                        }, throwable -> {
                            getNavigator().onFailCall("unique validation failed on username");
                        }));
    }

}
