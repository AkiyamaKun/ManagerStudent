package com.example.admin.managerstundent.Entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Course extends RealmObject {
    @PrimaryKey
    private int courseID;

    private boolean math;

    private boolean physical;

    private boolean chemistry;

    private boolean english;

    public int getCourseID() {
        return courseID;
    }

    public boolean isMath() {
        return math;
    }

    public boolean isPhysical() {
        return physical;
    }

    public boolean isChemistry() {
        return chemistry;
    }

    public boolean isEnglish() {
        return english;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setMath(boolean math) {
        this.math = math;
    }

    public void setPhysical(boolean physical) {
        this.physical = physical;
    }

    public void setChemistry(boolean chemistry) {
        this.chemistry = chemistry;
    }

    public void setEnglish(boolean english) {
        this.english = english;
    }
}
