package com.example.trantien.theflashquiz.mvc.views;

public class dataRoom {
    private String roomName;
    private String ID;
    private int roomSizes;
    private int num_Members;

    public dataRoom(String roomName, String ID, int roomSizes, int num_Members) {
        this.roomName = roomName;
        this.ID = ID;
        this.roomSizes = roomSizes;
        this.num_Members = num_Members;
    }
    public int getRoomSizes(String temp) {
        return roomSizes;
    }

    public void setRoomSizes(int roomSizes) {
        this.roomSizes = roomSizes;
    }

    public int getNum_Members() {
        return num_Members;
    }

    public void setNum_Members(int num_Members) {
        this.num_Members = num_Members;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
