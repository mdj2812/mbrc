package com.kelsos.mbrc.adapters;


import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kelsos.mbrc.R;
import com.kelsos.mbrc.data.ArtistEntry;

import java.util.ArrayList;

public class ArtistEntryAdapter extends ArrayAdapter<ArtistEntry> {
    private Context mContext;
    private int mResource;
    private ArrayList<ArtistEntry> mData;
    private Typeface robotoRegular;
    private MenuItemSelectedListener mListener;

    public ArtistEntryAdapter(Context context, int resource, ArrayList<ArtistEntry> objects) {
        super(context, resource, objects);
        this.mResource = resource;
        this.mContext = context;
        this.mData = objects;
        robotoRegular = Typeface.createFromAsset(mContext.getAssets(), "fonts/roboto_regular.ttf");
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder holder;

        if (row == null) {
            LayoutInflater layoutInflater = ((Activity) mContext).getLayoutInflater();
            row = layoutInflater.inflate(mResource, parent, false);

            holder = new Holder();
            holder.title = (TextView) row.findViewById(R.id.line_one);
            holder.title.setTypeface(robotoRegular);

            holder.indicator = (LinearLayout) row.findViewById(R.id.ui_item_context_indicator);

            row.setTag(holder);
        } else {
            holder = (Holder) row.getTag();
        }

        final ArtistEntry entry = mData.get(position);
        holder.title.setText(entry.getArtist());

        holder.indicator.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                popupMenu.inflate(R.menu.popup_artist);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override public boolean onMenuItemClick(MenuItem menuItem) {
                        if (mListener != null) {
                            mListener.OnMenuItemSelected(menuItem, entry);
                            return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        return row;
    }

    public void setMenuItemSelectedListener(MenuItemSelectedListener listener) {
        mListener = listener;
    }

    public interface MenuItemSelectedListener {
        void OnMenuItemSelected(MenuItem menuItem, ArtistEntry entry);
    }

    static class Holder {
        TextView title;
        LinearLayout indicator;
    }

}
