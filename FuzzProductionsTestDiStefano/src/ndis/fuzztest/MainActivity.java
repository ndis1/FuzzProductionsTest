package ndis.fuzztest;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import row.JsonLstElement;
import row.LazyAdapterAssignsImageLayoutorTextLayoutBasedType;
import ImgLoader.ImageLoader;
import Tabs.AllFragment;
import Tabs.AllFragment.OnAllSelectedListener;
import Tabs.ImageFragment;
import Tabs.ImageFragment.OnImageFragmentSelectedListener;
import Tabs.MyFragmentPagerAdapter;
import Tabs.TextFragment;
import Tabs.TextFragment.OnTextFragmentSelectedListener;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
 
public class MainActivity extends FragmentActivity implements OnImageFragmentSelectedListener,OnTextFragmentSelectedListener,OnAllSelectedListener {
 
    private ProgressDialog pDialog;
    MyFragmentPagerAdapter pagerAdapter;
    private static String jsonUrl = "http://dev.fuzzproductions.com/MobileTest/test.json";
    
    private static LazyAdapterAssignsImageLayoutorTextLayoutBasedType allAdapter;
    private static LazyAdapterAssignsImageLayoutorTextLayoutBasedType textAdapter;
    private static LazyAdapterAssignsImageLayoutorTextLayoutBasedType imageAdapter;
    private  String[] labels = {"All", "Text", "Images"};

    private static final String TAG_ID = "id";
    private static final String TAG_TYPE = "type";
    private static final String TAG_DATA = "data";
    
    JSONArray allElements = null;
 
    ArrayList<JsonLstElement> All;
    ArrayList<JsonLstElement> Images;
    ArrayList<JsonLstElement> Text;

    private ImageLoader imgLoader;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        All = new ArrayList<JsonLstElement>();
        Images = new ArrayList<JsonLstElement>();
        Text = new ArrayList<JsonLstElement>();
        imgLoader = new ImageLoader(this);
        
        new GetJson().execute();
    }
 
    /**
     * Async task class to get json by making HTTP call
     * */
    private class GetJson extends AsyncTask<Void, Void, Void> {
 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
 
        }
 
        @Override
        protected Void doInBackground(Void... arg0) {
            ServiceHandler sh = new ServiceHandler();
            String jsonStr = sh.makeServiceCall(jsonUrl, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);
 
            if (jsonStr != null) {
                try {
                    allElements = new JSONArray(jsonStr);
                    for (int i = 0; i < allElements.length(); i++) {
                        JSONObject c = allElements.getJSONObject(i);

                        String id = c.getString(TAG_ID);
                        String type = c.getString(TAG_TYPE);
                        if(c.has(TAG_DATA)){
                        	
                        	String data = c.getString(TAG_DATA);
                            JsonLstElement newListElement = new JsonLstElement(id,type,data);

                            All.add(newListElement);
                            if (type.equals("text")){
                            	Text.add(newListElement);
                            }
                            if (type.equals("image")){
                            	Images.add(newListElement);
                            }
                        }
                    }
                     allAdapter = new LazyAdapterAssignsImageLayoutorTextLayoutBasedType(MainActivity.this, All,imgLoader);
                     textAdapter = new LazyAdapterAssignsImageLayoutorTextLayoutBasedType(MainActivity.this, Text,imgLoader);
                     imageAdapter = new LazyAdapterAssignsImageLayoutorTextLayoutBasedType(MainActivity.this, Images,imgLoader);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            return null;
        }
 
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            //after json is loaded, set up the tab fragments
            setupTabs();
    }
    private void setupTabs(){
    	 
        /** Getting a reference to the ViewPager defined the layout file */
        final ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        getActionBar().setSelectedNavigationItem(position);
                    }
                });
        FragmentManager fm = getSupportFragmentManager();
          pagerAdapter = new MyFragmentPagerAdapter(fm);
 
        pager.setAdapter(pagerAdapter);
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            

			@Override
			public void onTabReselected(Tab arg0,
					android.app.FragmentTransaction arg1) {    				
			}

			@Override
			public void onTabSelected(Tab tab,
					android.app.FragmentTransaction arg1) {
	            pager.setCurrentItem(tab.getPosition());

			}

			@Override
			public void onTabUnselected(Tab arg0,
					android.app.FragmentTransaction arg1) {    				
			}
        };

        // Add 3 tabs, specifying the tab's text and TabListener
        for (int i = 0; i < 3; i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(labels[i])
                            .setTabListener(tabListener));
        }
    }
    }
   
	@Override
	public void onArticleSelected(int position) {

		 ImageFragment articleFrag = (ImageFragment) pagerAdapter.getRegisteredFragment(position);
		 articleFrag.setAd(imageAdapter);
		
	}
	@Override
	public void onTextSelected(int position) {

		 TextFragment articleFrag = (TextFragment) pagerAdapter.getRegisteredFragment(position);
		 articleFrag.setAd(textAdapter);
				
	}

	@Override
	public void onAllSelected(int position) {

		AllFragment articleFrag = (AllFragment) pagerAdapter.getRegisteredFragment(position);
		 articleFrag.setAd(allAdapter);
					
	}
}