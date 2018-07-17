package com.example.admin.managerstundent.DTO;

public class ClassDTO {
    private String className;
    private String subject;
    private String time;
    private String weeksdays;

    public ClassDTO(String className, String subject, String time, String weeksdays) {
        this.className = className;
        this.subject = subject;
        this.time = time;
        this.weeksdays = weeksdays;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeeksdays() {
        return weeksdays;
    }

    public void setWeeksdays(String weeksdays) {
        this.weeksdays = weeksdays;
    }
}
