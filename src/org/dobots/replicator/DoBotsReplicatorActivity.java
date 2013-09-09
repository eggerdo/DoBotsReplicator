package org.dobots.replicator;

import org.dobots.communication.zmq.ZmqHandler;
import org.dobots.utilities.BaseActivity;

import robots.RobotType;
import robots.gui.RobotLaunchHelper;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DoBotsReplicatorActivity extends BaseActivity {

//	private ZmqHandler mZmqHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		mZmqHandler = ZmqHandler.initialize(this);
		RobotLaunchHelper.showRobot(this, RobotType.RBT_REPLICATOR);
	}

	@Override
	protected void onResume() {
		super.onResume();
		finish();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
//		mZmqHandler.onDestroy();
	}
}
