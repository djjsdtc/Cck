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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SearchView;
import android.widget.VideoView;
import android.widget.SearchView.OnQueryTextListener;

public class VideoActivity extends Activity implements OnQueryTextListener {
	//同TextActivity类的代码，故不详细注释了
	protected ArrayList<String> arrlstItems=new ArrayList<String>();
	public ArrayList<VideoData> arrlstData=new ArrayList<VideoData>();
	protected ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		String listName=getIntent().getStringExtra("Category");
		this.setTitle(listName);
		XmlResourceParser xrp=getResources().getXml(R.xml.vdvitems);
		try {
			while(xrp.getEventType()!=XmlResourceParser.END_DOCUMENT){
				if(xrp.getEventType()==XmlResourceParser.START_TAG &&
				   xrp.getName().equals("Category") &&
				   xrp.getAttributeValue(0).equals(listName)){
					xrp.next();
					while(xrp.getName().equals("Item")){
						if(xrp.getEventType()==XmlResourceParser.START_TAG){
							arrlstItems.add(xrp.getAttributeValue(0));
							arrlstData.add(new VideoData(xrp.getAttributeValue(1)));
						}
						xrp.next();
					}
					break;
				}//END_IF
				xrp.next();
			}//END_WHILE
		}//END_DOCUMENT
		catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ListView lstItemsVdv=(ListView)findViewById(R.id.lstItemsVdv);
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrlstItems);
		lstItemsVdv.setAdapter(adapter);
		lstItemsVdv.setOnItemClickListener(new VideoListListener(this));
		lstItemsVdv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		lstItemsVdv.performItemClick(lstItemsVdv.getAdapter().getView(0, null, null), 0, 0);
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
	    searchView.setIconifiedByDefault(true);
	    searchView.setOnQueryTextListener(this);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		MenuController.onOptionsItemSelected(this,item);
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onQueryTextChange(String prefix) {
		adapter.getFilter().filter(prefix);
		return true;
	}

	@Override
	public boolean onQueryTextSubmit(String prefix) {
		return false;
	}
}
