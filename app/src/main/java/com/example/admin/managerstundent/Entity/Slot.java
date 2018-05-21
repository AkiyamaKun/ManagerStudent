package com.example.admin.managerstundent.Entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Slot extends RealmObject {

    /**
     * ID of slot
     */
    @PrimaryKey
    private int slotID;

    /**
     * 9h-11h
     */
    private boolean slot1;

    /**
     * 1h-3h
     */
    private boolean slot2;

    /**
     * 3h-5h
     */
    private boolean slot3;

    /**
     * 5h-7h
     */
    private boolean slot4;

    /**
     * 7h-9h
     */
    private boolean slot5;


    /**
     * Getter slotID
     * @return slotID
     */
    public int getSlotID() {
        return slotID;
    }

    /**
     * Getter slot1
     * @return slot1
     */
    public boolean isSlot1() {
        return slot1;
    }

    /**
     * Getter slot2
     * @return slot2
     */
    public boolean isSlot2() {
        return slot2;
    }

    /**
     * Getter slot3
     * @return slot3
     */
    public boolean isSlot3() {
        return slot3;
    }

    /**
     * Getter slot4
     * @return slot4
     */
    public boolean isSlot4() {
        return slot4;
    }

    /**
     * Getter slot5
     * @return slot5
     */
    public boolean isSlot5() {
        return slot5;
    }

    /**
     * Setter slotID
     * @param slotID
     */
    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    /**
     * Setter slot1
     * @param slot1
     */
    public void setSlot1(boolean slot1) {
        this.slot1 = slot1;
    }

    /**
     * Setter slot2
     * @param slot2
     */
    public void setSlot2(boolean slot2) {
        this.slot2 = slot2;
    }

    /**
     * Setter slot3
     * @param slot3
     */
    public void setSlot3(boolean slot3) {
        this.slot3 = slot3;
    }

    /**
     * Setter slot4
     * @param slot4
     */
    public void setSlot4(boolean slot4) {
        this.slot4 = slot4;
    }

    /**
     * Setter slot5
     * @param slot5
     */
    public void setSlot5(boolean slot5) {
        this.slot5 = slot5;
    }
}
