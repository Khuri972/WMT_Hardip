package com.wmt.hardip.ui.userList;

import com.wmt.hardip.base.BaseViewModel;
import com.wmt.hardip.data.local.PreferencesHelper;
import com.wmt.hardip.data.rest.RepoService;
import com.wmt.hardip.utils.rx.SchedulerProvider;

import java.util.ArrayList;

public class UserListViewModel extends BaseViewModel<UserListNavigator> {

    public UserListViewModel(PreferencesHelper mPreferencesHelper, RepoService mRepoService, SchedulerProvider schedulerProvider) {
        super(mPreferencesHelper, mRepoService, schedulerProvider);
    }


    void getUserData(int page) {
        getmCompositeDisposable()
                .add(getmRepoService().getData(page)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(response -> {
                            if (response.getMetaObj().isSuccess()) {
                                getNavigator().onSuccess(response.getDataObject().getUsersArrays(), response.getMetaObj().getMessage(),response.getDataObject().getPaginationObj().getTotal());
                            } else {
                                getNavigator().onFailCall(response.getMetaObj().getMessage());
                            }
                        }, throwable -> {
                            getNavigator().onFailCall("Internal error");
                        }));
    }



}
