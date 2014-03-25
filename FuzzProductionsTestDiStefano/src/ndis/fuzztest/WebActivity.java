package ndis.fuzztest;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
 
public class WebActivity extends Activity {
 
	private WebView webview;
 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		 WebView wv=(WebView)findViewById(R.id.webview);
		         wv.getSettings().setJavaScriptEnabled(true);
		         wv.loadUrl(this.getIntent().getDataString());
	}
 
}