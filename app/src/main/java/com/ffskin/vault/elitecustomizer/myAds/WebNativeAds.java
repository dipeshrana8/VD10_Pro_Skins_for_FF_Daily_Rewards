package com.ffskin.vault.elitecustomizer.myAds;


import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.ffskin.vault.elitecustomizer.R;

import java.util.ArrayList;
import java.util.Random;


public class WebNativeAds {

    @SuppressLint("WrongConstant")
    public static void NativeClass(FrameLayout frameLayout, final Context context) {
        if (AdsPreference.getNative_show()) {

            View adView = LayoutInflater.from(context).inflate(R.layout.native_ad, null);
            TextView titleView = adView.findViewById(R.id.tv_ad_title);
            TextView descriptionView = adView.findViewById(R.id.tv_ad_text);
            Button actionButton = adView.findViewById(R.id.btn_ad_action);
            ImageView adImageView = adView.findViewById(R.id.iv_ad_image);
            LinearLayout adContainer = adView.findViewById(R.id.ll_ad_container);

            loadRandomImage(context, adImageView);
            setAdText(titleView, descriptionView);

            View.OnClickListener redirectListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String redirectUrl = AdsPreference.getnative_redirect_link();
                    launchCustomTab(context, redirectUrl);
                }
            };
            actionButton.setOnClickListener(redirectListener);
            adContainer.setOnClickListener(redirectListener);

            frameLayout.addView(adView);
        } else {
            frameLayout.setVisibility(View.GONE);
        }
    }

    private static void loadRandomImage(Context context, ImageView imageView) {
        ArrayList<String> imageUrls = AdsPreference.getStringListPref();
        if (imageUrls != null && !imageUrls.isEmpty()) {
            String randomImageUrl = imageUrls.get(new Random().nextInt(imageUrls.size()));
            Glide.with(context).load(randomImageUrl).into(imageView);
        } else {
        }
    }

    private static void setAdText(TextView titleView, TextView descriptionView) {
        String[][] adTexts = {
                {"Play and Win Big Rewards", "Compete and Earn Amazing Prizes"},
                {"Earn Coins While Having Fun", "Enjoy Games Packed with Coin Rewards"},
                {"Collect Coins, Unlock Rewards", "Play Games and Maximize Your Fun"},
                {"Win Big with Every Game", "Exciting Games with Unmatched Coin Prizes"},
                {"Level Up and Earn Coins", "Start Playing and Watch Your Coin Balance Grow"}
        };

        int randomIndex = new Random().nextInt(adTexts.length);
        titleView.setText(adTexts[randomIndex][0]);
        descriptionView.setText(adTexts[randomIndex][1]);
    }

    @SuppressLint("WrongConstant")
    private static void launchCustomTab(Context context, String url) {
        if (url == null || url.isEmpty()) {
            return;
        }
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabColorSchemeParams.Builder colorBuilder = new CustomTabColorSchemeParams.Builder();
        colorBuilder.setToolbarColor(ContextCompat.getColor(context, R.color.colorprimary));

        builder.setDefaultColorSchemeParams(colorBuilder.build());
        builder.setShowTitle(true);
        builder.setShareState(CustomTabsIntent.SHARE_STATE_ON);
        builder.setInstantAppsEnabled(true);

        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.intent.setPackage("com.android.chrome");

        try {
            customTabsIntent.launchUrl(context, Uri.parse(url));
        } catch (Exception e) {
        }
    }
}
