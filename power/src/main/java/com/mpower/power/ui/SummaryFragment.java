package com.mpower.power.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mpower.power.R;
import com.mpower.power.adapters.SummaryAdapter;
import com.mpower.power.enums.PowerUsage;
import com.mpower.power.pojos.Summary;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.ArrayList;

/**
 * Display power usage summary
 *
 * @author eranga herath(eranga.herath@pagero.com)
 */
public class SummaryFragment extends android.support.v4.app.Fragment {

    // list view components
    private ListView summaryListView;
    private ArrayList<Summary> summaryList;
    private SummaryAdapter adapter;

    // use custom font here
    private Typeface typeface;

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.summary_layout, container, false);

        return root;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/vegur_2.otf");

        initSummaryListView();
        initSummaryList();
    }

    /**
     * Initialize UI components
     */
    private void initSummaryListView() {
        summaryListView = (ListView)getActivity().findViewById(R.id.summary_list_view);

        // add header and footer for list
        View headerView = View.inflate(this.getActivity(), R.layout.list_header, null);
        View footerView = View.inflate(this.getActivity(), R.layout.list_header, null);
        summaryListView.addHeaderView(headerView);
        summaryListView.addFooterView(footerView);

        // set up click listener
        summaryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position>0 && position <= summaryList.size()) {
                }
            }
        });
    }

    /**
     * Add content to summary list
     */
    private void initSummaryList() {
        summaryList = new ArrayList<Summary>();
        summaryList.add(new Summary("2015-03-10", PowerUsage.NORMAL, "230 V", "1400 MHz", "1200 A"));
        summaryList.add(new Summary("2015-03-09", PowerUsage.NORMAL, "232 V", "1430 MHz", "1300 A"));
        summaryList.add(new Summary("2015-03-08", PowerUsage.NORMAL, "231 V", "1420 MHz", "1100 A"));
        summaryList.add(new Summary("2015-03-07", PowerUsage.NORMAL, "234 V", "1410 MHz", "1400 A"));
        summaryList.add(new Summary("2015-03-06", PowerUsage.MID, "235 V", "1240 MHz", "1700 A"));
        summaryList.add(new Summary("2015-03-05", PowerUsage.MID, "232 V", "1100 MHz", "1500 A"));
        summaryList.add(new Summary("2015-03-04", PowerUsage.NORMAL, "230 V", "1400 MHz", "1600 A"));
        summaryList.add(new Summary("2015-03-03", PowerUsage.OVER, "250 V", "1500 MHz", "1300 A"));

        adapter = new SummaryAdapter(SummaryFragment.this.getActivity(), summaryList);
        adapter.notifyDataSetChanged();
        summaryListView.setAdapter(adapter);
    }

}
