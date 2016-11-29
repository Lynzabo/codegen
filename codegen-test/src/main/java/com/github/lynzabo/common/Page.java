package com.github.lynzabo.common;

import java.io.Serializable;

public class Page {
    public static final int RECORDS_PER_PAGE = 15;
    /**
     * @serial
     */
    private static final long serialVersionUID = -9153260940661933224L;

    private int currentPage = 1;

    private int recordsPerPage = RECORDS_PER_PAGE;

    private int totalRecords = 0;

    private boolean tooManySearchReturn = false;
    
    private long preId = -1;
    private long nextId = -1;
    private int currentRecord = 0;
    
    
    private Object data = new Serializable(){

		private static final long serialVersionUID = 4324950161762124888L;
    	
    };
    
    public Page(){
    	
    }
    public Page(int currentPage, int recordsPerPage) {
    	this.currentPage = currentPage;
    	this.recordsPerPage = recordsPerPage;
    }

	public int getStartRowPosition() {
    	if(recordsPerPage >= 0)
            return recordsPerPage * (getCurrentPage() - 1);
    	else
    		return 0;
    }

    public int getEndRowPosition() {
        int max = recordsPerPage * getCurrentPage();
        return max;
    }

    public boolean isLastPage() {
        return (getTotalPages() == 0 || currentPage == getTotalPages());
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalRecords() {
        return this.totalRecords;
    }

    public boolean isTooManySearchReturn() {
        return this.tooManySearchReturn;
    }

    public void setTooManySearchReturn(boolean tooManySearchReturn) {
        this.tooManySearchReturn = tooManySearchReturn;
    }

    public void setRecordsPerPage(int records) {
        if (records > 0 || records < 0)
            this.recordsPerPage = records;
    }

    public int getRecordsPerPage() {
        if(recordsPerPage == 0) {
            recordsPerPage = RECORDS_PER_PAGE;
        }
        return recordsPerPage;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage > 0)
            this.currentPage = currentPage;
        else
            currentPage = 1;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
    	if(recordsPerPage < 0)
    		return 1;
    	else
            return (totalRecords + recordsPerPage - 1) / recordsPerPage;
    }
    
    public long getPreId(){
    	return preId;
    }
    
    public void setPreId(long preId){
    	this.preId = preId;
    }
    
    public long getNextId(){
    	return this.nextId;
    }
    
    public void setNextId(long nextId){
    	this.nextId = nextId;
    }
    
    public int getCurrentRecord(){
    	return this.currentRecord;
    }
    
    public void setCurrentRecord(int currentRecord){
    	this.currentRecord = currentRecord;
    }
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}