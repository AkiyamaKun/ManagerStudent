package com.example.admin.managerstundent.Entity;

/**
 * Author: DangNHH
 * 27/05/2018
 *
 * This class Demo get data by API
 */
public class StudentDemo {
    private Integer id;
    private String name;
    private String studentNumber;
    private String parrentNumber;
    private Boolean feeCovered;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getParrentNumber() {
        return parrentNumber;
    }

    public Boolean getFeeCovered() {
        return feeCovered;
    }

    @Override
    public String toString() {
        return "StudentDemo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", parrentNumber='" + parrentNumber + '\'' +
                ", feeCovered=" + feeCovered +
                '}';
    }
}
