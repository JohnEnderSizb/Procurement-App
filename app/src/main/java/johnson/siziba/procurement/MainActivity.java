package johnson.siziba.procurement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
    WebView theWv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theWv = (WebView) findViewById(R.id.thewv);
        WebSettings webSetting = theWv.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDisplayZoomControls(false);
        theWv.loadUrl("file:///android_asset/main.html");
        theWv.addJavascriptInterface(this, "JSReceiver");
    }

    @JavascriptInterface
    public void openScan(){
        startActivity(new Intent(this, Scan.class));
        finish();
    }
}
