package com.example.testkladrapi.kladrAPI.buildJsonModel;

import com.google.gson.annotations.SerializedName;

public class SearchContext{

	@SerializedName("query")
	private String query;

	@SerializedName("limit")
	private int limit;

	@SerializedName("contentType")
	private String contentType;

	@SerializedName("streetId")
	private String streetId;

	public void setQuery(String query){
		this.query = query;
	}

	public String getQuery(){
		return query;
	}

	public void setLimit(int limit){
		this.limit = limit;
	}

	public int getLimit(){
		return limit;
	}

	public void setContentType(String contentType){
		this.contentType = contentType;
	}

	public String getContentType(){
		return contentType;
	}

	public void setStreetId(String streetId){
		this.streetId = streetId;
	}

	public String getStreetId(){
		return streetId;
	}
}