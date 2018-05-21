package com.example.admin.managerstundent.Entity;

import io.realm.annotations.PrimaryKey;

public class StudyClass {

    @PrimaryKey
    private int classID;

    private String className;

    private int quanity;

    private int courseID;

    private int teacherID;

    private int roomID;

    private int slotID;

    private int weekdayID;

    public int getClassID() {
        return classID;
    }

    public String getClassName() {
        return className;
    }

    public int getQuanity() {
        return quanity;
    }

    public int getCourseID() {
        return courseID;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public int getRoomID() {
        return roomID;
    }

    public int getSlotID() {
        return slotID;
    }

    public int getWeekdayID() {
        return weekdayID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public void setWeekdayID(int weekdayID) {
        this.weekdayID = weekdayID;
    }
}
