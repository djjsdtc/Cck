package com.example.cck;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

public class TextActivity extends Activity implements OnQueryTextListener {
	protected ArrayList<String> arrlstItems=new ArrayList<String>();
	public ArrayList<TextData> arrlstData=new ArrayList<TextData>();
	protected ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text);
		//����Ϊϵͳ�Զ����룬����Ϊ��ȡXML�������������Ŀ�Ĵ���
		String listName=getIntent().getStringExtra("Category");
		//��ȡ�����Category���������������ʾ��ǰ����
		this.setTitle(listName);
		//��������ʾ��ǰ����
		XmlResourceParser xrp=getResources().getXml(R.xml.txtpicitems);
		//��ȡXML�ĵ�������Ϊ��������
		try {
			//���ȶ�λ����Ҫ�Ľڵ�
			while(xrp.getEventType()!=XmlResourceParser.END_DOCUMENT){				//��ȡ���ĵ�β
				if(xrp.getEventType()==XmlResourceParser.START_TAG &&				//����ǿ�ʼ��־��<Category>����</Category>��
				   xrp.getName().equals("Category") &&								//����ڵ���Category�ڵ�
				   xrp.getAttributeValue(0).equals(listName)){						//����ڵ��Name���ԣ���һ��������Ҫ��
					xrp.next();														//���¶���Item�ڵ㣨��Ȼ�ڴ��Ҳ���ȷ���ǲ���Item�ڵ㣩
					while(xrp.getName().equals("Item")){							//�����Item�ڵ�
						if(xrp.getEventType()==XmlResourceParser.START_TAG){		//�����<Item>������</Item>
							arrlstItems.add(xrp.getAttributeValue(0));				//��Name����ֵ�������ֵ��б�
							arrlstData.add(new TextData(xrp.getAttributeValue(1),xrp.getAttributeValue(2)));
							//���ı���ͼƬ·���������ݽṹ���б�
						}
						xrp.next();		//����������Ľڵ�
					}
					break;		//����һ�ֽ�����ʱ��Ӧ���Ѿ�����</Category>
				}//END_IF
				xrp.next();
			}//END_WHILE
		}//END_TRY
		catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ListView lstItemsTxt=(ListView)findViewById(R.id.lstItemsTxt);		//�б��ؼ�lstItemsTxt
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrlstItems);
		//�������б�ѹ��һ��ArrayAdapter������ListView�ſ��Լ��أ����һ���ʵ����������
		lstItemsTxt.setAdapter(adapter);		//��Adapter
		lstItemsTxt.setOnItemClickListener(new TextListListener(this));		//ѡ����Ŀ��ʱ��ļ�����
		lstItemsTxt.setChoiceMode(ListView.CHOICE_MODE_SINGLE);					//�б���ǵ�ѡģʽ
		lstItemsTxt.performItemClick(lstItemsTxt.getAdapter().getView(0, null, null), 0, 0);
		//Ĭ��ѡ���һ��
		EditText txtContext=(EditText)findViewById(R.id.txtContext);		//�ı���ؼ�txtContext
		txtContext.setCursorVisible(false);      //����������еĹ�겻�ɼ�  
		txtContext.setFocusable(false);           //�޽���  
		txtContext.setFocusableInTouchMode(false);     //����ʱҲ�ò�������
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		// Get the SearchView and set the searchable configuration
	    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	    SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
	    // Assumes current activity is the searchable activity
	    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
	    searchView.setIconifiedByDefault(true);			//����Ĭ����һ����ť��������ֱ�ӳ�������
	    searchView.setOnQueryTextListener(this);		//����������ָ��ĵļ�����
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		MenuController.onOptionsItemSelected(this,item);
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onQueryTextChange(String prefix) {
		adapter.getFilter().filter(prefix);			//���ֹ���
		return true;
	}

	@Override
	public boolean onQueryTextSubmit(String prefix) {
		return false;		//����ʹ�ñ�����߹��ˣ�����Ҫ�ڰ�������ťʱ�����κη�Ӧ
	}
}