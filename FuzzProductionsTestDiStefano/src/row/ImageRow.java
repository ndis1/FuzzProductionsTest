package row;

import ndis.fuzztest.R;
import ImgLoader.ImageLoader;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class ImageRow implements Row {
    private final JsonLstElement listElement;
    private final LayoutInflater inflater;
    private ImageLoader imageLoader;

    public ImageRow(LayoutInflater inflater, JsonLstElement listElement,ImageLoader imageLoader) {
        this.listElement = listElement;
        this.inflater = inflater;
        this.imageLoader = imageLoader;
    }

    public View getView(View convertView) {
        ViewHolder holder;
        View view;
        if (convertView == null) {
            View vi = (View)inflater.inflate(R.layout.image_item, null);
            holder = new ViewHolder((ImageView)vi.findViewById(R.id.image));
	        imageLoader.DisplayImage(listElement.getData(), holder.image);
            vi.setTag(holder);
            view = vi;
        } else {
            view = convertView;
            holder = (ViewHolder)convertView.getTag();
        }
        imageLoader.DisplayImage(listElement.getData(), holder.image);
        return view;
    }

    public int getViewType() {
        return RowType.IMAGE_ROW.ordinal();
    }

    private static class ViewHolder {
        final ImageView image;

        private ViewHolder(ImageView image) {
            this.image = image;
        }
    }
}
