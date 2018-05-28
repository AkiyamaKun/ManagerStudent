package com.example.admin.managerstundent.Entity;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Teacher extends RealmObject {

    /**
     * ID of teacher
     */
    @PrimaryKey
    private int teacherID;

    /**
     * Name of teacher
     */
    private String name;

    /**
     * Birth day of teacher (dd/MM/yyyy)
     */
    private Date birthDate;

    /**
     * Sex of teacher
     */
    private String sex;

    /**
     *  Address of teacher
     */
    private String address;

    /**
     * Specialize of teacher
     */
    private String specialize;

    /**
     * Salary of teacher
     */
    private int salary;

    /**
     * Getter id
     * @return id
     */
    public int getTeacherID() {
        return teacherID;
    }

    /**
     * Getter name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter birthDate
     * @return birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Getter sex
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Getter address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter specialize
     * @return specialize
     */
    public String getSpecialize() {
        return specialize;
    }

    /**
     * Getter salary
     * @return salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Setter teacherID
     * @param teacherID
     */
    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    /**
     * Setter name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter birthDate
     * @param birthDate
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Setter sex
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Setter address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Setter specialize
     * @param specialize
     */
    public void setSpecialize(String specialize) {
        this.specialize = specialize;
    }

    /**
     * Setter salary
     * @param salary
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherID=" + teacherID +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", specialize='" + specialize + '\'' +
                ", salary=" + salary +
                '}';
    }
}
