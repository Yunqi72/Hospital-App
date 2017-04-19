package com.srdz.bigproject.communal;

import com.srdz.bigproject.R;
import android.app.Activity;
import android.app.NotificationManager;

public class NotificationDoctorThread extends Thread {

	private Activity activity;
	private NotificationManager nm;

	public NotificationDoctorThread(Activity activity, NotificationManager nm) {
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
		ShowNotification sn = new ShowNotification(getActivity(), nm, 6435541);
		sn.showNotification(R.drawable.ic_launcher, "��ϲ�㣬ԤԼ�ɹ�", "��ϲ�㣬ԤԼ�ɹ�",
				"ԤԼ�ɹ��ˣ���ţ�" + rd + "���뾡��ȥҽԺ��ҽ��");
	}
}