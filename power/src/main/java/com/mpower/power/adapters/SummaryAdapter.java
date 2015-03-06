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
            view = layoutInflater.inflate(R.layout.history_list_row_layout, viewGroup, false);

            //create view holder to store reference to child views
            holder = new ViewHolder();
            holder.usage = (RelativeLayout) view.findViewById(R.id.history_list_usage);
            holder.usageText = (TextView) view.findViewById(R.id.history_list_usage_text);
            holder.date = (TextView) view.findViewById(R.id.history_list_date);
            holder.voltage = (TextView) view.findViewById(R.id.history_list_voltage);
            holder.current = (TextView) view.findViewById(R.id.history_list_current);
            holder.frequency = (TextView) view.findViewById(R.id.history_list_frequency);

            // set custom font
            holder.usageText.setTypeface(typefaceThin, Typeface.BOLD);
            holder.date.setTypeface(typefaceThin, Typeface.BOLD);
            holder.voltage.setTypeface(typefaceThin, Typeface.NORMAL);
            holder.current.setTypeface(typefaceThin, Typeface.NORMAL);
            holder.frequency.setTypeface(typefaceThin, Typeface.NORMAL);

            view.setTag(holder);
        } else {
            //get view holder back_icon
            holder = (ViewHolder) view.getTag();
        }

        if (summary.getUsage() == PowerUsage.NORMAL) {
            holder.usage.setBackgroundColor(Color.parseColor("#63cbb0"));
            holder.usageText.setText("NORMAL");
        } else if (summary.getUsage() == PowerUsage.MID) {
            holder.usage.setBackgroundColor(Color.parseColor("#CCffc027"));
            holder.usageText.setText("MID");
        } else {
            holder.usage.setBackgroundColor(Color.parseColor("#d96459"));
            holder.usageText.setText("OVER");
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
        RelativeLayout usage;
        TextView usageText;
        TextView date;
        TextView voltage;
        TextView current;
        TextView frequency;
    }

}
