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
import android.widget.TextView;

import java.util.ArrayList;

import edu.eci.com.foreignmobile.R;


public class AdapterItemMyTutorials extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<TutoriaItem> items;

    public AdapterItemMyTutorials(Activity activity, ArrayList<TutoriaItem> items) {
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

    public void addAll(ArrayList<TutoriaItem> tutor) {
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
            v = inf.inflate(R.layout.item_mis_tutorias, null);
        }

        TutoriaItem dir = items.get(position);

        TextView nameTutor = (TextView) v.findViewById(R.id.nametutor);
        nameTutor.setText("Profesor : "+dir.getName_profesor());

        TextView date = (TextView) v.findViewById(R.id.date);
        date.setText("Fecha : "+dir.getDate().toString());

        TextView language = (TextView) v.findViewById(R.id.language);
        language.setText("Lenguage : "+dir.getLanguage());

        return v;
    }
}