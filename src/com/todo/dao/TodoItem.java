package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
    private String title;
    private String desc;
    private Date current_date=new Date();
    SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String Sdate = SDF.format(current_date);


    public TodoItem(String title, String desc){
        this.title=title;
        this.desc=desc;
        this.Sdate=Sdate;
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

	public String getSdate() {
		return Sdate;
	}

	public void setSdate(String sdate) {
		Sdate = sdate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + title + "] " + desc + " - " + Sdate;
	}
    
	public String toSaveString() {
		// TODO Auto-generated method stub
		return title + "##" + desc + "##" + Sdate + "\n";
		}
    
}
