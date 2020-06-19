package com.autoresto.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.autoresto.MainActivity;
import com.autoresto.R;
import com.autoresto.ui.login.LoginActivity;
import com.autoresto.ui.selecttable.SelectTableActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageView imageView = (ImageView) findViewById(R.id.logo);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translation);
        imageView.startAnimation(animation);
        final Intent intent = new Intent(this, SelectTableActivity.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
