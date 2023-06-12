package com.example.hogwartshouses.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue
    private long id;
    private int beds;

    private Gender gender;

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

    @JsonManagedReference
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && beds == room.beds && availableBeds == room.availableBeds && gender == room.gender && houseType == room.houseType && Objects.equals(students, room.students);
    }

   @Override
    public int hashCode() {
        return Objects.hash(id, beds, gender, houseType, availableBeds, students);
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

