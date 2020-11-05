package com.example.testkladrapi.kladrAPI.cityJsonModel;

import com.google.gson.annotations.SerializedName;

public class SearchContext{

	@SerializedName("withParent")
	private String withParent;

	@SerializedName("query")
	private String query;

	@SerializedName("limit")
	private int limit;

	@SerializedName("contentType")
	private String contentType;

	public void setWithParent(String withParent){
		this.withParent = withParent;
	}

	public String getWithParent(){
		return withParent;
	}

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
}