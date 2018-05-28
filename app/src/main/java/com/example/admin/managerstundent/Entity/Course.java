package com.example.admin.managerstundent.Entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Course extends RealmObject {
    /**
     * ID of course
     */
    @PrimaryKey
    private int courseID;

    /**
     * Math
     */
    private boolean math;

    /**
     * Physical
     */
    private boolean physical;

    /**
     * Chemistry
     */
    private boolean chemistry;

    /**
     * English
     */
    private boolean english;

    /**
     * Getter courseID
     * @return courseID
     */
    public int getCourseID() {
        return courseID;
    }

    /**
     * Getter math
     * @return math
     */
    public boolean isMath() {
        return math;
    }

    /**
     * Getter physical
     * @return physical
     */
    public boolean isPhysical() {
        return physical;
    }

    /**
     * Getter chemistry
     * @return chemistry
     */
    public boolean isChemistry() {
        return chemistry;
    }

    /**
     * Getter english
     * @return english
     */
    public boolean isEnglish() {
        return english;
    }

    /**
     * Setter courseID
     * @param courseID
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    /**
     * Setter math
     * @param math
     */
    public void setMath(boolean math) {
        this.math = math;
    }

    /**
     * Setter physical
     * @param physical
     */
    public void setPhysical(boolean physical) {
        this.physical = physical;
    }

    /**
     * Setter chemistry
     * @param chemistry
     */
    public void setChemistry(boolean chemistry) {
        this.chemistry = chemistry;
    }

    /**
     * Setter english
     * @param english
     */
    public void setEnglish(boolean english) {
        this.english = english;
    }
}
