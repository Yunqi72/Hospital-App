package com.srdz.bigproject.activity;

import java.util.ArrayList;
import java.util.HashMap;

import com.srdz.bigproject.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class AgreementActivity extends Activity {
	String[] agree = {
			"���ײ������ԡ���ӭ��ʹ�á�ʩ�˶��Ρ����������Ϊʹ�á�ʩ�˶��Ρ����������ͳ�ơ������������������Ӧ���Ķ�������"
					+ "����ʩ�˶��Ρ������ɼ�����Э�顷�����¼�ơ���Э�顱�����Լ�����ʩ�˶��Ρ�����Э�顷��������������Ķ�����������������ݣ��ر�����������������ε����"
					+ "�Լ���ͨ��ʹ��ĳ�����ĵ���Э�飬��ѡ����ܻ򲻽��ܡ����ơ�������������ԼӴ���ʽ��ʾ��ע�⡣"
					+ "���������Ķ������ܱ�Э�����������������Ȩ���ء���װ��ʹ�ñ��������ط����������ء���װ��ʹ�á���¼����Ϊ����Ϊ�����Ķ���ͬ�ⱾЭ���Լ����"
					+ "�����δ��18���꣬���ڷ����໤�˵���ͬ���Ķ���Э�飬���ر�ע��δ������ʹ�����",
			"  һ����Э��ķ�Χ��\n1.1��Э���������巶Χ��\n ��Э�������롰ʩ�˶��Ρ�֮����������ء���װ��ʹ�á���¼��������Լ�ʹ�ñ�������������Э�顣\n"
					+ " 1.2��Э���ϵ����ͻ���\n  ��Э������ݣ������������������뱾���񡢱�Э����ص�Э�顢���򡢹淶�Լ���ʩ�˶��Ρ����ܲ��Ϸ����Ĺ��ڱ���������Э�顢���򡢹淶�����ݣ�ǰ������һ����ʽ��������Ϊ��Э�鲻�ɷָ����ɲ��֣����乹��ͳһ���壬��ͬ��Ӧ�����أ�\n"
					+ "��1������ʩ�˶��Ρ�����Э�顷��\n��2������ʩ�˶��Ρ��˺Ź��򡷡�\n  ��Э�����������ݴ��ڳ�ͻ�ģ��Ա�Э��Ϊ׼��",
			"  ���������ڱ�����\n"
					+ "  2.1�����������ݡ�\n"
					+ " ������������ָ��ʩ�˶��Ρ�ͨ����������û��ṩ����ط��񣨼�ơ������񡱣���\n"
					+ " 2.2����������ʽ��\n"
					+ " ������ͨ���ֻ��ն��Կͻ�����ʽʹ�ñ����񣬾����ԡ�ʩ�˶��Ρ��ṩ��Ϊ׼��ͬʱ����ʩ�˶��Ρ��᲻�Ϸḻ��ʹ�ñ�������նˡ���ʽ�ȡ�����ʹ�ñ�����ʱ����Ӧѡ���������նˡ�ϵͳ����ƥ��ı�����汾�������������޷�����ʹ�ñ�����"
					+ "2.3����ɵķ�Χ��\n"
					+ "2.3.1 ��ʩ�˶��Ρ�������һ����˵ġ�����ת�ü��������Ե���ɣ���ʹ�ñ������������Ϊ����ҵĿ���ڵ�һ̨�ն��豸�����ء���װ��ʹ�á���¼�������"
					+ "2.3.2 �����������������һ�����������������ݡ����ݸ����������ԭ����к��е���������Ȩ��Ϣ��\n"
					+ " 2.3.3 ��������Э����������δ��ʾ��Ȩ������һ��Ȩ�����ɡ�ʩ�˶��Ρ�������������ʹ��ЩȨ��ʱ������ȡ�á�ʩ�˶��Ρ���������ɡ���ʩ�˶��Ρ����δ��ʹǰ���κ�Ȩ�����������ɶԸ�Ȩ���ķ�����\n" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.agreement);
		// ��Layout�����ListView
		ListView list = (ListView) findViewById(R.id.agreetext);

		// ���ɶ�̬���飬��������
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < 3; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("agreetext", agree[i]);// ͼ����Դ��ID
			listItem.add(map);
		}
		// ������������Item�Ͷ�̬�����Ӧ��Ԫ��
		SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,// ����Դ
				R.layout.listfor_agreement,// ListItem��XMLʵ��
				// ��̬������ImageItem��Ӧ������
				new String[] { "agreetext" },
				// ImageItem��XML�ļ������һ��ImageView,����TextView ID
				new int[] { R.id.agreement_text1 });
		// ��Ӳ�����ʾ
		list.setAdapter(listItemAdapter);

	}
}