package com.empty.ispy.Game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.empty.ispy.R;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Player> {

    private int resourceLayout;
    private Context mContext;

    public ListAdapter(Context context, int resource, List<Player> Players) {
        super(context, resource, Players);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, R.layout.item_vote);
        }

        Player p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.id);
            RadioButton radioGroup = (RadioButton) v.findViewById(R.id.)

            if (tt1 != null) {
                tt1.setText(p.);
            }

            if (tt2 != null) {
                tt2.setText(p.getCategory().getId());
            }

            if (tt3 != null) {
                tt3.setText(p.getDescription());
            }
        }

        return v;
    }

}