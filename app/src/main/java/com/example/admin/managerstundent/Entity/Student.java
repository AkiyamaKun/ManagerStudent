package com.example.admin.managerstundent.Entity;

import java.util.Date;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Author: DangNHH
 * 19/05/2018
 *
 * Student Entity Class
 */
public class Student extends RealmObject {

    /**
     * ID of student
     */
    @PrimaryKey
    private Integer studentID;

    /**
     * Name of student
     */
    private String name;

    /**
     * Birthday of student
     */
    private Date birthday;

    /**
     * Name Parent of student
     */
    private String name_parent;

    /**
     * Phone of parent of student
     */
    private Integer phone;

    /**
     * Yes/no learning subject Math
     */
    private Boolean math;

    /**
     * Yes/no learning subject Physical
     */
    private Boolean physical;

    /**
     * Yes/no learning subject Chemistry
     */
    private Boolean chemistry;

    /**
     * Grade of student
     */
    private Integer grade;

    /**
     * Getter name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter birthday
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Setter birthday
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Getter name_parent
     * @return name_parent
     */
    public String getName_parent() {
        return name_parent;
    }

    /**
     * Setter name_parent
     * @param name_parent
     */
    public void setName_parent(String name_parent) {
        this.name_parent = name_parent;
    }

    /**
     * Getter phone
     * @return phone
     */
    public Integer getPhone() {
        return phone;
    }

    /**
     * Setter phone
     * @param phone
     */
    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    /**
     * Getter math
     * @return math
     */
    public Boolean getMath() {
        return math;
    }

    /**
     * Setter math
     * @param math
     */
    public void setMath(Boolean math) {
        this.math = math;
    }

    /**
     * Getter physical
     * @return physical
     */
    public Boolean getPhysical() {
        return physical;
    }

    /**
     * Setter physical
     * @param physical
     */
    public void setPhysical(Boolean physical) {
        this.physical = physical;
    }

    /**
     * Getter chemistry
     * @return chemistry
     */
    public Boolean getChemistry() {
        return chemistry;
    }

    /**
     * Setter chemistry
     * @param chemistry
     */
    public void setChemistry(Boolean chemistry) {
        this.chemistry = chemistry;
    }

    /**
     * Getter studentID
     * @return studentID
     */
    public Integer getStudentID() {
        return studentID;
    }

    /**
     * Setter studentID
     * @param studentID
     */
    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    /**
     * Getter grade
     * @return grade
     */
    public Integer getGrade() {
        return grade;
    }
    /**
     * Setter grade
     * @param grade
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", name_parent='" + name_parent + '\'' +
                ", phone=" + phone +
                ", math=" + math +
                ", physical=" + physical +
                ", chemistry=" + chemistry +
                ", grade=" + grade +
                '}';
    }
}
