package org.dobots.replicator;

import org.dobots.communication.zmq.ZmqActivity;
import org.dobots.communication.zmq.ZmqConnectionHelper;
import org.dobots.communication.zmq.ZmqConnectionHelper.UseCase;
import org.dobots.utilities.BaseActivity;
import org.dobots.utilities.BaseApplication;

import robots.RobotType;
import robots.gui.RobotLaunchHelper;
import robots.gui.RobotView;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class DoBotsReplicatorActivity extends ZmqActivity {

	private ZmqConnectionHelper mZmqHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        // Register for broadcasts when a robot view is loaded
        IntentFilter filter = new IntentFilter(RobotView.VIEW_LOADED);
        registerReceiver(mReceiver, filter);
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		finish();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mZmqHelper.close();
		mZmqHandler.onDestroy();
	}

	@Override
	public void onZmqReady() {
		mZmqHelper = new ZmqConnectionHelper(UseCase.ROBOT);
		mZmqHelper.setup(mZmqHandler, this);
		
		RobotLaunchHelper.showRobot(this, RobotType.RBT_REPLICATOR);
	}

	@Override
	public void onZmqFailed() {
		showToast("Cannot start with invalid Zmq Settings!", Toast.LENGTH_LONG);
	}

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // When discovery finds a device
            if (RobotView.VIEW_LOADED.equals(action)) {
            	BaseActivity currentActivity = ((BaseApplication)context.getApplicationContext()).getCurrentActivity();
            	currentActivity.addMenuListener(mSettings);
            	currentActivity.addDialogListener(mSettings);
            };
        }
    };

}
