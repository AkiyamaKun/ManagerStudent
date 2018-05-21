package com.example.admin.managerstundent.Entity;

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
     * Quantity of maximum student in class
     */
    private int quanity;

    /**
     * ID of course
     */
    private int courseID;

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
    private int slotID;

    /**
     * ID of weekday
     */
    private int weekdayID;

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
     * Getter quanity
     * @return quanity
     */
    public int getQuanity() {
        return quanity;
    }

    /**
     * Getter courseID
     * @return courseID
     */
    public int getCourseID() {
        return courseID;
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
     * Getter slotID
     * @return slotID
     */
    public int getSlotID() {
        return slotID;
    }

    /**
     * Getter weekdayID
     * @return weekdayID
     */
    public int getWeekdayID() {
        return weekdayID;
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
     * Setter quanity
     * @param quanity
     */
    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    /**
     * Setter courseID
     * @param courseID
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
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
     * Setter slotID
     * @param slotID
     */
    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    /**
     * Setter weekdayID
     * @param weekdayID
     */
    public void setWeekdayID(int weekdayID) {
        this.weekdayID = weekdayID;
    }
}
