package com.example.admin.managerstundent.DTO;

public class StudentDTO {
    private Integer id;
    private String url;
    private String name;
    private Integer age;
    private String grade;
    private boolean paid;

    public StudentDTO(Integer id, String url, String name, Integer age, String grade, boolean paid) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.paid = paid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
