package com.example.hogwartshouses.service;

import com.example.hogwartshouses.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface StudentRepository extends JpaRepository<Student,Long> {

    Student findStudentByFirstNameAndHasRoomFalse(String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Student s SET s.room.id = :roomId, s.hasRoom = true WHERE s.firstName= :firstName")
    Integer updateStudent(@Param("roomId")long roomId,@Param("firstName")String firstName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Student s SET s.room.id = :roomId, s.hasRoom = false WHERE s.firstName= :firstName")
    Integer assignStudentToRoom(@Param("roomId") long roomId, @Param("firstName") String firstName);

}
