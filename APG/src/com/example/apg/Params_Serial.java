/*Final Project
 Params_Serial.java
 Shashank G Hebbale(800773977)
 Sahana K Raj(800774871)
 */

package com.example.apg;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Params_Serial implements Serializable{

	String Imageurl, title, endtime, dealurl;
	String original_price, discount_price;
	int remaining_quan, id;
	String dis_percent;
	public String getImageurl() {
		return Imageurl;
	}
	public void setImageurl(String imageurl) {
		Imageurl = imageurl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getDealurl() {
		return dealurl;
	}
	public void setDealurl(String dealurl) {
		this.dealurl = dealurl;
	}
	public String getOriginal_price() {
		return original_price;
	}
	public void setOriginal_price(String original_price) {
		this.original_price = original_price;
	}
	public String getDiscount_price() {
		return discount_price;
	}
	public void setDiscount_price(String discount_price) {
		this.discount_price = discount_price;
	}
	public int getRemaining_quan() {
		return remaining_quan;
	}
	public void setRemaining_quan(int remaining_quan) {
		this.remaining_quan = remaining_quan;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDis_percent() {
		return dis_percent;
	}
	public void setDis_percent(String dis_percent) {
		this.dis_percent = dis_percent;
	}
	@Override
	public String toString() {
		return "Params_Serial [Imageurl=" + Imageurl + ", title=" + title
				+ ", endtime=" + endtime + ", dealurl=" + dealurl
				+ ", original_price=" + original_price + ", discount_price="
				+ discount_price + ", remaining_quan=" + remaining_quan
				+ ", id=" + id + ", dis_percent=" + dis_percent + "]";
	}
	public Params_Serial(String imageurl, String title, String endtime,
			String dealurl, String original_price, String discount_price,
			int remaining_quan, int id, String dis_percent) {
		super();
		Imageurl = imageurl;
		this.title = title;
		this.endtime = endtime;
		this.dealurl = dealurl;
		this.original_price = original_price;
		this.discount_price = discount_price;
		this.remaining_quan = remaining_quan;
		this.id = id;
		this.dis_percent = dis_percent;
	}
	
	
	
}
