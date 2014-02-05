/*Final Project
 Divisions.java
 Shashank G Hebbale(800773977)
 Sahana K Raj(800774871)
 */
package com.example.apg;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Divisions implements Serializable {

	String name,id;
	Double lat,lng;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Divisions(String name, String id, Double lat, Double lng) {
		super();
		this.name = name;
		this.id = id;
		this.lat = lat;
		this.lng = lng;
	}
	@Override
	public String toString() {
		return "Divisions [name=" + name + ", id=" + id + ", lat=" + lat
				+ ", lng=" + lng + "]";
	}
	/*
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int arg1) {
		// TODO Auto-generated method stub
		dest.writeString(id);
		dest.writeString(name);
		dest.writeDouble(lat);
		dest.writeDouble(lng);
	}
	
	public Divisions(Parcel in) {
		
		this.id = in.readString();
		this.name = in.readString();
		this.lat = in.readDouble();
		this.lng = in.readDouble();
		
	}

	  public static final Parcelable.Creator<Divisions> CREATOR = new Parcelable.Creator<Divisions>() {
	        public Divisions createFromParcel(Parcel in) {
	            return new Divisions(in);
	        }

	        public Divisions[] newArray(int size) {
	            return new Divisions[size];
	        }
	    };
*/
}
