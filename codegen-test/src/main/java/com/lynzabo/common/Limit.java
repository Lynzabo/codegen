package com.lynzabo.common;

public class Limit {

	private int start = 0;
	private int maxRows = -1;

	public Limit(int start) {
		this.start = start;
	}

	public Limit(int start, int maxRows) {
		this.start = start;
		this.maxRows = maxRows;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

}
