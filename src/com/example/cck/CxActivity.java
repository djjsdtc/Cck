package com.example.cck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class CxActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cx);
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
	
	public void btnLu_Click(View view){
		Intent intent=new Intent(CxActivity.this,TextActivity.class);
		intent.putExtra("Category", "Â³²Ë");
		startActivity(intent);
	}
	
	public void btnChuan_Click(View view){
		Intent intent=new Intent(CxActivity.this,TextActivity.class);
		intent.putExtra("Category", "´¨²Ë");
		startActivity(intent);
	}
	
	public void btnHu_Click(View view){
		Intent intent=new Intent(CxActivity.this,TextActivity.class);
		intent.putExtra("Category", "»¦²Ë");
		startActivity(intent);
	}
	
	public void btnYue_Click(View view){
		Intent intent=new Intent(CxActivity.this,TextActivity.class);
		intent.putExtra("Category", "ÔÁ²Ë");
		startActivity(intent);
	}
	
	public void btnOther_Click(View view){
		Intent intent=new Intent(CxActivity.this,TextActivity.class);
		intent.putExtra("Category", "ÏÖ´ú²ËÏµ");
		startActivity(intent);
	}
}
