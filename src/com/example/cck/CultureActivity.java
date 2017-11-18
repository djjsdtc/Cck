package com.example.cck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class CultureActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_culture);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		MenuController.onOptionsItemSelected(this,item);
		return super.onOptionsItemSelected(item);
	}

	public void btnTradition_Click(View view){
		Intent intent=new Intent(CultureActivity.this,TextActivity.class);
		intent.putExtra("Category", "饮食风俗");
		startActivity(intent);
	}
	
	public void btnStory_Click(View view){
		Intent intent=new Intent(CultureActivity.this,TextActivity.class);
		intent.putExtra("Category", "饮食故事");
		startActivity(intent);
	}
	
	public void btnTaboo_Click(View view){
		Intent intent=new Intent(CultureActivity.this,TextActivity.class);
		intent.putExtra("Category", "饮食禁忌");
		startActivity(intent);
	}
	
	public void btnShopinrto_Click(View view){
		Intent intent=new Intent(CultureActivity.this,TextActivity.class);
		intent.putExtra("Category", "老字号");
		startActivity(intent);
	}
}
