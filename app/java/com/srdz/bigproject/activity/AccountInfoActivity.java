package com.srdz.bigproject.activity;

import cn.bmob.v3.BmobUser;
import com.srdz.bigproject.R;
import com.srdz.bigproject.bean.UserData;
import com.srdz.bigproject.communal.PageMenu;
import com.srdz.bigproject.view.SlidingLayout;
import android.app.TabActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class AccountInfoActivity extends TabActivity implements
		OnTabChangeListener {

	private ImageView headIcon;
	private TextView name;
	private TextView sex;
	private TextView add;
	private TabHost mTabHost;
	// �໬�˵�
	private SlidingLayout slidingLayout;// �໬���ֶ�������ͨ����ָ���������Ĳ˵����ֽ�����ʾ������
	private ImageButton muneButton;// menu��ť�������ťչʾ��಼�֣��ٵ��һ��������಼��
	private LinearLayout contentListView;// ����content�����е�ListView

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.account_info);
		headIcon = (ImageView) findViewById(R.id.account_info_icon);
		name = (TextView) findViewById(R.id.account_info_name);
		sex = (TextView) findViewById(R.id.account_info_sex);
		add = (TextView) findViewById(R.id.account_info_add);
		// ��ȡ�û���Ϣ
		UserData userInfo = BmobUser.getCurrentUser(AccountInfoActivity.this,
				UserData.class);
		// ����ͷ��
		// ��������
		this.name.setText(userInfo.getUserPersonName());
		String sex = null;
		if (userInfo.isUserSex()) {
			sex = "��";
		} else {
			sex = "Ů";
		}
		this.sex.setText(sex);
		this.add.setText(userInfo.getUserProvince() + "ʡ"
				+ userInfo.getUserCity() + "��");

		// �໬�˵�
		slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout);
		muneButton = (ImageButton) findViewById(R.id.account_info_muenuButton);
		contentListView = (LinearLayout) findViewById(R.id.account_info_contentList);
		// �����������¼�����contentListView��
		slidingLayout.setScrollEvent(contentListView);
		muneButton.setOnClickListener(new OnClickListener() {
			PageMenu pm = new PageMenu(AccountInfoActivity.this, 0);

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

		// �л���
		mTabHost = getTabHost();
		mTabHost.addTab(mTabHost.newTabSpec("tab1")
				.setIndicator(getString(R.string.account_info_xinxi))
				.setContent(R.id.account_info_tab1));
		mTabHost.addTab(mTabHost.newTabSpec("tab2")
				.setIndicator(getString(R.string.account_info_bingli))
				.setContent(R.id.account_info_tab2));
		mTabHost.addTab(mTabHost.newTabSpec("tab3")
				.setIndicator(getString(R.string.account_info_shoucang))
				.setContent(R.id.account_info_tab3));
		mTabHost.setCurrentTab(0);
		// mTabHost.setOnTabChangedListener(this);
	}

	@Override
	public void onTabChanged(String tabId) {

	}
}