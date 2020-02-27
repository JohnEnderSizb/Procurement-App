package johnson.siziba.procurement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Login extends AppCompatActivity {

    WebView theWv;

    SharedPreferences.Editor editor;
    SharedPreferences sharedpreferences;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        theWv = (WebView) findViewById(R.id.thewv);
        WebSettings webSetting = theWv.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDisplayZoomControls(false);
        theWv.loadUrl("file:///android_asset/login.html");
        //theWv.loadUrl("http://elrond.herokuapp.com/userssearch");

        //JavaScriptReceiver javaScriptReceiver;
        //javaScriptReceiver = new JavaScriptReceiver(this);
        theWv.addJavascriptInterface(this, "JSReceiver");

        sharedpreferences = getSharedPreferences("deedscan", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }


    @JavascriptInterface
    public void openSignUp(){
        Intent intent = new Intent(this, SignUp.class);
        //intent.putExtra("order",orderid);
        //intent.putExtra("recepient", theEmail);
        this.startActivity(intent);
        finish();

    }

    @JavascriptInterface
    public void openMain(){
        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("order",orderid);
        //intent.putExtra("recepient", theEmail);
        this.startActivity(intent);
        finish();

    }

    @JavascriptInterface
    public void login(String email, String password) {
        Log.d("Ender", "Email: " + email + ", Password: " + password);
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putInt("email_confirmed", 1);
        editor.commit();

    }


    @JavascriptInterface
    public void openProgDialog() {
        progressDialog = ProgressDialog.show(this,"Please Wait", "Processing...",true);
    }


    @JavascriptInterface
    public void closeProgDialog() {
        progressDialog.dismiss();
    }

    @JavascriptInterface
    public void  openErrorDialog() {

        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("An error occured. Please make sure that you have an internet connection and try again.");
        dialog.setTitle("Error");
        dialog.setPositiveButton("Okay",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(),"Yes is clicked",Toast.LENGTH_LONG).show();
                    }
                });;
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#4ABC96"));
    }

    @JavascriptInterface
    public void openWronInfoDialog() {

        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Email or password is incorrect.");
        dialog.setTitle("Login Error");
        /*
        dialog.setPositiveButton("Create Account",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(),"Yes is clicked",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Login.this, SignUp.class));
                        finish();
                    }
                });
         */
        dialog.setNegativeButton("Try Again",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getApplicationContext(),"cancel is clicked",Toast.LENGTH_LONG).show();
                ;
            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#4ABC96"));
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#4ABC96"));

    }
}
