package com.example.hogwartshouses.service;

import com.example.hogwartshouses.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("SELECT s FROM Student s where s.firstName =:name and s.hasRoom=false ")
    Student findByNameAndHasRoomNot(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Student s SET s.room.id = :roomId, s.hasRoom = true WHERE s.firstName= :name")
    Integer findById(long roomId,String name);

    @Modifying
    @Transactional
    @Query(value="UPDATE Room r SET r.availableBeds = r.availableBeds-1  WHERE r.id = :roomId")
    Integer findById(long roomId);
}
