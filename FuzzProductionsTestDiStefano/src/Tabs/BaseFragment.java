package Tabs;

import java.util.ArrayList;

import row.LazyAdapterAssignsImageLayoutorTextLayoutBasedType;

import ndis.fuzztest.MainActivity;
import ndis.fuzztest.R;
import ndis.fuzztest.WebActivity;
import ndis.fuzztest.R.layout;

import Tabs.ImageFragment.OnImageFragmentSelectedListener;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
 
public class BaseFragment extends ListFragment{
 
    LazyAdapterAssignsImageLayoutorTextLayoutBasedType adapter = null;
    int position;
    private static String webUrl = "http://fuzzproductions.com/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle data = getArguments();
        position = data.getInt("current_page", 0);
 
    }
    @Override
    public void onStart() {
        super.onStart();
        getListView().setOnItemClickListener(new OnItemClickListener() {
       	 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
            	Intent intent=new Intent(getActivity(), WebActivity.class);
                intent.setData(Uri.parse(webUrl));
                startActivity(intent);  
            }
        });
    }
    public void setAd(LazyAdapterAssignsImageLayoutorTextLayoutBasedType adapter){
    	this.adapter = adapter;
    }
 
    
 
}