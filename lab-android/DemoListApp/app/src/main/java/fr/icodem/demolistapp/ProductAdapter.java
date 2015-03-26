package fr.icodem.demolistapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private final Context mContext;

    private List<Product> products = new ArrayList<Product>();
    private BitmapLoader<Integer> bitmapLoader = new BitmapLoader<Integer>(Constants.IMAGE_BASE_URL);

    static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }

    public ProductAdapter(Context context) {
        super();
        mContext = context;
    }

    public void clear() {
        products.clear();
    }
    public void addAll(List<Product> products) {
        if (products == null) return;
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        Product p = (Product)getItem(position);

        ViewHolder viewHolder;

        if (convertView == null) {
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.product_row, container, false);

            // well set up the ViewHolder
            viewHolder = new ViewHolder();
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.icon);
            viewHolder.textView= (TextView) convertView.findViewById(R.id.label);

            // store the holder with the view.
            convertView.setTag(viewHolder);

        } else {
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(p.getName());
        bitmapLoader.loadBitmap(mContext, p.getId(), viewHolder.imageView);

        return convertView;

    }

}
