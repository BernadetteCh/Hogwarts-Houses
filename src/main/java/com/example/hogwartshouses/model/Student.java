package com.example.hogwartshouses.model;


import jakarta.persistence.*;

import java.util.Objects;

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
    private boolean hasRoom;

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && hasRoom == student.hasRoom && firstName.equals(student.firstName) && lastName.equals(student.lastName) && housePet == student.housePet && houseType == student.houseType && gender == student.gender && room.equals(student.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, housePet, houseType, gender, hasRoom, room);
    }

    /*
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", housePet=" + housePet +
                ", houseType=" + houseType +
                ", gender=" + gender +
                ", hasRoom=" + hasRoom +
                ", room=" + room +
                '}';
    }*/
}
