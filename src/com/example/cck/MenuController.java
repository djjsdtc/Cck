package com.example.cck;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.MenuItem;

public class MenuController {
	public static void onOptionsItemSelected(Activity a,MenuItem item){
		if(item.getTitle().equals("版本信息")){
			//如果选择了“版本信息”菜单项，就弹出关于对话框
			AlertDialog dialog = new AlertDialog.Builder(a).create();
			dialog.setTitle("关于");
			dialog.setMessage("版权所有(C) 2013。保留所有权利。\n\n" +
					"本程序受国际版权法的保护。\n\n" +
					"网址：http://358930328.qzone.qq.com");
			dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			dialog.show();
		}
	}
}
