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
 
public class TextFragment extends BaseFragment{
 
    OnTextFragmentSelectedListener mCallback;
	public interface OnTextFragmentSelectedListener {
        public void onTextSelected(int position);
    }
	 @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        
	        try {
	            mCallback = (OnTextFragmentSelectedListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement OnHeadlineSelectedListener");
	        }
	    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
    }

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View v = inflater.inflate(R.layout.frag_lay, container,false);
        
        mCallback.onTextSelected(position);
        setListAdapter(adapter);
        
        return v;
    }
 
}