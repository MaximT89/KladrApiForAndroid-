package com.example.testkladrapi.kladrAPI.streetJsonModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("result")
	private List<ResultItem> result;

	@SerializedName("searchContext")
	private SearchContext searchContext;

	public void setResult(List<ResultItem> result){
		this.result = result;
	}

	public List<ResultItem> getResult(){
		return result;
	}

	public void setSearchContext(SearchContext searchContext){
		this.searchContext = searchContext;
	}

	public SearchContext getSearchContext(){
		return searchContext;
	}
}