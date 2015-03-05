package com.mpower.power.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mpower.power.R;
import com.mpower.power.enums.PowerUsage;
import com.mpower.power.pojos.Summary;

import java.util.ArrayList;

/**
 * Display summary list
 */
public class SummaryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Summary> summaryList;

    // set custom font
    Typeface typefaceThin;
    Typeface typefaceBlack;

    /**
     * Initialize context variables
     *
     * @param context activity context
     * @param summaryList summary list
     */
    public SummaryAdapter(Context context, ArrayList<Summary> summaryList) {
        typefaceThin = Typeface.createFromAsset(context.getAssets(), "fonts/vegur_2.otf");
        typefaceBlack = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Black.ttf");

        this.context = context;
        this.summaryList = summaryList;
    }

    /**
     * Get size of sensor list
     * @return userList size
     */
    @Override
    public int getCount() {
        return summaryList.size();
    }

    /**
     * Get specific item from sensor list
     * @param i item index
     * @return list item
     */
    @Override
    public Object getItem(int i) {
        return summaryList.get(i);
    }

    /**
     * Get sensor list item id
     * @param i item index
     * @return current item id
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Create list row view
     * @param i index
     * @param view current list item view
     * @param viewGroup parent
     * @return view
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // A ViewHolder keeps references to children views to avoid unnecessary calls
        // to findViewById() on each row.
        final ViewHolder holder;

        final Summary summary = (Summary) getItem(i);

        if (view == null) {
            //inflate sensor list row layout
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.s_row_layout, viewGroup, false);

            //create view holder to store reference to child views
            holder = new ViewHolder();
            holder.userIcon = (RelativeLayout) view.findViewById(R.id.friend_list_row_layout_user_icon);
            holder.usage = (TextView) view.findViewById(R.id.usage);
            holder.date = (TextView) view.findViewById(R.id.friend_list_row_layout_date);
            holder.voltage = (TextView) view.findViewById(R.id.friend_list_row_layout_vol);
            holder.current = (TextView) view.findViewById(R.id.friend_list_row_layout_cur);
            holder.frequency = (TextView) view.findViewById(R.id.friend_list_row_layout_frq);

            // set custom font
            holder.usage.setTypeface(typefaceBlack, Typeface.NORMAL);
            holder.date.setTypeface(typefaceThin, Typeface.BOLD);
            holder.voltage.setTypeface(typefaceThin, Typeface.BOLD);
            holder.current.setTypeface(typefaceThin, Typeface.BOLD);
            holder.frequency.setTypeface(typefaceThin, Typeface.BOLD);

            view.setTag(holder);
        } else {
            //get view holder back_icon
            holder = (ViewHolder) view.getTag();
        }

        if (summary.getUsage() == PowerUsage.NORMAL) {
            holder.usage.setText("Normal usage");
            holder.usage.setTextColor(Color.parseColor("#63cbb0"));
            holder.date.setTextColor(Color.parseColor("#63cbb0"));
            holder.voltage.setTextColor(Color.parseColor("#63cbb0"));
            holder.current.setTextColor(Color.parseColor("#63cbb0"));
            holder.frequency.setTextColor(Color.parseColor("#63cbb0"));
            holder.userIcon.setBackgroundResource(R.drawable.circle_shape_green);
        } else if (summary.getUsage() == PowerUsage.MID) {
            holder.userIcon.setBackgroundResource(R.drawable.circle_shape_orange);
            holder.usage.setText("Mid usage");
            holder.usage.setTextColor(Color.parseColor("#CCffc027"));
            holder.date.setTextColor(Color.parseColor("#CCffc027"));
            holder.voltage.setTextColor(Color.parseColor("#CCffc027"));
            holder.frequency.setTextColor(Color.parseColor("#CCffc027"));
            holder.current.setTextColor(Color.parseColor("#CCffc027"));
        } else {
            holder.userIcon.setBackgroundResource(R.drawable.circle_shape_red);
            holder.usage.setText("Over usage");
            holder.usage.setTextColor(Color.parseColor("#d96459"));
            holder.date.setTextColor(Color.parseColor("#d96459"));
            holder.voltage.setTextColor(Color.parseColor("#d96459"));
            holder.current.setTextColor(Color.parseColor("#d96459"));
            holder.frequency.setTextColor(Color.parseColor("#d96459"));
        }

        holder.date.setText(summary.getDate());
        holder.voltage.setText(summary.getVoltage());
        holder.current.setText(summary.getCurrent());
        holder.frequency.setText(summary.getFrequency());

        return view;
    }

    /**
     * Keep reference to children view to avoid unnecessary calls
     */
    static class ViewHolder {
        RelativeLayout userIcon;
        TextView usage;
        TextView date;
        TextView voltage;
        TextView current;
        TextView frequency;
    }

}
