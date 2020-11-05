package com.example.testkladrapi.kladrAPI.streetJsonModel;

import com.google.gson.annotations.SerializedName;

public class SearchContext{

	@SerializedName("query")
	private String query;

	@SerializedName("cityId")
	private String cityId;

	@SerializedName("contentType")
	private String contentType;

	public void setQuery(String query){
		this.query = query;
	}

	public String getQuery(){
		return query;
	}

	public void setCityId(String cityId){
		this.cityId = cityId;
	}

	public String getCityId(){
		return cityId;
	}

	public void setContentType(String contentType){
		this.contentType = contentType;
	}

	public String getContentType(){
		return contentType;
	}
}