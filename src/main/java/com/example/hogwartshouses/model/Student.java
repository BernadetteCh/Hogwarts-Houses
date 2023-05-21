package com.example.hogwartshouses.model;


import jakarta.persistence.*;

@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private HousePet housePet;
    private HouseType houseType;
    private Gender gender;

    public Gender getGender() {
        return gender;
    }

    @ManyToOne
    @JoinColumn(name="room_id")
    private Room room;

    public Student(String firstName, String lastName, HousePet housePet, HouseType houseType, boolean hasRoom, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.housePet = housePet;
        this.houseType = houseType;
        this.hasRoom = hasRoom;
        this.gender = gender;
    }

    public Student() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public HousePet getHousePet() {
        return housePet;
    }

    public void setHousePet(HousePet housePet) {
        this.housePet = housePet;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public void setHouseType(HouseType houseType) {
        this.houseType = houseType;
    }

    public boolean isHasRoom() {
        return hasRoom;
    }

    public void setHasRoom(boolean hasRoom) {
        this.hasRoom = hasRoom;
    }

    private boolean hasRoom;
}
