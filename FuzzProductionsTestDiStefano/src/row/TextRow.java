package row;

import ndis.fuzztest.R;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


public class TextRow implements Row {
    private final JsonLstElement listElement;
    private final LayoutInflater inflater;

    public TextRow(LayoutInflater inflater, JsonLstElement listElement) {
        this.listElement = listElement;
        this.inflater = inflater;
    }
    
    private static class ViewHolder {
        final TextView txt;

        private ViewHolder(TextView txt) {
            this.txt = txt;
        }
    }
    
    public View getView(View convertView) {
        ViewHolder holder;
        View view;
        if (convertView == null) {
            View vi = (View)inflater.inflate(R.layout.text_item, null);
            holder = new ViewHolder((TextView)vi.findViewById(R.id.name));
            vi.setTag(holder);
            view = vi;
        } else {
            view = convertView;
            holder = (ViewHolder)convertView.getTag();
        }
        holder.txt.setText(listElement.getData());
        return view;
    }

    public int getViewType() {
        return RowType.TEXT_ROW.ordinal();
    }

    
}
