package com.example.lenovo.qimobigmissionapplication;

public class ClassItem {
    private String id;
    private int week;
    private int index;
    private int step;
    private String name;
    private String location;
    private String teacher;

    public ClassItem() {
        super();
        id="";
        week = 0;
        index = 0;
        step = 0;
        name = "";
        location = "";
        teacher="";
    }
    public ClassItem(String id,int week, int index,int step,String name,String location,String teacher) {
        super();
        this.id=id;
        this.week = week;
        this.index = index;
        this.step = step;
        this.name = name;
        this.location = location;
        this.teacher=teacher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
