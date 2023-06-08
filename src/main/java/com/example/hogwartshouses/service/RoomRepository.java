package com.example.hogwartshouses.service;

import com.example.hogwartshouses.model.Gender;
import com.example.hogwartshouses.model.HouseType;
import com.example.hogwartshouses.model.Room;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findRoomByGenderAndHouseType(Gender gender, HouseType houseType);

    @Modifying
    @Transactional
    @Query(value="UPDATE Room r SET r.availableBeds = r.availableBeds-1  WHERE r.id = :roomId")
    Integer updateRoom(@Param("roomId") long roomId);
}



