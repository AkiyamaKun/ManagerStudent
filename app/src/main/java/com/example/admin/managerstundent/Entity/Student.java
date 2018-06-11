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

    private String sex;

    /**
     * Name Parent of student
     */
    private String name_parent;

    /**
     * Phone of student
     */
    private String phone;

    private String phone_parent;

    private int tuitionFee;

    private boolean statusPayment;

    private Date dateStart;

    /**
     * Grade of student
     */
    private Integer grade;

    public Student() {}

    public Student(Integer studentID, String name, Date birthday, String sex) {
        this.studentID = studentID;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
    }

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
    public String getPhone() {
        return phone;
    }

    /**
     * Setter phone
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
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

    /**
     * Getter sex
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Setter sex
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Getter phone_parent
     * @return phone_parent
     */
    public String getPhone_parent() {
        return phone_parent;
    }

    /**
     * Setter phone_parent
     * @param phone_parent
     */
    public void setPhone_parent(String phone_parent) {
        this.phone_parent = phone_parent;
    }

    /**
     * Getter tuitionFee
     * @return tuitionFee
     */
    public int getTuitionFee() {
        return tuitionFee;
    }

    /**
     * Setter tuitionFee
     * @param tuitionFee
     */
    public void setTuitionFee(int tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    /**
     * Getter statusPayment
     * @return statusPayment
     */
    public boolean isStatusPayment() {
        return statusPayment;
    }

    /**
     * Setter statusPayment
     * @param statusPayment
     */
    public void setStatusPayment(boolean statusPayment) {
        this.statusPayment = statusPayment;
    }

    /**
     * Getter dateStart
     * @return dateStart
     */
    public Date getDateStart() {
        return dateStart;
    }

    /**
     * Setter dateStart
     * @param dateStart
     */
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }
}
