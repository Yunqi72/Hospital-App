package com.srdz.bigproject.communal;

import com.srdz.bigproject.activity.MainActivity;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

// ����ʱ����onCreate()�����г�ʼ��
// nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

public class ShowNotification {

	private Activity activity;
	private NotificationManager nm;
	private int notificationId;// ����һ��Ψһ��ID���������

	public ShowNotification(Activity activity, NotificationManager nm,
			int notificationId) {
		super();
		this.activity = activity;
		this.nm = nm;
		this.notificationId = notificationId;
	}

	@SuppressWarnings("deprecation")
	public void showNotification(int icon, String tickertext, String title,
			String content) {
		// ����һ��Ψһ��ID���������
		// Notification������
		Notification notification = new Notification(icon, tickertext,
				System.currentTimeMillis());
		// ����Ĳ����ֱ�����ʾ�ڶ���֪ͨ����Сͼ�꣬Сͼ���Ե����֣�������ʾ���Զ���ʧ��ϵͳ��ǰʱ��
		notification.defaults = Notification.DEFAULT_ALL;
		// ��������֪ͨ�Ƿ�ͬʱ�����������񶯣�����ΪNotification.DEFAULT_SOUND
		// ��ΪNotification.DEFAULT_VIBRATE;
		// LightΪNotification.DEFAULT_LIGHTS�����ҵ�Milestone�Ϻ���ûʲô��Ӧ
		// ȫ��ΪNotification.DEFAULT_ALL
		// ������񶯻���ȫ����������AndroidManifest.xml������Ȩ��
		PendingIntent pt = PendingIntent.getActivity(activity, 0, new Intent(
				activity, MainActivity.class), 0);
		// ���֪ͨ��Ķ�����������ת��main ���Acticity
		notification.setLatestEventInfo(activity, title, content, pt);
		nm.notify(notificationId, notification);
	}
}