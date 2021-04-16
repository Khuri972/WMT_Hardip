// Generated by Dagger (https://google.github.io/dagger).
package com.wmt.hardip.base;

import android.app.Activity;
import android.app.Service;
import com.wmt.hardip.data.local.PreferencesHelper;
import dagger.MembersInjector;
import dagger.android.DispatchingAndroidInjector;
import javax.inject.Provider;

public final class BaseApplication_MembersInjector implements MembersInjector<BaseApplication> {
  private final Provider<DispatchingAndroidInjector<Activity>>
      activityDispatchingAndroidInjectorProvider;

  private final Provider<DispatchingAndroidInjector<Service>>
      serviceDispatchingAndroidInjectorProvider;

  private final Provider<PreferencesHelper> helperProvider;

  public BaseApplication_MembersInjector(
      Provider<DispatchingAndroidInjector<Activity>> activityDispatchingAndroidInjectorProvider,
      Provider<DispatchingAndroidInjector<Service>> serviceDispatchingAndroidInjectorProvider,
      Provider<PreferencesHelper> helperProvider) {
    this.activityDispatchingAndroidInjectorProvider = activityDispatchingAndroidInjectorProvider;
    this.serviceDispatchingAndroidInjectorProvider = serviceDispatchingAndroidInjectorProvider;
    this.helperProvider = helperProvider;
  }

  public static MembersInjector<BaseApplication> create(
      Provider<DispatchingAndroidInjector<Activity>> activityDispatchingAndroidInjectorProvider,
      Provider<DispatchingAndroidInjector<Service>> serviceDispatchingAndroidInjectorProvider,
      Provider<PreferencesHelper> helperProvider) {
    return new BaseApplication_MembersInjector(
        activityDispatchingAndroidInjectorProvider,
        serviceDispatchingAndroidInjectorProvider,
        helperProvider);
  }

  @Override
  public void injectMembers(BaseApplication instance) {
    injectActivityDispatchingAndroidInjector(
        instance, activityDispatchingAndroidInjectorProvider.get());
    injectServiceDispatchingAndroidInjector(
        instance, serviceDispatchingAndroidInjectorProvider.get());
    injectHelper(instance, helperProvider.get());
  }

  public static void injectActivityDispatchingAndroidInjector(
      BaseApplication instance,
      DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector) {
    instance.activityDispatchingAndroidInjector = activityDispatchingAndroidInjector;
  }

  public static void injectServiceDispatchingAndroidInjector(
      BaseApplication instance,
      DispatchingAndroidInjector<Service> serviceDispatchingAndroidInjector) {
    instance.serviceDispatchingAndroidInjector = serviceDispatchingAndroidInjector;
  }

  public static void injectHelper(BaseApplication instance, PreferencesHelper helper) {
    instance.helper = helper;
  }
}
