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
		/* ѡ����Ŀʱ����3���£�
		 * 1��ȡ��������Ŀ��ѡ��״̬��������ɫȡ����
		 * 2��ѡ����Ŀ�ı�����ɫ��Ϊ����ɫ
		 * 3����ȡѡ����Ŀ���ı���ͼƬ�����ֳ���
		 */
		try {
			if (((ListView)parent).getTag() != null){
				 ((View)((ListView)parent).getTag()).setBackground(null);
				 //ȡ��������Ŀ�ı�����ɫ
			}
			((ListView)parent).setTag(view);		//ѡ�е�ǰѡ����
			view.setBackgroundColor(Color.LTGRAY);	//�Ѹ���ı���ɫ��Ϊ����ɫ��Light Gray��
			EditText txtContext = (EditText) activity.findViewById(R.id.txtContext);
			ImageView imgPic = (ImageView) activity.findViewById(R.id.imgPic);
			String name=activity.adapter.getItem(pos);		
			//��ȡ��ѡ���������
			//����ֱ���ô����pos��������Ϊ��������Ļ���ListView�е�pos���ʵ�ʵ�pos����Ӧ
			int actualPos=activity.arrlstItems.indexOf(name);
			//��ȡ���ֶ�Ӧ��ʵ�������ڵ�λ��
			//��������Java��IO�����ı��ļ����Ͳ�дע����
			FileInputStream fis = new FileInputStream(new File(activity.arrlstData.get(actualPos).getTxtFilePath()));
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			StringBuffer context = new StringBuffer("");
			String context_line;
			while ((context_line = br.readLine()) != null)
				context.append(context_line
						+ System.getProperty("line.separator"));
			txtContext.setText(context.toString());
			Bitmap bm = BitmapFactory.decodeFile(activity.arrlstData.get(actualPos).getPicFilePath());
			//����Android��ͼƬ�����õķ���
			imgPic.setImageBitmap(bm);
		} catch (Exception e) {
			//�����Ի�����ʾ��ȡ��Դ�ļ���ʱ������쳣
			AlertDialog dialog = new AlertDialog.Builder(activity).create();
			String title, message;
			if (e instanceof FileNotFoundException) {
				title = "�Ҳ�����Դ�ļ�";
				message = "������SD�������ֻ��洢����û���ҵ��������������Դ��";
			} else {
				title = "��ȡ��Դ�ļ�ʱ��������";
				message = "��ȡ��Դ�ļ�ʱ�������󣬿�����Դ�ļ����𻵡�";
			}
			dialog.setTitle(title);
			dialog.setMessage(message
					+ "����ʱ������վ������Դ�ļ���Ȼ���ѹ��SD����Ŀ¼�£��ٴ����б������\n"
					+ "�����վ������ѡ��˵��Ĺ��������ҵ���");
			dialog.setButton(DialogInterface.BUTTON_POSITIVE, "ȷ��",
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
		//��һ�����ȿ�����MediaController����ʵ�ֽ��ȿ���
		vdvVideo.start();		//��ʼ����
	}
}
