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
		//以上为系统自动代码，以下为读取XML、解析和添加项目的代码
		String listName=getIntent().getStringExtra("Category");
		//获取传入的Category参数，这个参数表示当前分类
		this.setTitle(listName);
		//标题栏显示当前分类
		XmlResourceParser xrp=getResources().getXml(R.xml.txtpicitems);
		//读取XML文档。以下为解析过程
		try {
			//首先定位到我要的节点
			while(xrp.getEventType()!=XmlResourceParser.END_DOCUMENT){				//读取到文档尾
				if(xrp.getEventType()==XmlResourceParser.START_TAG &&				//如果是开始标志（<Category>而非</Category>）
				   xrp.getName().equals("Category") &&								//如果节点是Category节点
				   xrp.getAttributeValue(0).equals(listName)){						//如果节点的Name属性（第一个）是我要的
					xrp.next();														//往下读进Item节点（当然在此我不能确定是不是Item节点）
					while(xrp.getName().equals("Item")){							//如果是Item节点
						if(xrp.getEventType()==XmlResourceParser.START_TAG){		//如果是<Item>而不是</Item>
							arrlstItems.add(xrp.getAttributeValue(0));				//把Name属性值加入名字的列表
							arrlstData.add(new TextData(xrp.getAttributeValue(1),xrp.getAttributeValue(2)));
							//把文本、图片路径存入数据结构的列表
						}
						xrp.next();		//继续读下面的节点
					}
					break;		//这样一轮结束的时候应该已经到了</Category>
				}//END_IF
				xrp.next();
			}//END_WHILE
		}//END_TRY
		catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ListView lstItemsTxt=(ListView)findViewById(R.id.lstItemsTxt);		//列表框控件lstItemsTxt
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrlstItems);
		//给名字列表压成一个ArrayAdapter，这样ListView才可以加载，并且还能实现搜索功能
		lstItemsTxt.setAdapter(adapter);		//绑定Adapter
		lstItemsTxt.setOnItemClickListener(new TextListListener(this));		//选择项目的时候的监听器
		lstItemsTxt.setChoiceMode(ListView.CHOICE_MODE_SINGLE);					//列表框是单选模式
		lstItemsTxt.performItemClick(lstItemsTxt.getAdapter().getView(0, null, null), 0, 0);
		//默认选择第一项
		EditText txtContext=(EditText)findViewById(R.id.txtContext);		//文本框控件txtContext
		txtContext.setCursorVisible(false);      //设置输入框中的光标不可见  
		txtContext.setFocusable(false);           //无焦点  
		txtContext.setFocusableInTouchMode(false);     //触摸时也得不到焦点
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
	    searchView.setIconifiedByDefault(true);			//搜索默认是一个按钮，而不是直接出搜索框
	    searchView.setOnQueryTextListener(this);		//添加搜索文字更改的监听器
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		MenuController.onOptionsItemSelected(this,item);
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onQueryTextChange(String prefix) {
		adapter.getFilter().filter(prefix);			//首字过滤
		return true;
	}

	@Override
	public boolean onQueryTextSubmit(String prefix) {
		return false;		//我们使用边输入边过滤，不需要在按搜索按钮时作出任何反应
	}
}