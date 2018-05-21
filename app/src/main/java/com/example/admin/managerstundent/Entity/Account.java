package com.example.admin.managerstundent.Entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Account extends RealmObject {

    /**
     * Username of Account
     */
    @PrimaryKey
    private String username;

    /**
     * Password of Account
     */
    private String password;

    /**
     * 0: Admin
     * 1: Teacher
     * 2: Student
     */
    private int role;

    /**
     * Mapping with person (teacherID or studentID)(Null if this is admin account)
     */
    private int personID;

    /**
     * Getter username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter role
     * @return role
     */
    public int getRole() {
        return role;
    }

    /**
     * Getter personID
     * @return personID
     */
    public int getPersonID() {
        return personID;
    }

    /**
     * Setter username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Setter password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter role
     * @param role
     */
    public void setRole(int role) {
        this.role = role;
    }

    /**
     * Setter personID
     * @param personID
     */
    public void setPersonID(int personID) {
        this.personID = personID;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", personID=" + personID +
                '}';
    }
}
