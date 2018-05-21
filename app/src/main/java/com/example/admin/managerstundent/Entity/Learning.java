package com.example.admin.managerstundent.Entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Learning extends RealmObject {

    @PrimaryKey
    private int learningID;

    private int studentID;

    private int classID;

    private int slotID;

    private int weekdayID;

    public int getLearningID() {
        return learningID;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getClassID() {
        return classID;
    }

    public int getSlotID() {
        return slotID;
    }

    public int getWeekdayID() {
        return weekdayID;
    }

    public void setLearningID(int learningID) {
        this.learningID = learningID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public void setWeekdayID(int weekdayID) {
        this.weekdayID = weekdayID;
    }
}
