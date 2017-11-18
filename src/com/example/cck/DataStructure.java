package com.example.cck;

class TextData{
	//用一个数据结构记录文本文件文件名和图片文件文件名
	//不记录项目名字，因为按数组列表的pos取数据（下同）
	private String txtFilePath;	//文本文件路径
	private String picFilePath;	//图片文件路径
	
	public TextData(String strTxt,String strPic){
		txtFilePath=strTxt;picFilePath=strPic;
	}
	
	public String getTxtFilePath() {
		return ConstString.PATH+txtFilePath;
	}
	public String getPicFilePath() {
		return ConstString.PATH+picFilePath;
	}
}

class VideoData{
	//记录视频的文件名
	private String filePath;
	
	public VideoData(String str){
		filePath=str;
	}

	public String getFilePath() {
		return ConstString.PATH+filePath;
	}
}

interface ConstString{
	String PATH="/sdcard/cck/";		//数据文件的文件夹
}