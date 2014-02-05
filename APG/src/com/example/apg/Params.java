/*Final Project
 Params.java
 Shashank G Hebbale(800773977)
 Sahana K Raj(800774871)
 */
package com.example.apg;


import android.os.Parcel;
import android.os.Parcelable;


public class Params implements Parcelable {

	String Imageurl, title, starttime, endtime, dealurl;
	Double lat,lng;
	String original_price, discount_price;
	int remaining_quan, id;
	String dis_percent,buy_url,status,redemption_loc;
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
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
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
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
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
	public String getBuy_url() {
		return buy_url;
	}
	public void setBuy_url(String buy_url) {
		this.buy_url = buy_url;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRedemption_loc() {
		return redemption_loc;
	}
	public void setRedemption_loc(String redemption_loc) {
		this.redemption_loc = redemption_loc;
	}
	@Override
	public String toString() {
		return "Params [Imageurl=" + Imageurl + ", title=" + title
				+ ", starttime=" + starttime + ", endtime=" + endtime
				+ ", dealurl=" + dealurl + ", lat=" + lat + ", lng=" + lng
				+ ", original_price=" + original_price + ", discount_price="
				+ discount_price + ", remaining_quan=" + remaining_quan
				+ ", id=" + id + ", dis_percent=" + dis_percent + ", buy_url="
				+ buy_url + ", status=" + status + ", redemption_loc="
				+ redemption_loc + "]";
	}
	
	public Params(String imageurl, String title, String starttime,
			String endtime, String dealurl, Double lat, Double lng,
			String original_price, String discount_price, int remaining_quan,
			int id, String dis_percent, String buy_url, String status,
			String redemption_loc) {
		super();
		Imageurl = imageurl;
		this.title = title;
		this.starttime = starttime;
		this.endtime = endtime;
		this.dealurl = dealurl;
		this.lat = lat;
		this.lng = lng;
		this.original_price = original_price;
		this.discount_price = discount_price;
		this.remaining_quan = remaining_quan;
		this.id = id;
		this.dis_percent = dis_percent;
		this.buy_url = buy_url;
		this.status = status;
		this.redemption_loc = redemption_loc;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(Imageurl);
		dest.writeString(title);
		dest.writeString(starttime);
		dest.writeString(endtime);
		dest.writeString(dealurl);
		dest.writeString(endtime);
		dest.writeDouble(lat);
		dest.writeDouble(lng);
		dest.writeString(original_price);
		dest.writeString(discount_price);
		dest.writeInt(remaining_quan);
		dest.writeInt(id);
		dest.writeString(dis_percent);
		dest.writeString(buy_url);
		dest.writeString(status);
		dest.writeString(redemption_loc);
	

	}
	
	public Params(Parcel in) {
		// TODO Auto-generated constructor stub
		Imageurl = in.readString();
		this.title = in.readString();
		this.starttime = in.readString();
		this.endtime = in.readString();
		this.dealurl = in.readString();
		this.lat = in.readDouble();
		this.lng = in.readDouble();
		this.original_price = in.readString();
		this.discount_price = in.readString();
		this.remaining_quan = in.readInt();
		this.id = in.readInt();
		this.dis_percent = in.readString();
		this.buy_url = in.readString();
		this.status = in.readString();
		this.redemption_loc = in.readString();
	}
	
    public static final Parcelable.Creator<Params> CREATOR = new Parcelable.Creator<Params>() {
        public Params createFromParcel(Parcel in) {
            return new Params(in);
        }

        public Params[] newArray(int size) {
            return new Params[size];
        }
    };
	
	
	

}
