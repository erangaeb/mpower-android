package com.mpower.power.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mpower.power.R;


/**
 * Switch board fragment content
 */
public class SwitchBoardFragment extends Fragment {

    // switch texts
    TextView switch1Icon;
    TextView switch2Icon;
    TextView switch3Icon;
    TextView switch4Icon;
    TextView switch1Text;
    TextView switch2Text;
    TextView switch3Text;
    TextView switch4Text;

    // set custom font
    Typeface typefaceThin;
    Typeface typefaceBlack;

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_switch_board, container, false);

        return root;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        typefaceThin = Typeface.createFromAsset(this.getActivity().getAssets(), "fonts/vegur_2.otf");
        typefaceBlack = Typeface.createFromAsset(this.getActivity().getAssets(), "fonts/Roboto-Black.ttf");

        initUi();
    }

    /**
     * Initialize UI components
     */
    private void initUi() {
        switch1Icon = (TextView) this.getActivity().findViewById(R.id.switch1_icon);
        switch2Icon = (TextView) this.getActivity().findViewById(R.id.switch2_icon);
        switch3Icon = (TextView) this.getActivity().findViewById(R.id.switch3_icon);
        switch4Icon = (TextView) this.getActivity().findViewById(R.id.switch4_icon);
        switch1Text = (TextView) this.getActivity().findViewById(R.id.switch1_text);
        switch2Text = (TextView) this.getActivity().findViewById(R.id.switch2_text);
        switch3Text = (TextView) this.getActivity().findViewById(R.id.switch3_text);
        switch4Text = (TextView) this.getActivity().findViewById(R.id.switch4_text);

        switch1Icon.setTypeface(typefaceThin, Typeface.BOLD);
        switch2Icon.setTypeface(typefaceThin, Typeface.BOLD);
        switch3Icon.setTypeface(typefaceThin, Typeface.BOLD);
        switch4Icon.setTypeface(typefaceThin, Typeface.BOLD);
        switch1Text.setTypeface(typefaceThin, Typeface.NORMAL);
        switch2Text.setTypeface(typefaceThin, Typeface.NORMAL);
        switch3Text.setTypeface(typefaceThin, Typeface.NORMAL);
        switch4Text.setTypeface(typefaceThin, Typeface.NORMAL);
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

}
