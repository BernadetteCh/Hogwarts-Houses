package com.example.hogwartshouses.service;

import com.example.hogwartshouses.endpoint.HogwartsEndpoints;
import com.example.hogwartshouses.model.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

class HogwartsServiceTest {
    private StudentRepository studentRepository = mock(StudentRepository.class);
    private RoomRepository roomRepository = mock(RoomRepository.class);
    HogwartsService hogwartsService = new HogwartsService(studentRepository, roomRepository);

    @Test
    void assignStudent() {
        Room room = new Room(4, HouseType.GRYFFINDOR, 2, Gender.FEMALE);
        Student student = new Student("Hermine", "Granger", HousePet.CAT, HouseType.GRYFFINDOR, false, Gender.FEMALE);
        room.setStudents(Set.of(student));
        when(studentRepository.findStudentByFirstNameAndHasRoomFalse(student.getFirstName())).thenReturn(student);
        when(roomRepository.findRoomByGenderAndHouseType(student.getGender(), student.getHouseType())).thenReturn(Optional.of(room));
        assertEquals((int) room.getId(), hogwartsService.assignStudent(student.getFirstName()));
    }

    @Test
    void saveStudent() {
        Student student1 = new Student("Harry", "Potter", HousePet.OWL, HouseType.GRYFFINDOR, true, Gender.MALE);
        hogwartsService.saveStudent(student1);
        verify(studentRepository).save(student1);

    }

    @Test
    void saveRoom() {
        Room room1 = new Room(4, HouseType.GRYFFINDOR, 2, Gender.FEMALE);
        hogwartsService.saveRoom(room1);
        verify(roomRepository).save(room1);
    }

    @Test
    void findAllRooms() {
        hogwartsService.findAllRooms();
        verify(roomRepository).findAll();
    }

    @Test
    void findARoomForRatOwner() {
        Room room = new Room(4, HouseType.GRYFFINDOR, 2, Gender.MALE);
        Student student = new Student("Ron", "Weasly", HousePet.RAT, HouseType.GRYFFINDOR, false, Gender.MALE);
        room.setStudents(Set.of(student));
        when(studentRepository.findStudentByFirstNameAndHasRoomFalse(student.getFirstName())).thenReturn(student);
        when(roomRepository.findRoomByGenderAndHouseType(student.getGender(), student.getHouseType())).thenReturn(Optional.of(room));

        assertEquals((int) room.getId(), hogwartsService.findARoomForRatOwner(student.getFirstName()));
    }

}