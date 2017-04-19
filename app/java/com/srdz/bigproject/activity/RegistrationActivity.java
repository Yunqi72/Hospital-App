package com.srdz.bigproject.activity;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.srdz.bigproject.R;
import com.srdz.bigproject.communal.PageMenu;
import com.srdz.bigproject.view.SlidingLayout;

public class RegistrationActivity extends Activity {

	private int hospitalId;
	private ListView list;
	// �໬�˵�
	private SlidingLayout slidingLayout;// �໬���ֶ�������ͨ����ָ���������Ĳ˵����ֽ�����ʾ������
	private ImageButton muneButton;// menu��ť�������ťչʾ��಼�֣��ٵ��һ��������಼��
	private LinearLayout contentListView;// ����content�����е�ListView

	public int[] Image = { R.drawable.hos_final1, R.drawable.hos_final2,
			R.drawable.hos_final3, R.drawable.hos_final4,
			R.drawable.hos_final_5 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);

		// �໬�˵�
		slidingLayout = (SlidingLayout) findViewById(R.id.slidingLayout);
		muneButton = (ImageButton) findViewById(R.id.registration_muenuButton);
		contentListView = (LinearLayout) findViewById(R.id.registration_contentList);
		// �����������¼�����contentListView��
		slidingLayout.setScrollEvent(contentListView);
		muneButton.setOnClickListener(new OnClickListener() {
			PageMenu pm = new PageMenu(RegistrationActivity.this, 2);

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

		// ��Layout�����ListView
		list = (ListView) findViewById(R.id.registration_listview1);

		// ���ɶ�̬���飬��������
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 5; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ItemImage", Image[i]);// ͼ����Դ��ID
			// map.put("ItemTitle", "Level "+i);
			listItem.add(map);
		}
		// ������������Item�Ͷ�̬�����Ӧ��Ԫ��
		SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,// ����Դ
				R.layout.forr_list,// ListItem��XMLʵ��
				// ��̬������ImageItem��Ӧ������
				new String[] { "ItemImage" },
				// ImageItem��XML�ļ������һ��ImageView,����TextView ID
				new int[] { R.id.forr_list_itemImage });
		// ��Ӳ�����ʾ
		list.setAdapter(listItemAdapter);
		// ��ӵ��
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				setYiyuanId(arg2);
				// switch (arg2) {
				// case 0:
				//
				// break;
				// }
				Intent intent = new Intent(RegistrationActivity.this,
						HospitalDetailActivity.class);
				startActivity(intent);
				System.out.println("�û�ѡ���˵�" + (hospitalId + 1) + "��ҽԺ��");
			}
		});

		// ��ӳ������
		// list.setOnCreateContextMenuListener(new OnCreateContextMenuListener()
		// {
		// @Override
		// public void onCreateContextMenu(ContextMenu menu, View v,
		// ContextMenuInfo menuInfo) {
		// menu.setHeaderTitle("ѡ��Һ�����");
		// menu.add(0, 0, 0, "��ͨ�Һ�");
		// menu.add(0, 1, 0, "����Һ�");
		// }
		// });
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent();
			intent.setClass(RegistrationActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
		return true;
	}

	public int getYiyuanId() {
		return hospitalId;
	}

	public void setYiyuanId(int yiyuanId) {
		this.hospitalId = yiyuanId;
	}
	// �����˵���Ӧ����
	// @Override
	// public boolean onContextItemSelected(MenuItem item) {
	// setTitle("ѡ����" + item.getItemId() + "ҽԺ");
	// return super.onContextItemSelected(item);
	// }
}
