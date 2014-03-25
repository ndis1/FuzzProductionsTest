package Tabs;

import ndis.fuzztest.MainActivity;
import ndis.fuzztest.R;
import ndis.fuzztest.WebActivity;
import row.LazyAdapterAssignsImageLayoutorTextLayoutBasedType;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
 
public class ImageFragment extends BaseFragment{
    OnImageFragmentSelectedListener mCallback;
	public interface OnImageFragmentSelectedListener {
        public void onArticleSelected(int position);
    }
	 @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        
	        try {
	            mCallback = (OnImageFragmentSelectedListener) activity;
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
        mCallback.onArticleSelected(position);
        setListAdapter(adapter);
        return v;
    }
 
}