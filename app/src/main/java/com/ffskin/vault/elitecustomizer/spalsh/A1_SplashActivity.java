package com.ffskin.vault.elitecustomizer.spalsh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.ffskin.vault.elitecustomizer.databinding.ActivitySplashBinding;
import com.ffskin.vault.elitecustomizer.myAds.AdsPreference;
import com.onesignal.Continue;
import com.onesignal.OneSignal;
import com.onesignal.debug.LogLevel;


public class A1_SplashActivity extends BaseActivity {

    ActivitySplashBinding binding;
    private boolean IntroChecked = false;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        showNativeAds = false;
        showInterstitialAds = true;
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        prefs.edit().putBoolean("spin_dialog_shown", false).apply();

        sharedPreferences = getSharedPreferences("FfPref", MODE_PRIVATE);
        IntroChecked = sharedPreferences.getBoolean("IntroChecked", false);

        // Enable verbose logging for debugging (remove in production)
        OneSignal.getDebug().setLogLevel(LogLevel.VERBOSE);

        // ✅ Initialize OneSignal first
        String oneSignalAppId = AdsPreference.getOneSignalAppId();
        if (!oneSignalAppId.isEmpty()) {
            OneSignal.initWithContext(this, oneSignalAppId);

            // ✅ Only call getNotifications AFTER initWithContext
            OneSignal.getNotifications().requestPermission(false, Continue.none());
        }

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (IntroChecked) {
                startActivity(new Intent(A1_SplashActivity.this, HomeActivity.class));
                finish();
            } else {
                startActivity(new Intent(A1_SplashActivity.this, Intro1Activity.class));
                finish();
            }
        }, 3000);
    }
}
