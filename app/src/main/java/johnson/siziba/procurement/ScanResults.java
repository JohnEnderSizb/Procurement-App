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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.JavascriptInterface;


public class ScanResults extends AppCompatActivity {

    WebView theWv;
    String qrValue;
    String email;
    SharedPreferences sharedpreferences;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_scan_results);

        qrValue = getIntent().getExtras().getString("qrValue");

        theWv = (WebView) findViewById(R.id.thewv);
        WebSettings webSetting = theWv.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDisplayZoomControls(false);
        theWv.loadUrl("file:///android_asset/scan_results.html");

        theWv.addJavascriptInterface(this, "JSReceiver");

        sharedpreferences = getSharedPreferences("deedscan", Context.MODE_PRIVATE);
        email = sharedpreferences.getString("email", "ender");
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        finish();
    }

    @JavascriptInterface
    public void scanNext() {
        Intent intent = new Intent(this, Scan.class);
        this.startActivity(intent);
        finish();
    }

    @JavascriptInterface
    public void home() {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        finish();
    }

    @JavascriptInterface
    public String getQrCode(){
        return qrValue;
    }

    @JavascriptInterface
    public String getUserEmail(){
        return email;
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
    public void openProgDialog() {
        progressDialog = ProgressDialog.show(this,"Please Wait", "Processing...",true);
    }


    @JavascriptInterface
    public void closeProgDialog() {
        progressDialog.dismiss();
    }

    @JavascriptInterface
    public void openNotFoundDialog() {

        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("The deed you scanned is not registered on our system.");
        dialog.setTitle("Not Found");
        dialog.setPositiveButton("Create Account",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(),"Yes is clicked",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ScanResults.this, MainActivity.class));
                        finish();
                    }
                });
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
