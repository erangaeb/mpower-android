package com.mpower.power.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mpower.power.MPowerApplication;
import com.mpower.power.R;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

/**
 * Fragment to display today power consumption
 *
 * @author eranga herath(eranga.herath@pagero.com)
 */
public class TodayFragment extends android.support.v4.app.Fragment {

    // UI Components
    LinearLayout consumptionSummary;
    TextView voltage;
    TextView current;
    TextView frequency;

    // use custom font here
    private Typeface typeface;

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_today, container, false);

        return root;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/vegur_2.otf");

        consumptionSummary = (LinearLayout) getActivity().findViewById(R.id.today_consumption_summary);
        voltage = (TextView) getActivity().findViewById(R.id.voltage);
        current = (TextView) getActivity().findViewById(R.id.current);
        frequency = (TextView) getActivity().findViewById(R.id.frequency);

        voltage.setTypeface(typeface, Typeface.BOLD);
        current.setTypeface(typeface, Typeface.BOLD);
        frequency.setTypeface(typeface, Typeface.BOLD);

        displayConsumption();
        displayConsumptionGraph();
    }

    /**
     * {@inheritDoc}
     */
    public void onResume() {
        super.onResume();
    }

    /**
     * {@inheritDoc}
     */
    public void onPause() {
        super.onPause();
    }

    /**
     * display power consumption values
     */
    private void displayConsumption() {
        if (MPowerApplication.STATE.equals(MPowerApplication.OVER)) {
            // load over values
            consumptionSummary.setBackgroundResource(R.drawable.circle_shape_red);
            voltage.setText("240V");
            current.setText("23 A");
            frequency.setText("55 MHz");
        } else {
            // load normal usage data
            consumptionSummary.setBackgroundResource(R.drawable.circle_shape_green);
            voltage.setText("230V");
            current.setText("23 A");
            frequency.setText("55 MHz");
        }
    }

    /**
     * Display consumption in a graph
     */
    private void displayConsumptionGraph() {
        ValueLineChart mCubicValueLineChart = (ValueLineChart) this.getActivity().findViewById(R.id.cubiclinechart);
        ValueLineSeries series = new ValueLineSeries();

        if (MPowerApplication.STATE.equals(MPowerApplication.OVER)) {
            series.setColor(0xFFd96459);
            series.addPoint(new ValueLinePoint("8.00 AM", 230f));
            series.addPoint(new ValueLinePoint("9.00 AM", 240f));
            series.addPoint(new ValueLinePoint("10.00 AM", 234f));
            series.addPoint(new ValueLinePoint("11.00 AM", 220f));
            series.addPoint(new ValueLinePoint("12.00 AM", 250f));
            series.addPoint(new ValueLinePoint("01.00 PM", 230f));
            series.addPoint(new ValueLinePoint("02.00 PM", 220f));
            series.addPoint(new ValueLinePoint("03.00 PM", 234f));
            series.addPoint(new ValueLinePoint("04.00 PM", 224f));
        } else {
            series.setColor(0xFF63cbb0);
            series.addPoint(new ValueLinePoint("8.00 AM", 220f));
            series.addPoint(new ValueLinePoint("9.00 AM", 240f));
            series.addPoint(new ValueLinePoint("10.00 AM", 234f));
            series.addPoint(new ValueLinePoint("11.00 AM", 220f));
            series.addPoint(new ValueLinePoint("12.00 AM", 230f));
            series.addPoint(new ValueLinePoint("01.00 PM", 230f));
            series.addPoint(new ValueLinePoint("02.00 PM", 220f));
            series.addPoint(new ValueLinePoint("03.00 PM", 214f));
            series.addPoint(new ValueLinePoint("04.00 PM", 204f));
        }


        mCubicValueLineChart.addSeries(series);
        mCubicValueLineChart.startAnimation();
    }
}
