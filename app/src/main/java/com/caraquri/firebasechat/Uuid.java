package com.caraquri.firebasechat;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.util.UUID;

public class Uuid {

  private static final String PREF = "uuid";
  private static final String KEY = "uuid";

  public static void init(@NonNull Context context) {
    final SharedPreferences prefs = sharedPreferences(context);
    if (!exists(prefs)) {
      final String uuid = UUID.randomUUID().toString();
      prefs.edit().putString(KEY, uuid).apply();
    }
  }

  @NonNull
  public static String get(@NonNull Context context) {
    final SharedPreferences prefs = sharedPreferences(context);
    checkExists(prefs);
    // null is already checked. ignore.
    // noinspection ConstantConditions
    return prefs.getString(KEY, null);
  }

  private static SharedPreferences sharedPreferences(@NonNull Context context) {
    return context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
  }

  private static boolean exists(SharedPreferences prefs) {
    // Does #contains() treat empty as contains?
    return !TextUtils.isEmpty(prefs.getString(KEY, null));
  }

  private static void checkExists(SharedPreferences prefs) {
    if (!exists(prefs)) {
      throw new IllegalStateException("UUID is not yet generated. Have you called Uuid.init() ?");
    }
  }
}
