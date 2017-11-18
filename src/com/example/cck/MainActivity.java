package com.example.cck;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void btnLearning_Click(View view){
		Intent intent=new Intent(MainActivity.this,LearningActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		MenuController.onOptionsItemSelected(this,item);
		//�����Ĳ˵��¼�����������Ϊ���н��湲��ͬһ���˵���
		return super.onOptionsItemSelected(item);
	}
	
	public void btnConcept_Click(View view){
		Intent intent=new Intent(MainActivity.this,TextActivity.class);
		//����һ����MainActivity��TextActivity�л���Intent�������ط�ͬ��
		intent.putExtra("Category", "��ʳ����");
		//�л�ʱ���ݲ�����Category=��ʳ���
		startActivity(intent);
		//���µ�Activity
	}
	
	public void btnFood_Click(View view){
		Intent intent=new Intent(MainActivity.this,CxActivity.class);
		startActivity(intent);
	}
	
	public void btnCulture_Click(View view){
		Intent intent=new Intent(MainActivity.this,CultureActivity.class);
		startActivity(intent);
	}
}
