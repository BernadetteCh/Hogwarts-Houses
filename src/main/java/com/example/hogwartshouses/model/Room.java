package com.example.hogwartshouses.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue
    private long id;

    private int beds;
    private Gender gender;
    //private List<Student> students;
    private HouseType houseType;
    private int availableBeds;

    @OneToMany(mappedBy = "room")
    private Set<Student> students;

    public Room( int beds, HouseType houseType, int availableBeds, Gender gender) {

        this.beds = beds;
        this.houseType = houseType;
        this.availableBeds = availableBeds;
        this.gender = gender;
    }

    public Room() {

    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public void setHouseType(HouseType houseType) {
        this.houseType = houseType;
    }

    public int getAvailableBeds() {
        return availableBeds;
    }

    public void setAvailableBeds(int availableBeds) {
        this.availableBeds = availableBeds;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", beds=" + beds +
                ", students=" + students +
                ", houseType=" + houseType +
                ", availableBeds=" + availableBeds +
                '}';
    }
}

