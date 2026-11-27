/*
 * Copyright 2020 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.rafeeq.twa;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.window.SplashScreenView;
import android.widget.ImageView;



public class LauncherActivity
        extends com.google.androidbrowserhelper.trusted.LauncherActivity {
    

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setting an orientation crashes the app due to the transparent background on Android 8.0
        // Oreo and below. We only set the orientation on Oreo and above. This only affects the
        // splash screen and Chrome will still respect the orientation.
        // See https://github.com/GoogleChromeLabs/bubblewrap/issues/496 for details.
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        }

        // The library shows its splash only once the browser has answered. Until then this translucent activity
        // has nothing to draw, so a phone that shows a splash of its own fades it out to an empty screen first.
        // The same view is put up straight away instead; the library swaps in its identical one later. Like the
        // library, it isn't shown when the app is already running in this task.
        if (!isFinishing() && isTaskRoot()) {
            showSplashScreen();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                // The system splash centres its icon on the whole screen, so this one is laid out behind the
                // system bars too. Android 15 and up do that anyway.
                getWindow().setDecorFitsSystemWindows(false);
                // Left alone, the system fades its icon out and then reveals this window, so the icon blinks.
                // Taking the splash over and dropping it at once swaps one for the other in a single frame.
                getSplashScreen().setOnExitAnimationListener(SplashScreenView::remove);
            }
        }
    }

    // Mirrors PwaWrapperSplashScreenStrategy.showSplashScreen(), so nothing moves when the library takes over.
    private void showSplashScreen() {
        ImageView view = new ImageView(this);
        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view.setImageResource(R.drawable.splash);
        view.setBackgroundColor(getColor(R.color.backgroundColor));

        ImageView.ScaleType scaleType = getSplashImageScaleType();
        view.setScaleType(scaleType);
        if (scaleType == ImageView.ScaleType.MATRIX) {
            view.setImageMatrix(getSplashImageTransformationMatrix());
        }

        setContentView(view);
    }

    @Override
    protected Uri getLaunchingUrl() {
        // Get the original launch Url.
        Uri uri = super.getLaunchingUrl();

        

        return uri;
    }
}
