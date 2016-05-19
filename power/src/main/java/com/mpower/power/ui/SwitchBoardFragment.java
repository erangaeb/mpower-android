package com.mpower.power.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.mpower.power.MPowerApplication;
import com.mpower.power.R;
import com.mpower.power.utils.ActivityUtils;
import com.mpower.power.utils.MPowerConstants;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;


/**
 * Switch board fragment content
 */
public class SwitchBoardFragment extends Fragment implements View.OnClickListener {

    // switch texts
    TextView switch1Text;
    TextView switch2Text;
    TextView switch3Text;
    TextView switch4Text;

    RelativeLayout switch1;
    RelativeLayout switch2;

    Switch kitchenSwitch;
    Switch bedRoomSwitch;

    // set custom font
    Typeface typefaceThin;
    Typeface typefaceBlack;

    boolean isSwitchedOn = false;
    boolean isKitchen = true;

    static final String TAG = SwitchBoardFragment.class.getName();

    private final WebSocketConnection webSocketConnection = new WebSocketConnection();

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
        switch1Text = (TextView) this.getActivity().findViewById(R.id.switch1_text);
        switch2Text = (TextView) this.getActivity().findViewById(R.id.switch2_text);
        switch3Text = (TextView) this.getActivity().findViewById(R.id.switch3_text);
        switch4Text = (TextView) this.getActivity().findViewById(R.id.switch4_text);

        switch1Text.setTypeface(typefaceThin, Typeface.NORMAL);
        switch2Text.setTypeface(typefaceThin, Typeface.NORMAL);
        switch3Text.setTypeface(typefaceThin, Typeface.NORMAL);
        switch4Text.setTypeface(typefaceThin, Typeface.NORMAL);

        switch1 = (RelativeLayout) this.getActivity().findViewById(R.id.switch1);
        switch2 = (RelativeLayout) this.getActivity().findViewById(R.id.switch2);

        kitchenSwitch = (Switch) this.getActivity().findViewById(R.id.kitchen_switch);
        bedRoomSwitch = (Switch) this.getActivity().findViewById(R.id.bed_room_switch);
        kitchenSwitch.setOnClickListener(this);
        bedRoomSwitch.setOnClickListener(this);

        if (MPowerApplication.STATE.equals(MPowerApplication.OVER)) {
           //loadOverUsageSwitchBoard();
        } else {
            //loadNormalUsageSwitchBoard();
        }
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
     * Display normal consumption switch board
     * All switches are green
     */
    private void loadNormalUsageSwitchBoard() {
        switch2.setBackgroundResource(R.drawable.green_button_selector);
        switch2Text.setText("Kitchen");
    }

    /**
     * Display over consumption switch board
     * All switches are green
     */
    private void loadOverUsageSwitchBoard() {
        switch2.setBackgroundResource(R.drawable.red_button_selector);
        switch2Text.setText("Kitchen");
    }

    /**
     * Connect to senz web socket server and sends a query
     * When DATA query receives we assume operation completed and disconnect from the senz service
     *
     * @param switchingQuery query to switch on/off
     */
    private void sendQueryToSenzServer(final String switchingQuery) {
        try {
            webSocketConnection.connect(MPowerConstants.SENZ_SERVER, new WebSocketHandler() {
                @Override
                public void onOpen() {
                    // send login query here
                    String loginQuery = "LOGIN #username eranga #password tess";
                    Log.d(TAG, "Connected to " + MPowerConstants.SENZ_SERVER);
                    Log.d(TAG, "Sending query... " + "LOGIN #username eranga #password tess");
                    webSocketConnection.sendTextMessage(loginQuery);
                }

                @Override
                public void onTextMessage(String payload) {
                    Log.d(TAG, "Got message: " + payload);
                    if (payload.contains("LOGIN")) {
                        Log.d(TAG, "Sending query: " + switchingQuery);
                        webSocketConnection.sendTextMessage(switchingQuery);
                    } else if(payload.contains("DONE")) {
                        // disconnect from senz
                        ActivityUtils.cancelProgressDialog();
                        if (isSwitchedOn) {
                            if (isKitchen) {
                                switch2.setBackgroundResource(R.drawable.green_button_selector);
                            } else {
                                switch1.setBackgroundResource(R.drawable.green_button_selector);
                            }

                            Toast.makeText(SwitchBoardFragment.this.getActivity(), "Switched on", Toast.LENGTH_LONG).show();
                        } else {
                            if (isKitchen) {
                                switch2.setBackgroundResource(R.drawable.disable_bg);
                            } else {
                                switch1.setBackgroundResource(R.drawable.disable_bg);
                            }

                            Toast.makeText(SwitchBoardFragment.this.getActivity(), "Switched off", Toast.LENGTH_LONG).show();
                        }
                        webSocketConnection.disconnect();
                    }
                }

                @Override
                public void onClose(int code, String reason) {
                    Log.d(TAG, "Disconnected form senz...");
                }
            });
        } catch (WebSocketException e) {
            Log.d(TAG, e.toString());
        }
    }

    @Override
    public void onClick(View v) {
        if (v == switch1) {
            // do nothing
        } else if (v == bedRoomSwitch) {
            // do nothing
            isSwitchedOn = bedRoomSwitch.isChecked();
            isKitchen = false;
            if (isSwitchedOn) {
                // off query
                ActivityUtils.showProgressDialog(this.getActivity(), "Switching on...");
                sendQueryToSenzServer("PUT #switch bedroom @home");
            } else {
                // on query
                ActivityUtils.showProgressDialog(this.getActivity(), "Switching off...");
                sendQueryToSenzServer(":PUT #switch bedroom @home");
            }
        } else if(v == kitchenSwitch) {

            isSwitchedOn = kitchenSwitch.isChecked();
            isKitchen = true;
            if (isSwitchedOn) {
                // off query
                ActivityUtils.showProgressDialog(this.getActivity(), "Switching on...");
                sendQueryToSenzServer("PUT #switch kitchen @home");
            } else {
                // on query
                ActivityUtils.showProgressDialog(this.getActivity(), "Switching off...");
                sendQueryToSenzServer(":PUT #switch kitchen @home");
            }
        }
    }
}
