package com.caraquri.firebasechat;

import android.util.Log;
import rx.Subscriber;

public abstract class ActionSubscriber<T> extends Subscriber<T> {

  private static final String TAG = ActionSubscriber.class.getSimpleName();

  @Override
  public void onCompleted() {
    Log.v(TAG, this + ": onCompleted");
  }

  @Override
  public void onError(Throwable e) {
    Log.e(TAG, this + ": " + e.getMessage(), e);
  }
}
