package com.lynzabo.common;

import java.util.Map;

public class QueryParam<K, V> {
	
	private Limit limit;
	private Page page;
	
	private Map<K, V> params;
	
	
	public QueryParam(){};
	
	public QueryParam(Map<K, V> params, Page page){
		this.page = page;
		this.params = params;
	};
	
	public Map<K, V> getParams() {
		return params;
	}

	public void setParams(Map<K, V> params) {
		this.params = params;
	}

	public Limit getLimit() {
		return limit;
	}

	public void setLimit(Limit limit) {
		this.limit = limit;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
