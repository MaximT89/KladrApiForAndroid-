package com.example.testkladrapi.kladrAPI;

public class SearchContext{
	private int withParent;
	private String query;
	private int limit;
	private String contentType;

	public void setWithParent(int withParent){
		this.withParent = withParent;
	}

	public int getWithParent(){
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
