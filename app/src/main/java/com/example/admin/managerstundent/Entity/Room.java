package com.example.admin.managerstundent.Entity;

import io.realm.annotations.PrimaryKey;

public class Room {

    /**
     * ID of room
     */
    @PrimaryKey
    private int roomID;

    /**
     * Number of room
     */
    private int roomNumber;

    /**
     * Floor of room
     */
    private int floot;

    /**
     * Getter roomID
     * @return roomID
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * Getter roomNumber
     * @return roomNumber
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Getter floot
     * @return floot
     */
    public int getFloot() {
        return floot;
    }

    /**
     * Setter roomID
     * @param roomID
     */
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    /**
     * Setter roomNumber
     * @param roomNumber
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * Setter floot
     * @param floot
     */
    public void setFloot(int floot) {
        this.floot = floot;
    }
}
