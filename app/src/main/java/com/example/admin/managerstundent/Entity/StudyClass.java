package com.example.admin.managerstundent.Entity;

import android.support.annotation.Nullable;


import io.realm.annotations.PrimaryKey;

public class StudyClass {

    /**
     * ID of class
     */
    @PrimaryKey
    private int classID;

    /**
     * Name of class
     */
    private String className;

    /**
     * ID of course
     */
    private int course;

    /**
     * ID of teacher
     */
    private int teacherID;

    /**
     * ID of room
     */
    private int roomID;

    /**
     * ID of slot
     */
    private int slot;

    /**
     * ID of weekday
     */
    private int weekday;

    /**
     * Getter classID
     * @return classID
     */
    public int getClassID() {
        return classID;
    }

    /**
     * Getter className
     * @return className
     */
    public String getClassName() {
        return className;
    }


    /**
     * Getter course
     * @return course
     */
    public int getCourseID() {
        return course;
    }

    /**
     * Getter teacherID
     * @return teacherID
     */
    public int getTeacherID() {
        return teacherID;
    }

    /**
     * Getter roomID
     * @return roomID
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * Getter slot
     * @return slot
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Getter weekday
     * @return weekday
     */
    public int getWeekday() {
        return weekday;
    }

    /**
     * Setter classID
     * @param classID
     */
    public void setClassID(int classID) {
        this.classID = classID;
    }

    /**
     * Setter className
     * @param className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Setter course
     * @param course
     */
    public void setCourseID(int course) {
        this.course = course;
    }

    /**
     * Setter teacherID
     * @param teacherID
     */
    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    /**
     * Setter roomID
     * @param roomID
     */
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    /**
     * Setter slot
     * @param slot
     */
    public void setSlot(int slot) {
        this.slot = slot;
    }

    /**
     * Setter weekdayID
     * @param weekdayID
     */
    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }
}
