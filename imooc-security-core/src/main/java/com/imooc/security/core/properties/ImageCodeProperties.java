package com.imooc.security.core.properties;

public class ImageCodeProperties extends SmsCodeProperties {
	private int width = 80;
	private int height = 45;
	private int fontSize = 40;
	
	public ImageCodeProperties() {
		setLength(4);
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
}
