package com.srdz.bigproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import com.srdz.bigproject.R;

public class SplashScreenActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		final View view = View.inflate(this, R.layout.splash_screen, null);
		setContentView(view);
		// ��ʼ�� Bmob SDK
		Bmob.initialize(this, "f20be0efcb9e800d5d73ca8667225f6d");
		// ʹ�����ͷ���ʱ�ĳ�ʼ������
		BmobInstallation.getCurrentInstallation(this).save();
		// �������ͷ���
		// BmobPush.startWork(this, "f20be0efcb9e800d5d73ca8667225f6d");

		// ����չʾ������,����ͨ�������������˿���Ӧ�ó���Ľ���
		AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
		aa.setDuration(2000);
		view.startAnimation(aa);
		// ��������Ӽ�������
		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				redirectTo();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}
		});
	}

	private void redirectTo() {
		Intent intent = new Intent(SplashScreenActivity.this,
				LoginActivity.class);
		startActivity(intent);
		finish();
	}
}