package com.quangnguyen.stackoverflowclient;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;

@Module
public class AppModule {
  private Context context;

  public AppModule(Application context) {
    this.context = context;
  }

  @Singleton
  public Context provideContext() {
    return context;
  }
}
