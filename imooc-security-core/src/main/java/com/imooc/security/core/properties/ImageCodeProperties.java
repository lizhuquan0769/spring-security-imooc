package com.imooc.security.core.properties;

public class ImageCodeProperties {
	private int width = 80;
	private int height = 45;
	private int length = 4;
	private int expireIn = 300;
	private String url = "*";
	private int fontSize = 40;
	
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
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getExpireIn() {
		return expireIn;
	}
	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
}
