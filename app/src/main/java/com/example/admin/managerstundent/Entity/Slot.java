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


    public int getSlotID() {
        return slotID;
    }

    public boolean isSlot1() {
        return slot1;
    }

    public boolean isSlot2() {
        return slot2;
    }

    public boolean isSlot3() {
        return slot3;
    }

    public boolean isSlot4() {
        return slot4;
    }

    public boolean isSlot5() {
        return slot5;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public void setSlot1(boolean slot1) {
        this.slot1 = slot1;
    }

    public void setSlot2(boolean slot2) {
        this.slot2 = slot2;
    }

    public void setSlot3(boolean slot3) {
        this.slot3 = slot3;
    }

    public void setSlot4(boolean slot4) {
        this.slot4 = slot4;
    }

    public void setSlot5(boolean slot5) {
        this.slot5 = slot5;
    }
}
