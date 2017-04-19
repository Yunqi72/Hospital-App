package com.srdz.bigproject.communal;

import com.srdz.bigproject.R;
import android.app.Activity;
import android.app.NotificationManager;

public class NotificationRegistrationThread extends Thread {

	private Activity activity;
	private NotificationManager nm;

	public NotificationRegistrationThread(Activity activity, NotificationManager nm) {
		this.activity = activity;
		this.nm = nm;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public int random() {
		int x = (int) (Math.random() * 100);
		return x;
	}

	@Override
	public void run() {
		int rd = (int) (Math.random() * 100);
		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ShowNotification sn = new ShowNotification(getActivity(), nm, 6435131);
		sn.showNotification(R.drawable.ic_launcher, "��ϲ�㣬�Һųɹ�", "��ϲ�㣬�Һųɹ�",
				"�Һųɹ��ˣ���ţ�" + rd + "���뾡��ȥҽԺ��ҽ��");
	}
}