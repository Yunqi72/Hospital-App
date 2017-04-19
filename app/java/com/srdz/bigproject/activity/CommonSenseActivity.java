package com.srdz.bigproject.activity;

import com.srdz.bigproject.R;
import com.srdz.bigproject.communal.PageMenu;
import com.srdz.bigproject.view.SlidingLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class CommonSenseActivity extends Activity {

	private SlidingLayout slidingLayout;// �໬���ֶ�������ͨ����ָ���������Ĳ˵����ֽ�����ʾ������
	private ImageButton muneButton;// menu��ť�������ťչʾ��಼�֣��ٵ��һ��������಼��
	private LinearLayout contentListView;// ����content�����е�ListView

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ȥ������
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.common_sense);

		slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout);
		muneButton = (ImageButton) findViewById(R.id.common_sense_muenuButton);
		contentListView = (LinearLayout) findViewById(R.id.common_sense_contentList);

		// �����������¼�����contentListView��
		slidingLayout.setScrollEvent(contentListView);
		muneButton.setOnClickListener(new OnClickListener() {
			PageMenu pm = new PageMenu(CommonSenseActivity.this, 3);

			@Override
			public void onClick(View v) {
				if (slidingLayout.isLeftLayoutVisible()) {
					pm.delListener();
					slidingLayout.scrollToRightLayout();
				} else {
					pm.addListener();
					slidingLayout.scrollToLeftLayout();
				}
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent();
			intent.setClass(CommonSenseActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
		return true;
	}

}