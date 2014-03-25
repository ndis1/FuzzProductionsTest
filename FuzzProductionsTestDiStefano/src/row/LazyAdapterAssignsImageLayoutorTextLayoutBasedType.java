package row;


import java.util.ArrayList;

import ImgLoader.ImageLoader;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class LazyAdapterAssignsImageLayoutorTextLayoutBasedType extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<JsonLstElement> data;
    private static LayoutInflater inflater=null;
    private ArrayList<Row> rows;
    public ImageLoader imageLoader; 
   
    public LazyAdapterAssignsImageLayoutorTextLayoutBasedType(Activity a, ArrayList<JsonLstElement> d, ImageLoader iml) {
        rows = new ArrayList<Row>();
        activity = a;
        imageLoader=iml;
        data=d;
        for(JsonLstElement s : data){
        	if(s.getType().equals("image")){
                rows.add(new ImageRow(LayoutInflater.from(activity), s,imageLoader));
        	}else{
                rows.add(new TextRow(LayoutInflater.from(activity), s));
        	}
        }
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    @Override
    public int getViewTypeCount() {
        return RowType.values().length;
    }

    @Override
    public int getItemViewType(int position) {
        return rows.get(position).getViewType();
    }

    public int getCount() {
        return rows.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        return rows.get(position).getView(convertView);
    }
}