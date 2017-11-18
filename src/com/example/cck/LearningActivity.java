package com.example.cck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class LearningActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_learning);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void btn2_Click(View view){
		Intent intent=new Intent(LearningActivity.this,VideoActivity.class);
		intent.putExtra("Category", "µ∂π§¡∑œ∞");
		startActivity(intent);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		MenuController.onOptionsItemSelected(this,item);
		return super.onOptionsItemSelected(item);
	}
	
	public void btn3_Click(View view){
		Intent intent=new Intent(LearningActivity.this,TextActivity.class);
		intent.putExtra("Category", "∫£œ —°‘Ò");
		startActivity(intent);
	}
	
	public void btn4_Click(View view){
		Intent intent=new Intent(LearningActivity.this,VideoActivity.class);
		intent.putExtra("Category", "±ﬂø¥±ﬂ—ß");
		startActivity(intent);
	}
	
	public void btn5_Click(View view){
		Intent intent=new Intent(LearningActivity.this,TextActivity.class);
		intent.putExtra("Category", "≥¥≤À«œ√≈");
		startActivity(intent);
	}
}
