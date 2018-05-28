package com.example.admin.managerstundent.Entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Weekday extends RealmObject {
    /**
     * ID of weekday
     */
    @PrimaryKey
    private int weekdayID;

    /**
     * monday
     */
    private boolean monday;

    /**
     * tuesday
     */
    private boolean tuesday;

    /**
     * wednesday
     */
    private boolean wednesday;

    /**
     * thursday
     */
    private boolean thursday;

    /**
     * friday
     */
    private boolean friday;

    /**
     * saturday
     */
    private boolean saturday;

    /**
     * sunday
     */
    private boolean sunday;

    /**
     * Getter weekdayID
     * @return weekdayID
     */
    public int getWeekdayID() {
        return weekdayID;
    }

    /**
     * Getter monday
     * @return monday
     */
    public boolean isMonday() {
        return monday;
    }

    /**
     * Getter tuesday
     * @return tuesday
     */
    public boolean isTuesday() {
        return tuesday;
    }

    /**
     * Getter wednesday
     * @return wednesday
     */
    public boolean isWednesday() {
        return wednesday;
    }

    /**
     * Getter thursday
     * @return thursday
     */
    public boolean isThursday() {
        return thursday;
    }

    /**
     * Getter friday
     * @return friday
     */
    public boolean isFriday() {
        return friday;
    }

    /**
     * Getter username
     * @return username
     */
    public boolean isSaturday() {
        return saturday;
    }

    /**
     * Getter username
     * @return username
     */
    public boolean isSunday() {
        return sunday;
    }

    /**
     * Setter role
     * @param role
     */
    public void setWeekdayID(int weekdayID) {
        this.weekdayID = weekdayID;
    }

    /**
     * Setter role
     * @param role
     */
    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    /**
     * Setter role
     * @param role
     */
    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    /**
     * Setter role
     * @param role
     */
    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    /**
     * Setter role
     * @param role
     */
    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    /**
     * Setter role
     * @param role
     */
    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    /**
     * Setter role
     * @param role
     */
    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    /**
     * Setter role
     * @param role
     */
    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }
}
