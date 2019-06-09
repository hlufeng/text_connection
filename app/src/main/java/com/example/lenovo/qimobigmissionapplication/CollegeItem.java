package com.example.lenovo.qimobigmissionapplication;

public class CollegeItem {
    private int id;
    private String title;
    private String date;
    private String text;
    private String addition;
    private String url;
    private String additionhref;

    public CollegeItem() {
        super();
        title = "";
        date = "";
        text = "";
        addition = "";
        url = "";
        additionhref="";
    }
    public CollegeItem(String title, String date,String text,String addition,String url,String additionhref) {
        super();
        this.title = title;
        this.date = date;
        this.text = text;
        this.addition = addition;
        this.url = url;
        this.additionhref=additionhref;
    }

    public String getAdditionhref() {
        return additionhref;
    }

    public void setAdditionhref(String additionhref) {
        this.additionhref = additionhref;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

