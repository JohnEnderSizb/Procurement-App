package johnson.siziba.procurement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private ImageView logo;
    private static int splashTimeOut=2500;
    private TextView text;

    SharedPreferences sharedpreferences;
    String password;
    String email;
    int email_confirmed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActionBar().hide();
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);

        sharedpreferences = getSharedPreferences("deedscan", Context.MODE_PRIVATE);

        password = sharedpreferences.getString("password", "ender");

        email = sharedpreferences.getString("email", "ender");

        email_confirmed = sharedpreferences.getInt("email_confirmed", 0);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE))
            { WebView.setWebContentsDebuggingEnabled(true); }
        }

        logo=(ImageView)findViewById(R.id.logo);
        text = findViewById(R.id.title);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },splashTimeOut);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mysplashanimation);
        logo.startAnimation(myanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(email.equals("ender") && password.equals("ender")) {
                    startActivity(new Intent(SplashActivity.this, Login.class));
                    finish();
                }

                else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
            }
        },2000);
        Animation myanim2 = AnimationUtils.loadAnimation(this,R.anim.mysplashanimatio2);
        text.startAnimation(myanim2);
    }
}
