package com.example.hogwartshouses.service;

import com.example.hogwartshouses.model.Gender;
import com.example.hogwartshouses.model.HouseType;
import com.example.hogwartshouses.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r where r.availableBeds >0")
    List<Room> findAll();
    @Query("SELECT r.id FROM Room r where r.gender = :gender and r.houseType = :houseType and r.availableBeds > 0")
    Optional<Long> findFirstBy(@Param("gender") Gender gender, @Param("houseType") HouseType houseType);

    @Query("SELECT r FROM Room r where r.houseType = :houseType and r.gender = :gender")
    Optional<Room> findRoomBy(@Param("gender") Gender gender, @Param("houseType") HouseType houseType);
}



