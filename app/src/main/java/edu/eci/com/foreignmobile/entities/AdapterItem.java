package edu.eci.com.foreignmobile.entities;

/**
 * Created by tata on 25/04/17.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.eci.com.foreignmobile.R;


public class AdapterItem extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Tutor> items;

    public AdapterItem (Activity activity, ArrayList<Tutor> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Tutor> tutor) {
        for (int i = 0; i < tutor.size(); i++) {
            items.add(tutor.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_tutor, null);
        }

        Tutor dir = items.get(position);

        TextView title = (TextView) v.findViewById(R.id.category);
        title.setText(dir.getTitle());

        TextView description = (TextView) v.findViewById(R.id.texto);
        description.setText(dir.getDescription());

        TextView costo = (TextView) v.findViewById(R.id.costo);
        costo.setText(dir.getCost());

        ImageView imagen = (ImageView) v.findViewById(R.id.imageView4);
        imagen.setImageDrawable(dir.getImage());

        return v;
    }
}