package com.example.admin.managerstundent.Entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Learning extends RealmObject {

    /**
     * ID of Learning
     */
    @PrimaryKey
    private int learningID;

    /**
     * ID of student
     */
    private int studentID;

    /**
     * ID of class
     */
    private int classID;


    public int getLearningID() {
        return learningID;
    }

    /**
     * Getter studentID
     * @return studentID
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * Getter classID
     * @return classID
     */
    public int getClassID() {
        return classID;
    }

    /**
     * Setter learningID
     * @param learningID
     */
    public void setLearningID(int learningID) {
        this.learningID = learningID;
    }

    /**
     * Setter studentID
     * @param studentID
     */
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    /**
     * Setter classID
     * @param classID
     */
    public void setClassID(int classID) {
        this.classID = classID;
    }

}
