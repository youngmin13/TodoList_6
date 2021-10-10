package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
    private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;
	private int id;
	private int is_completed;
	


    @Override
	public String toString() {
    	if(getIs_completed() == 1)
    		return id + " [" + category + "] " +  title + "[V]" + " - " + desc + " - " + current_date + " - " + due_date;
    	else
    		return id + " [" + category + "] " +  title + " - " + desc + " - " + current_date + " - " + due_date;
	}

	public TodoItem(String title, String desc, String category, String due_date){
    	this.category = category;
    	this.title=title;
        this.desc=desc;
        this.due_date = due_date;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date = f.format(new Date());
    }
    
    public TodoItem(String title, String desc, String category, String due_date, String current_date){
    	this.category = category;
    	this.title=title;
        this.desc=desc;
        this.due_date = due_date;
        this.current_date = current_date;
    }
    
    public TodoItem(String title, String desc, String category, String due_date, int is_completed){
    	this.category = category;
    	this.title=title;
        this.desc=desc;
        this.due_date = due_date;
        //this.current_date = current_date;
        this.is_completed = is_completed;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public String toSaveString()
    {
    	return title + "##" + category + "##" + desc + "##" + due_date + "##" + current_date + "\n";
    }

	public String getCategory() {
		return category;
	}
	

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public int getIs_completed() {
		return is_completed;
	}

	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}
}
