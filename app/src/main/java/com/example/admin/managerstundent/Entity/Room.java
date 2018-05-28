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
    private int floor;

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
     * Getter floor
     * @return floor
     */
    public int getFloor() {
        return floor;
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
     * Setter floor
     * @param floor
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }
}
