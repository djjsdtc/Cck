package com.example.cck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

class TextListListener implements OnItemClickListener {
	TextActivity activity;

	public TextListListener(TextActivity a) {
		activity = a;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		/* 选中项目时必做3件事：
		 * 1、取消其他项目的选中状态（背景颜色取消）
		 * 2、选中项目的背景颜色改为亮灰色
		 * 3、读取选中项目的文本和图片并呈现出来
		 */
		try {
			if (((ListView)parent).getTag() != null){
				 ((View)((ListView)parent).getTag()).setBackground(null);
				 //取消其他项目的背景颜色
			}
			((ListView)parent).setTag(view);		//选中当前选择项
			view.setBackgroundColor(Color.LTGRAY);	//把该项的背景色变为亮灰色（Light Gray）
			EditText txtContext = (EditText) activity.findViewById(R.id.txtContext);
			ImageView imgPic = (ImageView) activity.findViewById(R.id.imgPic);
			String name=activity.adapter.getItem(pos);		
			//获取被选中项的名字
			//不能直接用传入的pos参数，因为如果搜索的话，ListView中的pos会和实际的pos不对应
			int actualPos=activity.arrlstItems.indexOf(name);
			//获取名字对应的实际上所在的位置
			//下面是用Java的IO来读文本文件，就不写注释了
			FileInputStream fis = new FileInputStream(new File(activity.arrlstData.get(actualPos).getTxtFilePath()));
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			StringBuffer context = new StringBuffer("");
			String context_line;
			while ((context_line = br.readLine()) != null)
				context.append(context_line
						+ System.getProperty("line.separator"));
			txtContext.setText(context.toString());
			Bitmap bm = BitmapFactory.decodeFile(activity.arrlstData.get(actualPos).getPicFilePath());
			//这是Android读图片所调用的方法
			imgPic.setImageBitmap(bm);
		} catch (Exception e) {
			//弹出对话框提示读取资源文件的时候出现异常
			AlertDialog dialog = new AlertDialog.Builder(activity).create();
			String title, message;
			if (e instanceof FileNotFoundException) {
				title = "找不到资源文件";
				message = "在您的SD卡（或手机存储）中没有找到本软件的数据资源。";
			} else {
				title = "读取资源文件时发生错误";
				message = "读取资源文件时发生错误，可能资源文件已损坏。";
			}
			dialog.setTitle(title);
			dialog.setMessage(message
					+ "请访问本软件网站下载资源文件，然后解压到SD卡根目录下，再次运行本软件。\n"
					+ "软件网站可以在选项菜单的关于项中找到。");
			dialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							activity.finish();
						}
					});
			dialog.show();
		}
	}
}

class VideoListListener implements OnItemClickListener {
	VideoActivity activity;

	public VideoListListener(VideoActivity a) {
		activity = a;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		if (((ListView)parent).getTag() != null){
			 ((View)((ListView)parent).getTag()).setBackground(null);
		}
		((ListView)parent).setTag(view);
		view.setBackgroundColor(Color.LTGRAY);
		VideoView vdvVideo = (VideoView) activity.findViewById(R.id.vdvVideo);
		String name=activity.adapter.getItem(pos);
		int actualPos=activity.arrlstItems.indexOf(name);
		vdvVideo.setVideoPath(activity.arrlstData.get(actualPos).getFilePath());
		MediaController mc = new MediaController(activity);
		vdvVideo.setMediaController(mc);
		//绑定一个进度控制器MediaController，来实现进度控制
		vdvVideo.start();		//开始播放
	}
}
