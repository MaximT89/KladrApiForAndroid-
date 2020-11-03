package com.example.testkladrapi.kladrAPI;

import java.util.List;

public class Response{
	private List<ResultItem> result;
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